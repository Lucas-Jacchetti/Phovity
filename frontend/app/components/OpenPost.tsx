'use client'

import { useState, useEffect } from "react"
import { api } from "../services/apiService"
import { Client } from "@stomp/stompjs"
import SockJS from "sockjs-client"

type OpenPostProps = {
  post: any
  onClose: () => void
}

export default function OpenPost({post, onClose} : OpenPostProps) {
    const [comments, setComments] = useState<any[]>([])
    const [comment, setComment] = useState("");
    const [like, setLike] = useState(false);
    const [likeCount, setLikecount] = useState(0);
    

    const handleLike = async (event: { preventDefault: () => void }) => {
        event.preventDefault()
        const newLike = {
            postId: post.id,
        }
        try {
            const respose = await api.post("/likes", newLike)
            setLikecount(respose.data.likeCount)
            setLike(respose.data.liked)
        } catch (error) {
            
        }
    }

    const handleComment = async (event: { preventDefault: () => void }) => {
        event.preventDefault()
        const newComment = {
          text: comment,
          postId: post.id,
        }
        try {
            await api.post("/comments", newComment);
            const response = await api.get(`/comments/post/${post.id}`)
            setComments(response.data)
            setComment("")
        } catch (error) {
            console.log(error)
        }
    }

    useEffect(() => {
      const socket = new SockJS("http://localhost:8080/ws")

      const client = new Client({
        webSocketFactory: () => socket,
        onConnect: () => {
          client.subscribe(`/topic/likes/${post.id}`, (message) => {
            const data = JSON.parse(message.body)
            setLikecount(data.likeCount)
            setLike(data.liked)
          })
        }
      })

      client.activate()

      return () => {
        client.deactivate()
      }
    }, [post.id])

    useEffect(() => {
      async function fetchComments() {
        const response = await api.get(`/comments/post/${post.id}`)
        setComments(response.data)
      }

      fetchComments()
    }, [post.id])

    useEffect(() => {
      async function fetchPostData() {
        const response = await api.get(`/posts/${post.id}`)
        setLikecount(response.data.likeCount)
        setLike(response.data.likedByMe)
      }

      fetchPostData()
    }, [post.id])

    useEffect(() => {
      console.log("Post aberto:", post.likeCount)
    }, [post])
    
  return (
    <div className="fixed inset-0 bg-black/70 flex items-center justify-center z-50 text-black">

      <div className="bg-white w-[95%] max-w-5xl h-[85%] rounded-2xl flex overflow-hidden shadow-2xl ">

        <div className="w-1/2 bg-gray-100 flex flex-col items-center justify-center">
          <img
            src={`http://localhost:8080${post.postImgUrl}`}
            alt="Post"
            className="max-h-full object-contain p-3"
          />
          <div className="mt-5">{post.description || ''}</div>
        </div>

        <div className="w-1/2 flex flex-col">

          <div className="p-5 border-b border-b-gray-500 flex justify-between items-center">
            <div className="flex items-center gap-3">
              <div className="w-9 h-9 bg-gray-300 rounded-full"></div>
              <span className="font-semibold">{post.author?.username || 'username'}</span>
            </div>

            <button onClick={onClose} className="text-gray-500 hover:text-black text-xl hover:cursor-pointer">
              ✕
            </button>
          </div>

          <div className="flex-1 overflow-y-auto p-5 space-y-4">
            {comments.length === 0 ? (
                <p className="text-sm text-gray-500">
                Nenhum comentário ainda.
                </p>
            ) : (
                comments.map((c) => (
                <div key={c.id} className="text-sm">
                    <span className="font-semibold">{c.author?.username}</span>{" "}
                    {c.text}
                </div>
                ))
            )}
           </div>

          <div className="border-t border-t-gray-500 p-5 space-y-4">

            <div className="flex items-center gap-2 text-xl">
              <button onClick={handleLike} className="hover:scale-110 transition">♥️</button>
              <div className="text-sm font-semibold">{likeCount}</div>
              <div className="ml-5 text-[15px]">{post.tag && '#' + post.tag}</div>
            </div>

            <div className="flex gap-3">
              <input
                value={comment} onChange={(e) => setComment(e.target.value)}
                type="text"
                placeholder="Adicione um comentário..."
                className="flex-1 border rounded-lg px-4 py-2 text-sm outline-none"
              />
              <button className="text-black bg-blue-400 font-semibold rounded-lg hover:cursor-pointer" onClick={handleComment}>
                <p className="p-3">Publicar</p>
              </button>
            </div>

          </div>

        </div>
      </div>
    </div>
  )
}
