'use client'

import { useEffect, useState } from "react"
import { Sidebar } from ".././components/Sidebar"
import { api } from ".././services/apiService";
import ProfileMasonry from "@/app/components/ProfileMasonry";


export default function ProfileHeader() {
  const [userName, setUserName] = useState("");
  const [bio, setBio] = useState("");
  const [activeBio, setActiveBio] = useState(false);
  const [profileImgUrl, setProfileImgUrl] = useState(null);
  const [image, setImage] = useState<File | null>(null);
  const [preview, setPreview] = useState<string | null>(null);
  const [loading, setLoading] = useState(true);

  async function fetchProfile(){
    try {
      const response = await api.get("/users/me")
      setUserName(response.data.userName)

      setBio(response.data.bio ?? "")

      setPreview(
        response.data.profileImgUrl ?? null
      );

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

  async function handleUpload(file: File) {
    try {

      const cloudName = "dc8naqlov";
      const uploadPreset = "phovity_upload";

      const formData = new FormData();
      formData.append("file", file);
      formData.append("upload_preset", uploadPreset);

      const uploadResponse = await fetch(
        `https://api.cloudinary.com/v1_1/${cloudName}/image/upload`,
        {
          method: "POST",
          body: formData,
        }
      );

      const data = await uploadResponse.json();

      const imageUrl = data.secure_url;

      setPreview(imageUrl);
      setProfileImgUrl(imageUrl);

      await api.put("/users/me", {
        profileImgUrl: imageUrl
      });

    } catch (error) {
      console.log(error);
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
    <div>
      <div className="w-full max-w-5xl mx-auto px-8 py-10">
        <Sidebar />
        <div className="flex gap-10 items-start">
          
          <div className="shrink-0 relative">
            <label htmlFor="profile-upload" className="cursor-pointer">
              <div className="w-32 h-32 rounded-full overflow-hidden border-4 border-white dark:border-neutral-700 shadow-md hover:opacity-80 transition">
                <img
                  src={
                    preview || "https://icones.pro/wp-content/uploads/2021/02/icono-de-camara-gris.png"
                  }
                  alt="Profile"
                  className="w-full h-full object-cover"
                />
              </div>
            </label>

            <input
              id="profile-upload"
              type="file"
              accept="image/*"
              className="hidden"
              onChange={(e) => {
                if (e.target.files && e.target.files[0]) {
                  const file = e.target.files[0];
                  setImage(file);
                  setPreview(URL.createObjectURL(file));  
                  handleUpload(file);
                }
              }}
            />
          </div>

          <div className="flex flex-col gap-4 flex-1">
            
            <div className="flex items-center gap-6">
              {userName && <h2 className="text-2xl font-semibold text-black dark:text-white" >{userName}</h2>}

              <button className="bg-black text-white dark:bg-neutral-200  dark:text-black text-sm px-4 py-1.5 rounded-md hover:bg-gray-800 transition hover:cursor-pointer" onClick={() => setActiveBio(true)}>
                Editar Perfil
              </button>
            </div>

            <div className="flex flex-col gap-2 text-sm">
              {!activeBio ? (
                <p className="text-black dark:text-white max-w-md">
                  {bio}
                </p>
              ) : (
                <div className="flex items-start gap-2">
                  <textarea
                    className="w-full text-black dark:text-white dark:bg-neutral-800 max-w-md h-24 resize-none rounded-lg border border-gray-300 p-3 text-sm focus:outline-none focus:ring-2 focus:ring-black"
                    placeholder="Escreva uma bio..."
                    value={bio}
                    onChange={(e) => setBio(e.target.value)}
                  />
                  <button
                    className="bg-black text-white dark:bg-neutral-200  dark:text-black px-3 py-2 rounded-md text-sm hover:bg-gray-800 transition hover:cursor-pointer"
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
      
      </div>
      <div className="ml-[4%] mr-[2%]">
        <ProfileMasonry/>
      </div>
    </div>
  )
}
