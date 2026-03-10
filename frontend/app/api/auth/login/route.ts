import { NextRequest, NextResponse } from "next/server";

export async function POST(request: NextRequest) {
  const body = await request.json();

  const backendRes = await fetch("https://phovity.onrender.com/auth/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body),
  });

  if (!backendRes.ok) {
    return NextResponse.json({ error: "Login failed" }, { status: 401 });
  }

  const setCookie = backendRes.headers.get("set-cookie");
  const token = setCookie?.match(/token=([^;]+)/)?.[1];

  if (!token) {
    return NextResponse.json({ error: "No token" }, { status: 500 });
  }

  const response = NextResponse.json({ ok: true });
  response.cookies.set("token", token, {
    httpOnly: true,
    secure: true,
    sameSite: "lax",
    maxAge: 60 * 60 * 2,
    path: "/",
  });

  return response;
}