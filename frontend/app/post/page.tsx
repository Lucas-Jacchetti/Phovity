'use client'

import { Sidebar } from "../components/Sidebar"

import { api } from '../services/apiService'
import { useState } from 'react'


export default function CreatePostView() {
  const [description, setDescripion] = useState("");
  const [tag, setTag] = useState("");
  const [image, setImage] = useState<File | null>(null);
  const [responseMessage, setResponseMessage] = useState("");

  const handleSubmit = async (event: { preventDefault: () => void }) => {
    event.preventDefault();

    const formData = new FormData();

    if (!image) {
      setResponseMessage("Selecione uma imagem");
      return;
    }

    formData.append(
      "data",
      new Blob(
        [JSON.stringify({ description, tag })],
        { type: "application/json" }
      )
    );
    formData.append("image", image);

    try {
      await api
        .post("/posts", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }); 
        setResponseMessage("Post concluído!")
    } catch (error) {
      setResponseMessage("Ocorreu um erro ao fazer o post")
    }
  }

  return (
    <div className="w-full">
     <Sidebar/>
     <div className="min-h-screen bg-white px-8 py-10">
      <div className="mx-auto max-w-5xl">
        <h1 className="text-2xl font-semibold text-gray-900">
          Criar Novo Post
        </h1>
        <p className="mb-8 text-sm text-gray-500">
          Preencha os campos abaixo para criar seu post
        </p>

        <form className="grid grid-cols-1 gap-6 md:grid-cols-3" onSubmit={handleSubmit}>
          <div className="space-y-6 md:col-span-2">
            <div>
              <label className="mb-2 block text-sm font-medium text-gray-700">
                DESCRIÇÃO DO POST
              </label>
              <textarea
                className="h-40 w-full text-black resize-none rounded-lg border border-gray-300 p-4 text-sm placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-black"
                placeholder="Escreva a descrição do seu post aqui..."
                value={description}
                onChange={(e) => setDescripion(e.target.value)}
              />
            </div>

            <div>
              <label className="mb-2 block text-sm font-medium text-gray-700">
                TAG
              </label>
              <input
                type="text"
                className="w-full text-black rounded-lg border border-gray-300 p-3 text-sm placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-black"
                placeholder="Ex: tecnologia, design, marketing..."
                value={tag}
                onChange={(e) => setTag(e.target.value)}
              />
            </div>

            <div className="flex gap-3">
              <button
                type="submit"
                className="rounded-lg bg-black px-6 py-2 text-sm font-medium text-white hover:bg-gray-900 hover:cursor-pointer"
              >
                Publicar Post
              </button>
              <button
                type="button"
                className="rounded-lg bg-gray-200 px-6 py-2 text-sm font-medium text-gray-700 hover:bg-gray-300 hover:cursor-pointer"
              >
                Cancelar
              </button>
            </div>
          </div>

          <div>
            <label
              htmlFor="image-upload"
              className="mt-7 flex h-56 cursor-pointer flex-col items-center justify-center rounded-lg border-2 border-dashed border-gray-300 text-center hover:border-black transition"
            >
              <div className="flex h-10 w-10 items-center justify-center rounded-full bg-black">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  className="h-5 w-5 text-white"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                  strokeWidth={2}
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M3 16l4-4a3 3 0 014 0l4 4m0 0l4-4a3 3 0 014 0l4 4M13 10V3m0 0L9 7m4-4l4 4"
                  />
                </svg>
              </div>

              <p className="text-sm font-medium text-gray-800">
                Upload de Imagem
              </p>

              {image && (
                <p className="text-xs text-gray-500 mt-2">
                  {image.name}
                </p>
              )}
            </label>

            <input
              id="image-upload"
              type="file"
              accept="image/*"
              onChange={(e) => {
                if (e.target.files) {
                  setImage(e.target.files[0]);
                }
              }}
              className="hidden"
            />
          </div>
        </form>
        {responseMessage && <p className="text-black mt-3.5 ml-2">{responseMessage}</p>}
      </div>
    </div>
   </div>
    
  )
}
