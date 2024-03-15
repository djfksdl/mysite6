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
	public int guestDelete(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao.guestDelete()");
		
		int count = sqlSession.delete("guestbook.delete", guestbookVo);

		//return guestbookVo.getNo();
		return count;
	}
	
	//ajax등록
	public int insertSelectKey(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao.insertSelectKey");
		
		System.out.println(guestbookVo);//no비어있음
		int count = sqlSession.insert("guestbook.insertSelectKey",guestbookVo);
		System.out.println(guestbookVo);//no 있음
		
		
		return count;
	}
	
	//데이터 1개 가져오기 - no 1명 데이터 가져오기
	public GuestbookVo guestbookSelectOne(int no) {
		System.out.println("GuestbookDao.guestbookSelectOne");
		//guestbookVo.getNo();//no를 가져와서 하나 가져온다.- what?reg_date없기 때문에 이걸로 조회해서 reg_date가져와야함.
		GuestbookVo guestbookVo =sqlSession.selectOne("guestbook.selectOne",no);
		System.out.println(guestbookVo);
		
		return guestbookVo;
	}
}
