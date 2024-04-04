"use client"
import { useEffect, useState } from "react"
interface articlesInterface {
    id: number,
    createdDate: string,
    modifiedDate: string,
    subject: string,
    content: string
}

export default function Article() {

    const [articles, setArticles] = useState<articlesInterface[]>([]);

    useEffect(() => {
        const fetchData = async () => {
            const result = await fetch("http://localhost:8090/api/v1/articles")
            const datos = await result.json();
            setArticles(datos.data.articleList)
        }

        fetchData();
    }, []);

    return (
        <>
            <h1>||게시글 목록||</h1>
            <ul>
                {articles.map(article => (<li key={article.id}>|{article.id}|번 |{article.subject}|{article.content}|{article.createdDate}</li>))}
            </ul>
        </>
    )
}