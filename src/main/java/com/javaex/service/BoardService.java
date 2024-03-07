package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	//필드
	@Autowired
	private BoardDao boardDao;
	//전체리스트
	public List<BoardVo> exeList() {
		System.out.println("BoardService.exeList");
		
		List<BoardVo> bList = boardDao.boardList();
		
		return bList;
	}
	
	//삭제
	public void exeDelete(int no) {
		System.out.println("BoardService.exeDelete");
		
		boardDao.boardDelete(no);
		
	}
	//글쓰기
	public void exeWrite(BoardVo boardVo) {
		System.out.println("BoardService.exeWrite");
		
		boardDao.boardWrite(boardVo);
		
	}
	//게시글 클릭
	public BoardVo exeRead(int no) {
		System.out.println("BoardService.exeRead");
		
		BoardVo boardVo= boardDao.boardRead(no);
		
		return boardVo;
	}
	//수정
	public void exeUpdate(BoardVo boardVo) {
		System.out.println("BoardService.exeUpdate");
		
		boardDao.boardUpdate(boardVo);
	}
}
