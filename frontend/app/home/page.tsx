import Masonry from '../components/Masonry'
import { SearchBar } from '../components/Searchbar'
import { Sidebar } from '../components/Sidebar'

export default function FeedPage() {
  return (
    <div className="flex w-full">
      <Sidebar />
      <div className='flex flex-col'>
        <div className='flex flex-row'>
          <main className="ml-16 flex-1 p-8 ">
            <header className="mb-8">
              <h1 className="text-2xl font-semibold text-black">Home</h1>
              <p className="text-sm text-gray-600">
                Tudo que você gosta
              </p>
            </header>
          </main>
          <div className='ml-14.5'>
            <SearchBar/>
          </div>
        </div>
        <div className='ml-22 mr-5'>
          <Masonry/>
        </div>
      </div>
    </div>
  )
}