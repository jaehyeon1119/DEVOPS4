package org.cloud.dto;

import lombok.Data;

@Data
public class FileDto {

	private int fileIdx;
	private int boardId;
	private String originalFileName;
	private String storedFilePath;
	private long fileSize;
	private String creatorId;
	public int getFileIdx() {
		return fileIdx;
	}
	public void setFileIdx(int fileIdx) {
		this.fileIdx = fileIdx;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getStoredFilePath() {
		return storedFilePath;
	}
	public void setStoredFilePath(String storedFilePath) {
		this.storedFilePath = storedFilePath;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	} 
	
}
