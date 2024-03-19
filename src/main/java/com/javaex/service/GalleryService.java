package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service	
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;
	//리스트 불러오기
	public List<GalleryVo> exeList() {
		System.out.println("GalleryService.exeList");
		
		//디비에 접근해서 정보 불러오기
		List<GalleryVo> galleryList = galleryDao.galleryList();
		
		return galleryList;
		
	}
	
	//이미지 등록하기
	public void exeUpload(GalleryVo galleryVo) {
		System.out.println("GalleryService.exeUpload");
		
		//하드디스크에 저장하기 + DB에 저장
		//파일 저장 폴더
		String savaDir = "C:\\javaStudy\\upload";
		
		//(0)파일 관련 정보수집
		//오리지널 파일 명
		String orgName =  galleryVo.getFile().getOriginalFilename();
		System.out.println("orgName: "+ orgName);
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("exeName: "+ exName);
		
		//저장파일 명(겹지지 않아야함)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName : " + saveName);
		
		//파일 사이즈
		long fileSize = galleryVo.getFile().getSize();
		System.out.println("fileSize: " + fileSize);
		
		//파일 전체 경로(저장 파일명 포함)
		String filePath = savaDir + "\\" + saveName;
		System.out.println("filePath: " + filePath);
		
		//컨텐츠
		String content = galleryVo.getContent();
		System.out.println("content: "+ content);
		
		//로그인 정보
		int user_no = galleryVo.getUser_no();
		System.out.println("user_no: " + user_no);
		
		//(1) 파일 정보를 DB에 저장
		GalleryVo gVo = new GalleryVo(user_no,content,filePath,orgName,saveName,fileSize);
		galleryDao.insert(gVo);
		
		//(2)파일을 하드디스크에 저장
		//*파일 저장
		try {
			byte[] fileData = galleryVo.getFile().getBytes();
			
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//return saveName; 
	}//이미지 등록하기 끝
	
	
	//
}
