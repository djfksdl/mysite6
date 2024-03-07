package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CommentDao;
import com.javaex.vo.CommentVo;

@Service	
public class CommentService {
	//필드
	@Autowired
	private CommentDao commentDao;
	//전체 리스트
	public List<CommentVo> exeList() {
		System.out.println("CommentService.exeList");
		
		List<CommentVo> cList = commentDao.commentList();
		
		return cList;
	}
	
	//댓글등록
	public void exeInsert(CommentVo commentVo) {
		System.out.println("CommentService.exeInsert");
		
		//1) order_no를 이전꺼 다 +1씩해놓기
		commentDao.commentUpdate(commentVo);
		//2) 글 등록하기
		commentDao.commentInsert(commentVo);
	}
}
