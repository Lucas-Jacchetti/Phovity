'use client'

import {
  Home,
  Compass,
  Plus,
  Bell,
  Bookmark,
  Settings,
} from 'lucide-react'

export function Sidebar() {
  return (
    <aside className="fixed left-0 top-0 h-screen w-16 bg-black flex flex-col items-center py-4 gap-6">
      <div className="text-white font-bold text-lg">P</div>

      <nav className="flex flex-col gap-4 mt-4">
        <IconButton icon={<Home size={20} />} />
        <IconButton icon={<Compass size={20} />} active />
        <IconButton icon={<Plus size={20} />} />
        <IconButton icon={<Bell size={20} />} />
        <IconButton icon={<Bookmark size={20} />} />
      </nav>

      <div className="mt-auto flex flex-col gap-4 items-center">
        <IconButton icon={<Settings size={20} />} />
        <div className="w-8 h-8 rounded-full bg-gray-300" />
      </div>
    </aside>
  )
}

function IconButton({
  icon,
  active,
}: {
  icon: React.ReactNode
  active?: boolean
}) {
  return (
    <button
      className={`w-10 h-10 rounded-xl flex items-center justify-center transition
        ${
          active
            ? 'bg-white text-black'
            : 'text-white hover:bg-white/10'
        }`}
    >
      {icon}
    </button>
  )
}