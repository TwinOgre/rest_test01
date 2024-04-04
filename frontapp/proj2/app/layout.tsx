import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import Link from "next/link";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "REST-API_TEST02",
  description: "Generated by create next app",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="ko">
      <body className={inter.className}>
        <div>
          <Link href={'/'}>홈으로</Link> <span> | </span>
          <Link href={'/article'}>게시글로</Link>
        </div>
        {children}
        </body>
    </html>
  );
}