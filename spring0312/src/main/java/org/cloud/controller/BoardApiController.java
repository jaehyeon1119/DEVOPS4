package org.cloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cloud.dto.BoardDto;
import org.cloud.dto.Criteria;
import org.cloud.dto.PageResponse;
import org.cloud.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/board")
public class BoardApiController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> openBoardList(@ModelAttribute Criteria cri) throws Exception {
		if (cri.getPageNum() <= 0) {
			cri.setPageNum(1);
		}
		if (cri.getAmount() <= 0) {
			cri.setAmount(10);
		}

		List<BoardDto> list = boardService.selectBoardListPaging(cri);
		int total = boardService.selectBoardTotalCount();

		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("pageMaker", new PageResponse(cri, total));

		return ResponseEntity.ok(result);
	}

	@PostMapping("/write")
	public ResponseEntity<String> insertBoard(BoardDto board, MultipartHttpServletRequest request) throws Exception {
		boardService.insertBoard(board, request);
		return ResponseEntity.ok("write success");
	}

	@GetMapping("/detail/{boardId}")
	public ResponseEntity<BoardDto> openBoardDetail(@PathVariable("boardId") int boardId) throws Exception {
		BoardDto board = boardService.selectDetail(boardId);
		return ResponseEntity.ok(board);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateBoard(BoardDto board, MultipartHttpServletRequest request) throws Exception {
		boardService.updateBoard(board, request);
		return ResponseEntity.ok("update success");
	}

	@DeleteMapping("/delete/{boardId}")
	public ResponseEntity<String> deleteBoard(@PathVariable("boardId") int boardId) throws Exception {
		boardService.deleteBoard(boardId);
		return ResponseEntity.ok("delete success");
	}

	@DeleteMapping("/file/{fileIdx}")
	public ResponseEntity<String> deleteFile(@PathVariable("fileIdx") int fileIdx) throws Exception {
		boardService.deleteFile(fileIdx);
		return ResponseEntity.ok("file delete success");
	}
}