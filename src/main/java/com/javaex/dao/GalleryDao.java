package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	@Autowired
	private SqlSession sqlSession;
	
	//리스트 불러오기
	public List<GalleryVo> galleryList() {
		
		System.out.println("GalleryDao.galleryList");
		
		//디비에 접근해서 리스트 불러오기
		List<GalleryVo> galleryList= sqlSession.selectList("gallery.selectList");
		
		return galleryList;
	}
	
	//이미지 등록하기 
	public void insert(GalleryVo gVo) {
		System.out.println("GalleryDao.insert");
		
		System.out.println("이미지 디비 들어가기전 :" + gVo);
		//디비에 등록하기
		sqlSession.insert("gallery.insert", gVo);
		System.out.println("이미지 디비 들어가고 난 후 :" + gVo);
	}
	
	//이미지, 내용 가져오기
	public GalleryVo selectOne(int no) {
		System.out.println("GalleryDao.selectOne");
		
		//디비에 등록하기
		GalleryVo galleryVo= sqlSession.selectOne("gallery.selectOne", no);
		
		return galleryVo;
	}
	
	//삭제하기
	public int delete(int no) {
		System.out.println("GalleryDao.delete");
		
		//디비에서 삭제하기
		int count =sqlSession.delete("gallery.delete", no);
		return count ;
	}
}
