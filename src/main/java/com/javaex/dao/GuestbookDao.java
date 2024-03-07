package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	//필드
	@Autowired
	private SqlSession sqlSession;
	
	//전체 리스트-메인
	public List<GuestbookVo> guestList() {
		System.out.println("GuestbookDao.guestList");
		
		List<GuestbookVo> gusetList =sqlSession.selectList("guestbook.selectList");
		
		return gusetList ;
	}
	
	//글 등록
	public void guestWrite(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao.guestWrite");
		
		sqlSession.insert("guestbook.insert", guestbookVo);
	}
	
	//삭제
	public void guestDelete(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao.guestDelete");
		
		sqlSession.delete("guestbook.delete", guestbookVo);
	}
}
