import { BrowserRouter, Route, Routes } from "react-router-dom";
import BoardListPage from "./pages/BoardListPage";
import BoardDetailPage from "./pages/BoardDetailPage";
import BoardWritePage from "./pages/BoardWritePage";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<BoardListPage />} />
        <Route path="/write" element={<BoardWritePage />} />
        <Route path="/detail/:boardId" element={<BoardDetailPage />} />
      </Routes>
    </BrowserRouter>
  );
}
