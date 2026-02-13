'use client'

import Masonry from "../components/Masonry"
import { Sidebar } from "../components/Sidebar"


export default function ProfileHeader() {

  return (
    <div className="w-full max-w-5xl mx-auto px-8 py-10">
      <Sidebar />
      <div className="flex gap-10 items-start">
        
        <div className="shrink-0">
          <div className="w-32 h-32 rounded-full overflow-hidden border-4 border-white shadow-md">
            <img
              src="https://i.pravatar.cc/300"
              alt="Profile"
              className="w-full h-full object-cover"
            />
          </div>
        </div>

        <div className="flex flex-col gap-4 flex-1">
          
          <div className="flex items-center gap-6">
            <h2 className="text-2xl font-semibold text-black">
              marcossilva
            </h2>

            <button className="bg-black text-white text-sm px-4 py-1.5 rounded-md hover:bg-gray-800 transition">
              Editar Perfil
            </button>
          </div>

          <div className="flex flex-col gap-1 text-sm">
            <p className="text-gray-600 max-w-md">
              Designer gráfico e fotógrafo apaixonado por minimalismo e composições monocromáticas.
              Explorando a beleza através da simplicidade. 🖤
            </p>
            
          </div>
        </div>
      </div>

      <div className="mt-10 border-b border-gray-200"></div>
     <Masonry/>
    </div>
  )
}
