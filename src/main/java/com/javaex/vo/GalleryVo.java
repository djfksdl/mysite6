package com.javaex.vo;

import org.springframework.web.multipart.MultipartFile;

public class GalleryVo {
	//필드
	private int no;
	private int user_no;
	private String content;
	private String filePath;
	private String orgName;
	private String saveName;
	private long fileSize;
	private String name;
	private MultipartFile file;
	
	//생성자
	public GalleryVo() {
		super();
	}

	
	public GalleryVo(int user_no, String content, String filePath, String orgName, String saveName, long fileSize) {
		super();
		this.user_no = user_no;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}


	public GalleryVo(int no, int user_no, String content, String filePath, String orgName, String saveName,
			long fileSize, String name, MultipartFile file) {
		super();
		this.no = no;
		this.user_no = user_no;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
		this.name = name;
		this.file = file;
	}



	//메소드-gs
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	
	//메소드-일반
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", user_no=" + user_no + ", content=" + content + ", filePath=" + filePath
				+ ", orgName=" + orgName + ", saveName=" + saveName + ", fileSize=" + fileSize + ", name=" + name
				+ ", file=" + file + "]";
	}
	


	
}
