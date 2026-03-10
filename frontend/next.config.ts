import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  images: {
    remotePatterns: [
      {
        protocol: "https",
        hostname: "phovity.onrender.com",
        pathname: "/uploads/**",
      },
    ],
  },
};

export default nextConfig;