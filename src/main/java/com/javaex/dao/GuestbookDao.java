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
		
		
		return count;//Service에서도 guestbookVo을 넘겨받아서 no를 이미 꺼내쓸 수 있기떄문에 굳이 getNo를 넘겨주지 않아도됨. count만 넘겨도 됨.
	}
	
	//데이터 1개 가져오기 - no 1명 데이터 가져오기
	public GuestbookVo guestbookSelectOne(int no) {
		System.out.println("GuestbookDao.guestbookSelectOne");
		//guestbookVo.getNo();//no를 가져와서 하나의 리스트틀 가져온다. 근데 하나의 Dao에 두가지가 같이 있으면 안되서 메소드를 나눠준다.
		GuestbookVo guestbookVo =sqlSession.selectOne("guestbook.selectOne",no);
		System.out.println(guestbookVo);
		
		return guestbookVo;
	}
}
