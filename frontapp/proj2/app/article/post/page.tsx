"use client"
import { useState } from "react"
import { useRouter } from "next/navigation";

export default function createArticle() {
  

  return (
    <>
     <h1>| 게시물 등록하기 |</h1>
      <ArticleForm />
    </>
  )
}
function ArticleForm(){
  const router = useRouter();
  const [article, setArticle] = useState({ subject: '', content: '' })

  const handleSubmit = async (e: any) => {
    e.preventDefault();

    const response = await fetch("http://localhost:8090/api/v1/articles", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(article)
    });
    if (response.ok) {
      alert("게시물이 등록 됐습니다.")
      router.push("/article")
    }
  }
  function handleChange(e: any) {
    const {name, value} = e.target;
    setArticle({ ...article, [name]: value })
    console.log({ ...article, [name]: value })

  }
  return(
    <form onSubmit={handleSubmit} >
    <span>제목입력</span>
    <input type="text" name="subject" onChange={handleChange} />
    <br />
    <span>내용입력</span>
    <input type="text" name="content" onChange={handleChange}  />
    <br />
    <button>등록</button>
  </form>
  )
}