'use client'

import { Mail, Eye, EyeOff, User } from 'lucide-react'
import Image from 'next/image'
import { useState } from 'react'
import Link from 'next/link'
import { api } from '../services/apiService'

export default function LoginPage() {
  const [showPassword, setShowPassword] = useState(false)
  const [userName, setUserName] = useState("")
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [responseMessage, setResponseMessage] = useState("")
  const [errors, setErrors] = useState<any>({})

  const handleSubmit = async (event: any) => {
    event.preventDefault()

    setErrors({})
    setResponseMessage("")

    const newRegister = {
      email,
      password,
      userName
    }

    try {
      await api.post("/auth/register", newRegister)
      setResponseMessage("Cadastrado! Faça seu login")
    } catch (error: any) {
      const data = error.response?.data

      if (data?.error) {
        setResponseMessage(data.error)
      } else if (typeof data === "object") {
        setErrors(data)
      } else {
        setResponseMessage("Erro inesperado")
      }
    }
  }

  return (
    <div className="min-h-screen flex items-center justify-center px-4">
      <Image
        src="/fundo.jpg"
        fill
        className="absolute inset-0 w-full h-full object-cover -z-10"
        alt="Fundo"
      />

      <div className="w-full max-w-md bg-white rounded-2xl shadow-xl p-8">
        <h1 className="text-3xl font-bold text-center text-black">Phovity</h1>
        <p className="text-center text-gray-500 mt-1">
          Cadastre sua conta
        </p>

        <form onSubmit={handleSubmit} className="mt-8 space-y-5">

          {/* EMAIL */}
          <div>
            <p className="block text-sm font-medium mb-1 text-black">
              Email
            </p>

            <div className="relative">
              <input
                type="email"
                value={email}
                onChange={(e) => {
                  setEmail(e.target.value)
                  setErrors((prev: any) => ({ ...prev, email: null }))
                }}
                placeholder="seu@email.com"
                className="w-full border border-gray-300 rounded-lg px-4 py-2 pr-10 focus:outline-none focus:ring-2 focus:ring-black text-black"
              />
              <Mail className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-400 w-5 h-5"/>
            </div>

            {errors.email && (
              <p className="text-red-500 text-xs mt-1">{errors.email}</p>
            )}
          </div>

          {/* SENHA */}
          <div>
            <label className="block text-sm font-medium mb-1 text-black">
              Senha
            </label>

            <div className="relative">
              <input
                value={password}
                onChange={(e) => {
                  setPassword(e.target.value)
                  setErrors((prev: any) => ({ ...prev, password: null }))
                }}
                type={showPassword ? 'text' : 'password'}
                placeholder="••••••••"
                className="w-full border border-gray-300 rounded-lg px-4 py-2 pr-10 focus:outline-none focus:ring-2 text-black focus:ring-black"
              />

              <button
                type="button"
                onClick={() => setShowPassword(!showPassword)}
                className="absolute right-3 top-1/2 -translate-y-1/2 cursor-pointer"
              >
                {showPassword ? (
                  <EyeOff className="w-5 h-5 text-gray-400" />
                ) : (
                  <Eye className="w-5 h-5 text-gray-400" />
                )}
              </button>
            </div>

            {errors.password && (
              <p className="text-red-500 text-xs mt-1">{errors.password}</p>
            )}
          </div>

          {/* USERNAME */}
          <div className="mb-10">
            <label className="block text-sm font-medium mb-1 text-black">
              Nome de usuário
            </label>

            <div className="relative">
              <input
                value={userName}
                onChange={(e) => {
                  setUserName(e.target.value)
                  setErrors((prev: any) => ({ ...prev, userName: null }))
                }}
                className="w-full border border-gray-300 rounded-lg px-4 py-2 pr-10 focus:outline-none focus:ring-2 text-black focus:ring-black"
                placeholder="seu_nome"
              />
              <User className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-400 w-5 h-5"/>
            </div>

            {errors.userName && (
              <p className="text-red-500 text-xs mt-1">{errors.userName}</p>
            )}
          </div>

          <button
            type="submit"
            className="w-full bg-black text-white py-2.5 rounded-lg font-medium hover:opacity-90 transition cursor-pointer"
          >
            Cadastrar
          </button>
        </form>

        {/* MENSAGEM GERAL */}
        <div className="text-black flex mt-5 w-full items-center justify-center">
          {responseMessage && (
            <Link className="underline" href="login">
              {responseMessage}
            </Link>
          )}
        </div>

        <div className="flex items-center gap-3 my-6">
          <div className="h-px bg-gray-200 flex-1" />
          <div className="h-px bg-gray-200 flex-1" />
        </div>

      </div>
    </div>
  )
}