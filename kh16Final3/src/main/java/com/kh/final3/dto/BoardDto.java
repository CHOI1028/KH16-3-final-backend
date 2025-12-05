package com.kh.final3.dto;

import java.sql.Timestamp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class BoardDto {
	private long boardNo;
    private String boardTitle, boardWriter, boardContent;
    private Timestamp boardWtime, boardEtime;
    private int boardRead;
    private String boardType;
}
