import { NextRequest, NextResponse } from "next/server";

const BACKEND_URL = "https://phovity.onrender.com";

async function handler(
  request: NextRequest,
  { params }: { params: { path: string[] } }
) {

  const token = request.cookies.get("token")?.value;

  const path = params.path.join("/");

  const url = `${BACKEND_URL}/${path}`;

  const backendRes = await fetch(url, {
    method: request.method,
    headers: {
      "Content-Type": "application/json",
      Authorization: token ? `Bearer ${token}` : "",
    },
    body:
      request.method !== "GET" && request.method !== "HEAD"
        ? await request.text()
        : undefined,
  });

  const data = await backendRes.text();

  return new NextResponse(data, {
    status: backendRes.status,
    headers: {
      "Content-Type":
        backendRes.headers.get("content-type") || "application/json",
    },
  });
}

export { handler as GET };
export { handler as POST };
export { handler as PUT };
export { handler as DELETE };
export { handler as PATCH };