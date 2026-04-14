import { useEffect, useState } from "react";
import {
  Link,
  useNavigate,
  useParams,
  useSearchParams,
} from "react-router-dom";
import { boardApi } from "../api/boardApi";
import type { BoardDto } from "../types/Board";

export default function BoardDetailPage() {
  const { boardId } = useParams();
  const [board, setBoard] = useState<BoardDto | null>(null);
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const pageNum = Number(searchParams.get("pageNum") || 1);

  useEffect(() => {
    if (boardId) {
      boardApi.getDetail(Number(boardId)).then((res) => {
        setBoard(res.data);
      });
    }
  }, [boardId]);

  const onDelete = async () => {
    if (!boardId) return;
    if (!confirm("삭제할까?")) return;

    await boardApi.delete(Number(boardId));
    navigate(`/?pageNum=${pageNum}`);
  };

  const onDeleteFile = async (fileIdx: number) => {
    if (!boardId) return;
    if (!confirm("파일 삭제할까?")) return;

    await boardApi.deleteFile(fileIdx);
    const res = await boardApi.getDetail(Number(boardId));
    setBoard(res.data);
  };

  if (!board) return <div style={{ padding: "20px" }}>로딩중...</div>;

  return (
    <div style={{ padding: "20px" }}>
      <h2>게시글 상세</h2>

      <p>글번호: {board.boardId}</p>
      <p>조회수: {board.hitCnt}</p>
      <p>작성자: {board.creatorId}</p>
      <p>작성일: {board.createdDatetime}</p>
      <p>제목: {board.title}</p>
      <p>내용: {board.contents}</p>

      <h3>첨부파일</h3>
      <ul>
        {board.fileList && board.fileList.length > 0 ? (
          board.fileList.map((file) => (
            <li key={file.fileIdx}>
              <a
                href={`http://localhost:8080/upload/${file.storedFilePath.split(/[\\/]/).pop()}`}
                target="_blank"
                rel="noreferrer"
              >
                {file.originalFileName}
              </a>
              <button
                type="button"
                onClick={() => onDeleteFile(file.fileIdx)}
                style={{ marginLeft: "10px" }}
              >
                파일삭제
              </button>
            </li>
          ))
        ) : (
          <li>첨부파일 없음</li>
        )}
      </ul>

      <div style={{ marginTop: "20px" }}>
        <Link to={`/?pageNum=${pageNum}`}>목록으로</Link>
        <button type="button" onClick={onDelete} style={{ marginLeft: "10px" }}>
          삭제
        </button>
      </div>
    </div>
  );
}
