'use client'

import { Search } from 'lucide-react'

export function SearchBar() {
    return (
        <div className="p-8 mt-2">
            <div className="relative w-300">
                <input type="text" placeholder="Pesquise por tag" className="h-10 w-full rounded-xl border border-black text-black pl-3 pr-10"/>
                <Search size={20} className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-600"/>
            </div>
        </div>
    )
}
