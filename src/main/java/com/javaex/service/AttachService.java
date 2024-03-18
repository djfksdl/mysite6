package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.AttachDao;
import com.javaex.vo.AttachVo;

@Service
public class AttachService {
	
	@Autowired
	private AttachDao attachDao;
	
	public String exeUpload(MultipartFile file) {
		System.out.println("AttachService.exeUpload()");
		
		System.out.println(file.getOriginalFilename());
		
		//파일 저장 폴더
		String saveDir = "C:\\javaStudy\\upload";// 슬래시 쓰려면 \t, \n처럼 특수기호로 인식해서? 하나 더 붙여줘야 오류가 안남 11:44
		
		//(0)파일 관련 정보수집 11:00
		// 오리지날 파일명
		String orgName = file.getOriginalFilename();
		System.out.println("orgName: "+orgName);
		
		
		//확장자 (.jsp, .exle 등등): 있으면 매칭되는 아이콘 생길수있음
		String exName = orgName.substring(orgName.lastIndexOf("."));//.찍힌곳에 어디에 있는지
		System.out.println("exeName:"+ exName); //세이브네임에 확장자 붙이려고 만든거임. 굳이 얘는 db에 저장할 필요없음
		//System.out.println(orgName.substring(4));
		//System.out.println(orgName.lastIndexOf("."));
		
		//저장 파일 명(겹치지 않아야함)
		String saveName= System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName : " + saveName);
		
		//파일 사이즈
		long fileSize = file.getSize();//바이트 단위임-> long형으로 써주기
		System.out.println("fileSize: " + fileSize);
		
		//파일 전체 경로(저장파일명 포함)
		//C:\javaStudy\\upload \ 1710729656947:2d5594bf-da75-48be-ac45-0c2748f6e4ea.jpg로 저장할거임
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath: " + filePath);
		
		//(1) 파일 정보를 DB에 저장
		//*vo로 묶어주기
		AttachVo attachVo = new AttachVo(orgName,saveName,filePath,fileSize);
		System.out.println(attachVo);
		
		//*DB에 저장
		//Dao 메소드 호출해서 저장 -- > 만들어볼것
		System.out.println("...DB저장완료");
		attachDao.saveFile(attachVo);
		
		//table도 만들어야함. 
		
		//(2)파일을 하드디스크에 저장
		//*파일 저장
		try {
			byte[] fileData = file.getBytes();
			
			OutputStream os = new FileOutputStream(filePath); //이 이름으로 만들꺼다. 빨대 꽃음. filePath로 빈 파일이 생김
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();//빨대 회수
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return saveName;
	}
}
