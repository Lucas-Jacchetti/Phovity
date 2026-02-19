'use client'

import { Sidebar } from "../components/Sidebar"
import { useEffect, useState } from "react"

export default function Settings() {
  const [darkMode, setDarkMode] = useState(false)

  useEffect(() => {
    const savedTheme = localStorage.getItem("theme")

    if (savedTheme === "dark") {
      document.documentElement.classList.add("dark")
      setDarkMode(true)
    } else if (savedTheme === "light") {
      document.documentElement.classList.remove("dark")
      setDarkMode(false)
    } else {
      const prefersDark = window.matchMedia("(prefers-color-scheme: dark)").matches
      if (prefersDark) {
        document.documentElement.classList.add("dark")
        setDarkMode(true)
      }
    }
  }, [])

  const toggleTheme = () => {
    if (darkMode) {
      document.documentElement.classList.remove("dark")
      localStorage.setItem("theme", "light")
    } else {
      document.documentElement.classList.add("dark")
      localStorage.setItem("theme", "dark")
    }

    setDarkMode(!darkMode)
  }

  return (
    <div className="w-full bg-white dark:bg-zinc-900 transition-colors duration-300">
      <Sidebar />

      <div className="min-h-screen px-8 py-10">
        <div className="mx-auto max-w-3xl">

          <h1 className="text-2xl font-semibold text-gray-900 dark:text-white">
            Configurações
          </h1>

          <div className="mt-10 rounded-2xl border border-gray-200 dark:border-zinc-700 p-6 shadow-sm bg-white dark:bg-zinc-800 transition-colors duration-300">
            
            <div className="flex items-center justify-between">
              
              <div>
                <h2 className="text-lg font-medium text-gray-900 dark:text-white">
                  Modo Escuro
                </h2>
                <p className="text-sm text-gray-500 dark:text-gray-400">
                  Alterar entre modo claro e escuro
                </p>
              </div>

              <button
                onClick={toggleTheme}
                className={`relative inline-flex h-7 w-14 items-center rounded-full transition-colors duration-300 ${
                  darkMode ? "bg-black" : "bg-gray-300"
                }`}
              >
                <span
                  className={`inline-block h-5 w-5 transform rounded-full bg-white transition-transform duration-300 ${
                    darkMode ? "translate-x-7" : "translate-x-1"
                  }`}
                />
              </button>

            </div>

          </div>
        </div>
      </div>
    </div>
  )
}
