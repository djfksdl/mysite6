package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service	
public class GuestbookService {
	//필드
	@Autowired
	private GuestbookDao guestbookDao;
	//메소드
	//전체 리스트-메인
	public List<GuestbookVo> exeList() {
		System.out.println("GuestbookService.exeList");
		
		List<GuestbookVo> guestList = guestbookDao.guestList();
		
		return guestList;
	}
	
	//글 등록
	public void exeWrite(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService.exeWrite");
		
		guestbookDao.guestWrite(guestbookVo);
		
	}
	
	//삭제
	public int exeDelete(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService.exeDelete");
		
		int count = guestbookDao.guestDelete(guestbookVo);
		return count;
		//int no= guestbookDao.guestDelete(guestbookVo);
		//return no;
	}
	
	//ajax등록
	public GuestbookVo exeAddandGuest(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService.exeAddandGuest()");
		
		//저장
		System.out.println("전"+ guestbookVo);//no비어있음
		int no = guestbookDao.insertSelectKey(guestbookVo);
		System.out.println("후"+guestbookVo);//no있음
		
		//1명데이터 가져오기
		GuestbookVo gVo= guestbookDao.guestbookSelectOne(guestbookVo.getNo());
		return gVo;
	}
}
