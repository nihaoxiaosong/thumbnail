package com.thumbnail;

import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

@Service
public class ThumbnialService {
	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;
	
	public String thumbnail(CommonsMultipartFile file, String uploadPath, String realUploadPath){
		try {
			String des = realUploadPath+"/thum_"+file.getOriginalFilename();
//			Thumbnails.of(file.getInputStream()).size(WIDTH, HEIGHT).toFile(des);
			Builder<? extends InputStream> thumbnail = Thumbnails.of(file.getInputStream());
			thumbnail.size(WIDTH, HEIGHT);
			thumbnail.toFile(des);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uploadPath+"/thum_"+file.getOriginalFilename();
	}
}
