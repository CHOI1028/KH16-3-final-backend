package com.kh.final3.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kh.final3.dto.BoardDto;
import com.kh.final3.service.BoardService; 
import com.kh.final3.error.TargetNotfoundException; 

@CrossOrigin
@RestController
@RequestMapping("/board")
public class BoardRestController {
    
	@Autowired
	private BoardService boardService; 
	
	@PostMapping("/")
	public void insert(@RequestBody BoardDto boardDto) {
        String loginLevel = "MEMBER";
		boardService.insert(boardDto, loginLevel);
	}
	
	@GetMapping("/")
	public List<BoardDto> list() {
		return boardService.selectList(); 
	}
	
	@GetMapping("/{boardNo}")
	public BoardDto detail(@PathVariable long boardNo) {
		BoardDto boardDto = boardService.selectOne(boardNo);
		return boardDto; 
	}
	
	@DeleteMapping("/{boardNo}")
	public void delete(@PathVariable long boardNo
//								@RequestAttribute TokenVO tokenVO
			) {
        String loginId = "testuser1"; // 실제 로그인 ID
        String loginLevel = "일반회원"; // 실제 로그인 레벨
		boardService.delete(boardNo, loginId, loginLevel);
	}
	
	@PatchMapping("/{boardNo}")
	public void edit(@PathVariable long boardNo,
								@RequestBody BoardDto boardDto
//								@RequestAttribute TokenVO tokenVO
								) {
        boardDto.setBoardNo(boardNo);
        String loginId = "tester"; // 임시 
        String loginLevel = "default";//임시
		boardService.update(boardDto, loginId, loginLevel); 
	}
}