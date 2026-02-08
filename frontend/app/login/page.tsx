'use client'

import { Mail, Eye, EyeOff } from 'lucide-react'
import Image from 'next/image'
import { useState } from 'react'

export default function LoginPage() {
  const [showPassword, setShowPassword] = useState(false)

  return (
    
    <div className="min-h-screen flex items-center justify-center bg-gray-100 px-4">
      {/* <Image
      src="/fundo.jpg"
      fill={true}
      className='absolute inset-0 w-full h-full object-cover -z-10'
      alt="Fundo"
      /> */}
      <div className="w-full max-w-md bg-white rounded-2xl shadow-xl p-8">
        <h1 className="text-3xl font-bold text-center text-black">Phovity</h1>
        <p className="text-center text-gray-500 mt-1">
          Entre na sua conta
        </p>

        <form className="mt-8 space-y-5">
          <div>
            <p className="block text-sm font-medium mb-1 text-black">
              Email
            </p>
            <div className="relative">
              <input
                type="email"
                placeholder="seu@email.com"
                className="w-full border border-gray-300 rounded-lg px-4 py-2 pr-10 focus:outline-none focus:ring-2 focus:ring-black text-black"
              />
              <Mail className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-400 w-5 h-5"/>
            </div>
          </div>

          <div>
            <label className="block text-sm font-medium mb-1 text-black">
              Senha
            </label>
            <div className="relative">
              <input
                type={showPassword ? 'text' : 'password'}
                placeholder="••••••••"
                className="w-full border border-gray-300 rounded-lg px-4 py-2 pr-10 focus:outline-none focus:ring-2 text-black focus:ring-black"
              />
              <button
                type="button"
                onClick={() => setShowPassword(!showPassword)}
                className="absolute right-3 top-1/2 -translate-y-1/2"
              >
                {showPassword ? (
                  <EyeOff className="w-5 h-5 text-gray-400" />
                ) : (
                  <Eye className="w-5 h-5 text-gray-400" />
                )}
              </button>
            </div>

            <div className="text-right mt-2">
              <a href="#" className="text-sm text-gray-500 hover:underline">
                Esqueci minha senha
              </a>
            </div>
          </div>

          <button
            type="submit"
            className="w-full bg-black text-white py-2.5 rounded-lg font-medium hover:opacity-90 transition"
          >
            Entrar
          </button>
        </form>

        <p className="text-center text-sm text-gray-500 mt-6">
          Não tem uma conta?{' '}
          <a href="#" className="text-black font-medium hover:underline">
            Cadastre-se
          </a>
        </p>

        <div className="flex items-center gap-3 my-6">
          <div className="h-px bg-gray-200 flex-1" />
          <div className="h-px bg-gray-200 flex-1" />
        </div>

        {/* Social Login */}
        <div className="flex justify-center gap-4 text-black">
          <SocialButton label="Google">G</SocialButton>
          <SocialButton label="Apple"></SocialButton>
          <SocialButton label="Microsoft">▦</SocialButton>
        </div>
      </div>
    </div>
  )
}

function SocialButton({
  children,
  label,
}: {
  children: React.ReactNode
  label: string
}) {
  return (
    <button
      aria-label={label}
      className="w-11 h-11 rounded-full bg-gray-100 flex items-center justify-center text-lg hover:bg-gray-200 transition"
    >
      {children}
    </button>
  )
}
