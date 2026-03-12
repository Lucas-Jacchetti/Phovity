import { NextRequest, NextResponse } from "next/server";

const BACKEND_URL = "https://phovity.onrender.com";

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
        ? await request.text()
        : undefined,
  });

  const data = await backendRes.text();

  console.log("URL chamada:", url);
  console.log("Token presente:", !!token);
  console.log("Status resposta:", backendRes.status);
  console.log("Resposta:", data.slice(0, 300));

  const contentType = backendRes.headers.get("content-type") || "application/json";

  return new NextResponse(data, {
    status: backendRes.status,
    headers: {
      "Content-Type": contentType,
    },
  });
}

export { handler as GET };
export { handler as POST };
export { handler as PUT };
export { handler as PATCH };
export { handler as DELETE };