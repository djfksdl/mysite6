package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	//필드
	@Autowired
	private SqlSession sqlSession;
	//전체리스트
	public List<BoardVo> boardList() {
		System.out.println("BoardDao.boardList");
		
		List<BoardVo> bList = sqlSession.selectList("board.selectList");
		
		return bList;
	}
	
	//삭제
	public void boardDelete(int no) {
		System.out.println("BoardDao.boardDelete");
		
		sqlSession.delete("board.delete", no);
	}
	
	//글쓰기
	public void boardWrite(BoardVo boardVo) {
		System.out.println("BoardDao.boardWrite");
		
		sqlSession.insert("board.insert", boardVo);
	}
	
	//게시글 클릭
	public BoardVo boardRead(int no) {
		System.out.println("BoardDao.boardRead");
		
		BoardVo boardVo= sqlSession.selectOne("board.selectByNo", no);
		
		return boardVo;
	}
	
	//수정
	public void boardUpdate(BoardVo boardVo) {
		System.out.println("BoardDao.boardUpdate");
		
		sqlSession.update("board.update", boardVo);
	}
}
