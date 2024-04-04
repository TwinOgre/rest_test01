"use client"
import { useState } from "react"
import { useParams, useRouter } from "next/navigation";

export default function modfiyArticle() {
  const paraData = useParams();
  return (
    <>
     <h1>| {paraData.id} 번 게시물 수정하기 |</h1>
      <ArticleModifyForm />
    </>
  )
}
function ArticleModifyForm(){
  const router = useRouter();
  const [article, setArticle] = useState({ subject: '', content: '' })
  const paramData = useParams();
  const handleSubmit = async (e: any) => {
    e.preventDefault();

    const response = await fetch(`http://localhost:8090/api/v1/articles/${paramData.id}`, {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(article)
    });
    if (response.ok) {
      alert("게시물이 수정 됐습니다.")
      router.push(`/article/${paramData.id}`)
    }else{
      alert("게시물 수정에 실패했습니다.")
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
    <input type="text" name="subject" onChange={handleChange} className="inputer" />
    <br />
    <span>내용입력</span>
    <input type="text" name="content" onChange={handleChange} className="inputer"  />
    <br />
    <button>수정</button>
  </form>
  )
}