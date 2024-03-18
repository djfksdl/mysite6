package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.AttachVo;

@Repository
public class AttachDao {
	@Autowired
	private SqlSession sqlSession;
	
	public void saveFile(AttachVo attachVo) {
		System.out.println("AttachDao.saveFile()");
		
		sqlSession.insert("attach.insertFile",attachVo);
		System.out.println("DB저장완료");
	}
}
