"use client"
import { useParams } from "next/navigation";
import { useEffect, useState } from "react"
type articleInterface  = {
    id: number,
    createdDate: string,
    modifiedDate: string,
    subject: string,
    content: string
}
export default function Article (){
    const [article, setArticle] = useState<articleInterface>();
    const paramData = useParams();
    useEffect(() => {
        const fetchData = async () => {
            const result = await fetch(`http://localhost:8090/api/v1/articles/${paramData.id}`)
            const datos = await result.json();
            setArticle(datos.data.article)
        }

        fetchData();
    }, []);
    return(
        <>
        <h1>|| {article?.id} 번 게시글 ||</h1>
            <div>
                <span>{article?.id} || {article?.subject} || {article?.content} || {article?.createdDate} || {article?.modifiedDate}</span>
            </div>
        </>
    )
}