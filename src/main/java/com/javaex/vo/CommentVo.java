package com.javaex.vo;

public class CommentVo {
	//필드
	private int no;
	private String title;
	private String name;
	private String content;
	private int hit;
	private String reg_date;
	private int group_no;
	private int order_no;
	private int depth;
	private int user_no;
	
	//생성자
	public CommentVo() {
		super();
	}
	
	public CommentVo(int no, String title, String name, String content, int hit, String reg_date, int group_no,
			int order_no, int depth, int user_no) {
		super();
		this.no = no;
		this.title = title;
		this.name = name;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.group_no = group_no;
		this.order_no = order_no;
		this.depth = depth;
		this.user_no = user_no;
	}





	//메소드-gs
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	


	public int getGroup_no() {
		return group_no;
	}

	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	


	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	
	//메소드-일반
	@Override
	public String toString() {
		return "CommentVo [no=" + no + ", title=" + title + ", name=" + name + ", content=" + content + ", hit=" + hit
				+ ", reg_date=" + reg_date + ", group_no=" + group_no + ", order_no=" + order_no + ", depth=" + depth
				+ ", user_no=" + user_no + "]";
	}





	

}
