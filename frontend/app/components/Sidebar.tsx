'use client'

import {
  Home,
  Compass,
  Plus,
  Bell,
  Bookmark,
  Settings,
} from 'lucide-react'

import { useRouter, usePathname } from 'next/navigation'

export function Sidebar() {
  const router = useRouter()
  const pathname = usePathname()

  return (
    <aside className="fixed left-0 top-0 h-screen w-16 bg-black flex flex-col items-center py-4 gap-6">
      <div className="text-white font-bold text-lg">P</div>

      <nav className="flex flex-col gap-4 mt-4">
        <IconButton
          icon={<Home size={20} />}
          active={pathname === '/'}
          onClick={() => router.push('/')}
        />

        <IconButton
          icon={<Compass size={20} />}
          active={pathname.startsWith('/explore')}
          onClick={() => router.push('/explore')}
        />

        <IconButton
          icon={<Plus size={20} />}
          active={pathname.startsWith('/post')}
          onClick={() => router.push('/post')}
        />

        <IconButton
          icon={<Bell size={20} />}
          active={pathname.startsWith('/notifications')}
          onClick={() => router.push('/notifications')}
        />

        <IconButton
          icon={<Bookmark size={20} />}
          active={pathname.startsWith('/saved')}
          onClick={() => router.push('/saved')}
        />
      </nav>

      <div className="mt-auto flex flex-col gap-4 items-center">
        <IconButton
          icon={<Settings size={20} />}
          active={pathname.startsWith('/settings')}
          onClick={() => router.push('/settings')}
        />

        <div className="w-8 h-8 rounded-full bg-gray-300" />
      </div>
    </aside>
  )
}

function IconButton({
  icon,
  active,
  onClick,
}: {
  icon: React.ReactNode
  active?: boolean
  onClick: () => void
}) {
  return (
    <button
      onClick={onClick}
      className={`w-10 h-10 rounded-xl flex items-center justify-center transition hover:cursor-pointer
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
