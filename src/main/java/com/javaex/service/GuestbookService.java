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
	public void exeDelete(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService.exeDelete");
		
		guestbookDao.guestDelete(guestbookVo);
		
	}
}
