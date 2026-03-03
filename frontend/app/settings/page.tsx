'use client'

import { ModeToggle } from "../components/ModeToggle"
import { Sidebar } from "../components/Sidebar"
import { useEffect, useState } from "react"

export default function Settings() {


  return (
    <div className="w-full transition-colors duration-300">
      <Sidebar />

      <div className="min-h-screen px-8 py-10">
        <div className="mx-auto max-w-3xl">

          <h1 className="text-2xl font-semibold">
            Configurações
          </h1>

          <div className="mt-10 rounded-2xl border border-current/20 p-6 shadow-sm transition-colors duration-300">
            
            <div className="flex items-center justify-between">
              
              <div>
                <h2 className="text-lg font-medium">
                  Modo Escuro
                </h2>
                <p className="text-sm opacity-70">
                  Alterar entre modo claro e escuro
                </p>
              </div>

              <ModeToggle/>

            </div>

          </div>
        </div>
      </div>
    </div>
  )
}
