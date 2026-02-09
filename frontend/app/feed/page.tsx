// import { ImageMasonryClient } from '../components/ImageMasonryClient'
import { Sidebar } from '../components/Sidebar'

export default function FeedPage() {
  return (
    <div className="flex">
      <Sidebar />

      <main className="ml-16 flex-1 p-8">
        <header className="mb-8">
          <h1 className="text-2xl font-semibold">Explorar</h1>
          <p className="text-sm text-gray-500">
            Descubra conteúdo inspirador
          </p>
        </header>
        
      </main>
    </div>
  )
}