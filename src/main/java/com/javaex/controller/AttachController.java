package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;

@Controller
public class AttachController {
	
	@Autowired
	private AttachService attachService;
	
	@RequestMapping(value="/attach/uploadform", method= {RequestMethod.POST, RequestMethod.GET})
	public String uploadform() {
		System.out.println("AttachController.uploadform()");
		return"attach/form";
	}
	
	@RequestMapping(value="/attach/upload", method= RequestMethod.POST)
	public String upload(@RequestParam(value="file") MultipartFile file, Model model) { //MultipartFile: 파일을 담기 위한 방법. String 이나 int로 담을 수 없다. 
		System.out.println("AttachController.upload()");
		//System.out.println(file.getOriginalFilename());
		//System.out.println(file.toString());//원래는 toString으로 찍어서 확인하는데, 첨부파일이 있어도 찍고, 없어도 찍는애다.그래서 올라왔는지 안올라왔는지 toString으로 확인하면 안된다. (주의)
		
		String saveName = attachService.exeUpload(file);
		model.addAttribute("saveName", saveName);
		
		return"attach/result";
	}
}
