package com.cjg.traveling.dto;

import lombok.Data;

@Data
public class OpinionDto {
	
	private Long alarmId;
	private Long boardId;
	private String userId;
	private String regDate; 
	private String type;
	private String value;

}
