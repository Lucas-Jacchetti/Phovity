'use client'

import { useEffect, useState } from 'react'
import { api } from '../services/apiService'
import OpenPost from './OpenPost'



export default function Feed() {
  const [posts, setPosts] = useState<any[]>([])
  const [loading, setLoading] = useState(true)
  const [selectedPost, setSelectedPost] = useState<any | null>(null)

  async function fetchPosts() {
    try {
      const response = await api.get('/posts')
      setPosts(response.data)
    } catch (error) {
      console.error(error)
    } finally {
      setLoading(false)
    }
  }


  useEffect(() => {
    fetchPosts()
  }, [])

  if (loading) {
    return <p className="text-center py-10">Carregando imagens...</p>
  }

  return (
    
    <div className="columns-2 sm:columns-3 lg:columns-4 gap-5 py-10">
      {posts.map(post => (
        <div key={post.id} className="mb-4 break-inside-avoid">
          <img
            onClick={() => setSelectedPost(post)}
            src={`http://localhost:8080${post.postImgUrl}`}
            alt="Post"
            className="w-full rounded-lg object-cover hover:cursor-pointer"
          />
        </div>
      ))}
      {selectedPost && <OpenPost post={selectedPost} onClose={() => setSelectedPost(null)}/>}
    </div>  
  )
}
