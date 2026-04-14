import axios from "axios";
import type { BoardDto, BoardListResponse } from "../types/Board";

const api = axios.create({
  baseURL: "http://localhost:8080/api/board",
});

export const boardApi = {
  getList: (pageNum: number, amount: number) =>
    api.get<BoardListResponse>(`/list?pageNum=${pageNum}&amount=${amount}`),

  getDetail: (boardId: number) => api.get<BoardDto>(`/detail/${boardId}`),

  write: (formData: FormData) =>
    api.post<string>("/write", formData, {
      headers: { "Content-Type": "multipart/form-data" },
    }),

  update: (formData: FormData) =>
    api.put<string>("/update", formData, {
      headers: { "Content-Type": "multipart/form-data" },
    }),

  delete: (boardId: number) => api.delete<string>(`/delete/${boardId}`),

  deleteFile: (fileIdx: number) => api.delete<string>(`/file/${fileIdx}`),
};
