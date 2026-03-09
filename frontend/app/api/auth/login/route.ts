import { NextResponse } from "next/server";

export async function POST(request: Request) {
  const body = await request.json();

  const backendResponse = await fetch(
    "https://phovity.onrender.com/auth/login",
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
      credentials: "include",
    }
  );

  const data = await backendResponse.json();

  const response = NextResponse.json(data);

  const cookie = backendResponse.headers.get("set-cookie");

  if (cookie) {
    response.headers.set("set-cookie", cookie);
  }

  return response;
}