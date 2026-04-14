import { useEffect, useState } from "react";
import { Link, useSearchParams } from "react-router-dom";
import { boardApi } from "../api/boardApi";
import type { BoardDto, PageResponse } from "../types/Board";

export default function BoardListPage() {
  const [list, setList] = useState<BoardDto[]>([]);
  const [pageMaker, setPageMaker] = useState<PageResponse | null>(null);
  const [searchParams] = useSearchParams();

  const pageNum = Number(searchParams.get("pageNum") || 1);
  const amount = 10;

  useEffect(() => {
    boardApi.getList(pageNum, amount).then((res) => {
      setList(res.data.list);
      setPageMaker(res.data.pageMaker);
    });
  }, [pageNum]);

  return (
    <div style={{ padding: "20px" }}>
      <h2>게시글 목록</h2>

      <table
        border={1}
        cellPadding={10}
        style={{ borderCollapse: "collapse", width: "100%" }}
      >
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>조회수</th>
            <th>작성일</th>
          </tr>
        </thead>
        <tbody>
          {list.length > 0 ? (
            list.map((board) => (
              <tr key={board.boardId}>
                <td>{board.boardId}</td>
                <td>
                  <Link to={`/detail/${board.boardId}?pageNum=${pageNum}`}>
                    {board.title}
                  </Link>
                </td>
                <td>{board.hitCnt}</td>
                <td>{board.createdDatetime}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan={4}>조회된 결과가 없습니다.</td>
            </tr>
          )}
        </tbody>
      </table>

      <div style={{ marginTop: "20px" }}>
        {pageMaker?.prev && (
          <Link to={`/?pageNum=${pageMaker.startPage - 1}`}>이전</Link>
        )}

        {pageMaker &&
          Array.from(
            { length: pageMaker.endPage - pageMaker.startPage + 1 },
            (_, i) => pageMaker.startPage + i,
          ).map((num) => (
            <Link key={num} to={`/?pageNum=${num}`} style={{ margin: "0 8px" }}>
              {num}
            </Link>
          ))}

        {pageMaker?.next && (
          <Link to={`/?pageNum=${pageMaker.endPage + 1}`}>다음</Link>
        )}
      </div>

      <div style={{ marginTop: "20px" }}>
        <Link to="/write">글쓰기</Link>
      </div>
    </div>
  );
}
