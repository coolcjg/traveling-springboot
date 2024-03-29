package com.cjg.traveling.dto;

import lombok.Data;

@Data
public class MediaDto {
	
	private Long mediaId;
	
	private String type;
	
	private String status;
	
	private String originalFilePath;
	private String originalFileName;
	private String originalFileClientName;
	
	private String encodingFilePath;
	private String encodingFileName;
	private Long encodingFileSize;
	private String encodingFileUrl;
		
	private String originalFileUrl;
	
	private String thumbnailPath;
	
	private String thumbnailImgUrl;
	
	private int percent;
	
}

