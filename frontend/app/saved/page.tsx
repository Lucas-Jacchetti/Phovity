'use client'
import SavedMasonry from '../components/SavedMasonry'
import { SearchBar } from '../components/Searchbar'
import { Sidebar } from '../components/Sidebar'

export default function SavedPage() {
  return (
    <div className="flex w-full">
      <Sidebar />
      <div className='flex flex-col'>
        <div className='flex flex-row'>
          <main className="ml-16 flex-1 p-8 ">
            <header className="mb-8">
              <h1 className="text-2xl font-semibold text-black dark:text-white">Salvos</h1>
              <p className="text-sm text-gray-600 dark:text-gray-400">
                Conteúdo salvo
              </p>
            </header>
          </main>
          <div className='ml-70 absolute'>
            <SearchBar/>
          </div>
        </div>
        <div className='ml-[4%] mr-[2%]'>
          <SavedMasonry/>
        </div>
      </div>
    </div>
  )
}