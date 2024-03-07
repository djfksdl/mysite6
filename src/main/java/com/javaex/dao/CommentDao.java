package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CommentVo;

@Repository
public class CommentDao {
	//필드
	@Autowired
	private SqlSession sqlSession;
	//전체 리스트
	public List<CommentVo> commentList() {
		System.out.println("CommentDao.commentList");
		
		List<CommentVo> cList= sqlSession.selectList("comment.selectList");
		
		return cList;
	}
	//글쓰기 등록- 선 업뎃
	public void commentUpdate(CommentVo commentVo) {
		System.out.println("CommentDao.commentUpdate");
		
		sqlSession.update("comment.update", commentVo);
	}
	//글쓰기 등록- 후 등록
	public void commentInsert(CommentVo commentVo) {
		System.out.println("CommentDao.commentInsert");
		
		sqlSession.insert("comment.insert", commentVo);
	}
}
