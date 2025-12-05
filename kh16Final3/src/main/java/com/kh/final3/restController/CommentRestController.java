//package com.kh.final3.restController;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.ResponseEntity;
//
//import com.kh.final3.dto.CommentDto;
//import com.kh.final3.service.CommentService;
//import com.kh.final3.error.TargetNotfoundException; 
//import com.kh.final3.error.UnauthorizationException; 
//import com.kh.final3.error.NeedPermissionException; 
//
//@CrossOrigin
//@RestController
//@RequestMapping("/rest/comment") // REST API 관례에 따라 경로를 /rest/comment로 설정
//public class CommentRestController {
//    
//	@Autowired
//	private CommentService commentService; 
//	
//	/**
//	 * 1. 댓글 등록 (POST)
//	 */
//	@PostMapping("/")
//	public CommentDto insert(@RequestBody CommentDto commentDto
////			@RequestAttribute TokenVO tokenVO
//			) {
//        
//        String loginId = "testuser1"; // 임시 로그인 ID
//        
//        if (loginId == null || loginId.isEmpty()) {
//            throw new UnauthorizationException("로그인 후 댓글 작성이 가능합니다.");
//        }
//        
//		return commentService.insert(commentDto, loginId);
//	}
//	
//	/**
//	 * 2. 댓글 목록 조회 (GET)
//	 * - 특정 게시글의 모든 댓글 조회
//	 */
//	@GetMapping("/{boardNo}")
//	public List<CommentDto> list(@PathVariable long boardNo) {
//		return commentService.selectList(boardNo); 
//	}
//	
//	/**
//	 * 3. 댓글 수정 (PATCH)
//	 */
//	@PatchMapping("/{commentNo}")
//	public ResponseEntity<?> edit(
//				@PathVariable long commentNo,
//				@RequestBody CommentDto commentDto
////				@RequestAttribute TokenVO tokenVO
//				) {
//        
//        commentDto.setCommentNo(commentNo); // PathVariable을 DTO에 설정
//        String loginId = "tester"; // 임시 로그인 ID
//        String loginLevel = "default"; // 임시 로그인 레벨
//        
//		commentService.update(commentDto, loginId, loginLevel); 
//        
//        return ResponseEntity.ok().build(); // 200 OK 반환
//	}
//	
//	/**
//	 * 4. 댓글 삭제 (DELETE)
//	 * - softDelete 처리 (실제 DB에서 삭제하는 대신 상태만 변경)
//	 */
//	@DeleteMapping("/{commentNo}")
//	public ResponseEntity<?> delete(
//				@PathVariable long commentNo
////				@RequestAttribute TokenVO tokenVO
//			) {
//        
//        String loginId = "testuser1"; // 임시 로그인 ID
//        String loginLevel = "일반회원"; // 임시 로그인 레벨
//        
//		commentService.softDelete(commentNo, loginId, loginLevel);
//        
//        return ResponseEntity.ok().build(); // 200 OK 반환
//	}
//    
//    // 예외 처리는 BoardRestController와 마찬가지로 별도로 작성해야 합니다.
//    /*
//	@ExceptionHandler(UnauthorizationException.class)
//	public ResponseEntity<String> handleUnauthorization(UnauthorizationException e) {
//		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
//	}
//	
//    @ExceptionHandler(NeedPermissionException.class)
//	public ResponseEntity<String> handleNeedPermission(NeedPermissionException e) {
//		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
//	}
//
//	@ExceptionHandler(TargetNotfoundException.class)
//	public ResponseEntity<String> handleTargetNotFound(TargetNotfoundException e) {
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//	}
//    */
//}