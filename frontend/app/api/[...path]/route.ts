import { NextRequest, NextResponse } from "next/server";

const BACKEND_URL = process.env.NEXT_PUBLIC_API_URL;

async function handler(
  request: NextRequest,
  context: { params: Promise<{ path: string[] }> }
) {
  const { path } = await context.params;

  const token = request.cookies.get("token")?.value;

  const url = `${BACKEND_URL}/${path.join("/")}${request.nextUrl.search}`;

  const headers = new Headers(request.headers);

  if (token) {
    headers.set("Authorization", `Bearer ${token}`);
  }

  headers.delete("host");

  const backendRes = await fetch(url, {
    method: request.method,
    headers,
    body:
      request.method !== "GET" && request.method !== "HEAD"
        ? request.body
        : undefined,
  });

  console.log("URL chamada:", url);
  console.log("Token presente:", !!token);
  console.log("Status resposta:", backendRes.status);

  const response = new NextResponse(backendRes.body, {
    status: backendRes.status,
  });

  backendRes.headers.forEach((value, key) => {
    if (key.toLowerCase() === "content-encoding") return;
    if (key.toLowerCase() === "content-length") return;

    response.headers.set(key, value);
  });

  return response;
}

export { handler as GET };
export { handler as POST };
export { handler as PUT };
export { handler as PATCH };
export { handler as DELETE };