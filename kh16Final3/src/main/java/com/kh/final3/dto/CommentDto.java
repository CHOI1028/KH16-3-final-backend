package com.kh.final3.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CommentDto {
	
	// 댓글 정보
	private long commentNo; 			// 댓글 번호 (PK)
	private long commentBoardNo; 		// 댓글이 달린 게시글 번호 (FK to BOARD)
	private String commentWriter; 		// 댓글 작성자 ID (FK to MEMBER)
	private String commentContent; 		// 댓글 내용
	private Date commentTime; 			// 댓글 작성 시각
    private Date commentEditDate;       // 댓글 수정 시각 (추가됨)
	private String commentStatus;       // 댓글 상태 ('y', 'n') (추가됨)
	
	// 대댓글/계층형 구현을 위한 필드(안함)
//	private Long commentParent; 		// 상위 댓글 번호 (대댓글인 경우)
//	private Integer commentGroup; 		// 댓글 그룹 (최상위 댓글의 번호)
//	private Integer commentDepth; 		// 댓글 깊이 (0: 최상위 댓글, 1 이상: 대댓글)
	
    private String writerNickname;      // 작성자 닉네임
}