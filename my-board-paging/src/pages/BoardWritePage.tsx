import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { boardApi } from "../api/boardApi";

export default function BoardWritePage() {
  const [title, setTitle] = useState("");
  const [contents, setContents] = useState("");
  const [creatorId, setCreatorId] = useState("");
  const [files, setFiles] = useState<FileList | null>(null);
  const navigate = useNavigate();

  const onSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append("title", title);
    formData.append("contents", contents);
    formData.append("creatorId", creatorId);

    if (files) {
      Array.from(files).forEach((file) => formData.append("files", file));
    }

    await boardApi.write(formData);
    navigate("/");
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>게시글 등록</h2>

      <form onSubmit={onSubmit}>
        <p>
          제목:
          <input
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            style={{ marginLeft: "10px", width: "300px" }}
          />
        </p>

        <p>
          작성자:
          <input
            value={creatorId}
            onChange={(e) => setCreatorId(e.target.value)}
            style={{ marginLeft: "10px", width: "300px" }}
          />
        </p>

        <p>
          내용:
          <br />
          <textarea
            value={contents}
            onChange={(e) => setContents(e.target.value)}
            style={{ width: "500px", height: "200px" }}
          />
        </p>

        <p>
          첨부파일:
          <input
            type="file"
            multiple
            onChange={(e) => setFiles(e.target.files)}
          />
        </p>

        <button type="submit">저장</button>
      </form>
    </div>
  );
}
