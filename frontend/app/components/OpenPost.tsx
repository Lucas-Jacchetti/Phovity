'use client'

type OpenPostProps = {
  post: any
  onClose: () => void
}

export default function OpenPost({post, onClose} : OpenPostProps) {
  return (
    <div className="fixed inset-0 bg-black/70 flex items-center justify-center z-50 text-black">

      <div className="bg-white w-[95%] max-w-5xl h-[85%] rounded-2xl flex overflow-hidden shadow-2xl ">

        <div className="w-1/2 bg-gray-100 flex items-center justify-center">
          <img
            src={`http://localhost:8080${post.postImgUrl}`}
            alt="Post"
            className="max-h-full object-contain"
          />
        </div>

        <div className="w-1/2 flex flex-col">

          <div className="p-5 border-b border-b-gray-500 flex justify-between items-center">
            <div className="flex items-center gap-3">
              <div className="w-9 h-9 bg-gray-300 rounded-full"></div>
              <span className="font-semibold">{post.userName || 'username'}</span>
            </div>

            <button onClick={onClose} className="text-gray-500 hover:text-black text-xl hover:cursor-pointer">
              ✕
            </button>
          </div>

          <div className="flex-1 overflow-y-auto p-5 space-y-4">
            <div className="text-sm">
              <span className="font-semibold">username</span> Comentário exemplo.
            </div>

            <div className="text-sm">
              <span className="font-semibold">user2</span> Outro comentário aqui.
            </div>

            <div className="text-sm">
              <span className="font-semibold">user3</span> Mais um comentário.
            </div>
          </div>

          <div className="border-t border-t-gray-500 p-5 space-y-4">

            <div className="flex items-center gap-2 text-xl">
              <button className="hover:scale-110 transition">♥️</button>
              <div className="text-sm font-semibold">128 curtidas</div>
            </div>

            <div className="flex gap-3">
              <input
                type="text"
                placeholder="Adicione um comentário..."
                className="flex-1 border rounded-lg px-4 py-2 text-sm outline-none"
              />
              <button className="text-black bg-blue-400 font-semibold rounded-lg hover:cursor-pointer">
                <p className="p-3">Publicar</p>
              </button>
            </div>

          </div>

        </div>
      </div>
    </div>
  )
}
