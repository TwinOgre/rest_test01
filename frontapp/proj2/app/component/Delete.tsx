'use client'
export default function deleteArticle(id: string) {
  const handleDelete = async (id:string) => {

    const response = await fetch(`http://localhost:8090/api/v1/articles/${id}`, {
      method: 'DELETE',
    });
    if (response.ok) {
      alert("게시물이 삭제 됐습니다.")
    } else {
      alert("게시물 삭제 실패했습니다.")
    }
  }
  handleDelete(id);
}