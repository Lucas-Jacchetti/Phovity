import { NextRequest, NextResponse } from "next/server";

const BACKEND_URL = process.env.NEXT_PUBLIC_API_URL!;

async function handler(
  request: NextRequest,
  context: { params: Promise<{ path: string[] }> }
) {
  try {
    const { path } = await context.params;

    const token = request.cookies.get("token")?.value;

    const url = `${BACKEND_URL}/${path.join("/")}${request.nextUrl.search}`;

    const headers = new Headers();

    request.headers.forEach((value, key) => {
      const lower = key.toLowerCase();

      if (
        lower === "host" ||
        lower === "content-length" ||
        lower === "connection"
      ) return;

      headers.set(key, value);
    });

    if (token) {
      headers.set("Authorization", `Bearer ${token}`);
    }

    let body: any = undefined;

    if (request.method !== "GET" && request.method !== "HEAD") {
      const contentType = request.headers.get("content-type") || "";

      if (contentType.includes("application/json")) {
        const json = await request.json();
        body = JSON.stringify(json);
        headers.set("Content-Type", "application/json");
      } else {
        body = await request.text();
      }
    }

    const backendRes = await fetch(url, {
      method: request.method,
      headers,
      body,
      duplex: "half", 
    } as RequestInit);

    const response = new NextResponse(backendRes.body, {
      status: backendRes.status,
    });

    backendRes.headers.forEach((value, key) => {
      const lower = key.toLowerCase();

      if (
        lower === "content-encoding" ||
        lower === "content-length" ||
        lower === "transfer-encoding"
      ) return;

      response.headers.set(key, value);
    });

    return response;
  } catch (error) {
    console.error("PROXY ERROR:", error);

    return NextResponse.json(
      { error: "Erro no proxy" },
      { status: 500 }
    );
  }
}

export { handler as GET };
export { handler as POST };
export { handler as PUT };
export { handler as PATCH };
export { handler as DELETE };