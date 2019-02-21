package com.goshop.content.controller;

import java.io.File;
import java.io.IOException;

import com.shop.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {
	
	
	@RequestMapping("/uploadFile")
	public Result uploadFile(MultipartFile file){
		try {
			//设置虚拟的映射路径 ---> D:/file
			String path="D:/file";
			String url = "";
			if (file!=null && file.getSize()>0) {
				file.transferTo(new File(path, file.getOriginalFilename()));
				url = "http://localhost:9903/upload/"+file.getOriginalFilename();
			}	
			return new Result(true, url);
		} catch (IOException e) {
			e.printStackTrace();
			return new Result(false, "上传失败");
		}
		
	}
}
