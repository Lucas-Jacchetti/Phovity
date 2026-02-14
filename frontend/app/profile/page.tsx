'use client'

import { useEffect, useState } from "react"
import Masonry from "../components/Masonry"
import { Sidebar } from "../components/Sidebar"
import { api } from "../services/apiService";


export default function ProfileHeader() {
  const [userName, setUserName] = useState("");
  const [bio, setBio] = useState("");
  const [activeBio, setActiveBio] = useState(false);
  const [profileImgUrl, setProfileImgUrl] = useState(null);
  const [loading, setLoading] = useState(true);

  async function fetchProfile(){
    try {
      const response = await api.get("/users/me")
      setUserName(response.data.userName)
      setBio(response.data.bio ?? "")
      if (!response.data.bio || response.data.bio.trim() === "") {
        setActiveBio(true);
      } else {
        setActiveBio(false);
      }
    } catch (error) {
      console.log(error)
    } finally {
    setLoading(false);
    }
  }

  async function handleUpdateProfile() {
  try {
    await api.put("/users/me", {
      userName,
      bio,
      profileImgUrl
    });

    setActiveBio(false); 
  } catch (error) {
    console.log(error);
  }
}

  useEffect(() => {
    
    fetchProfile()
  }, [])


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
            {userName && <h2 className="text-2xl font-semibold text-black">{userName}</h2>}

            <button className="bg-black text-white text-sm px-4 py-1.5 rounded-md hover:bg-gray-800 transition hover:cursor-pointer" onClick={() => setActiveBio(true)}>
              Editar Perfil
            </button>
          </div>

          <div className="flex flex-col gap-2 text-sm">
            {!activeBio ? (
              <p className="text-black max-w-md">
                {bio}
              </p>
            ) : (
              <div className="flex items-start gap-2">
                <textarea
                  className="w-full text-black max-w-md h-24 resize-none rounded-lg border border-gray-300 p-3 text-sm focus:outline-none focus:ring-2 focus:ring-black"
                  placeholder="Escreva uma bio..."
                  value={bio}
                  onChange={(e) => setBio(e.target.value)}
                />
                <button
                  className="bg-black text-white px-3 py-2 rounded-md text-sm hover:bg-gray-800 transition hover:cursor-pointer"
                  onClick={handleUpdateProfile}
                >
                  Salvar
                </button>
              </div>
            )}
          </div>
        </div>
      </div>

      <div className="mt-10 border-b border-gray-200"></div>
     <Masonry/>
    </div>
  )
}
