'use client'

import { useEffect, useState } from 'react'
import { api } from '../services/apiService'

export default function Feed() {
  const [posts, setPosts] = useState<any[]>([])
  const [loading, setLoading] = useState(true)

  async function fetchPosts() {
    try {
      const response = await api.get('/posts/user/me')
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
    <div className="columns-2 sm:columns-3 lg:columns-4 gap-4 py-10">
      {posts.map(post => (
        <div key={post.id} className="mb-4 break-inside-avoid">
          <img
            src={`http://localhost:8080${post.postImgUrl}`}
            alt="Post"
            className="w-full rounded-lg object-cover"
          />
        </div>
      ))}
    </div>
  )
}
