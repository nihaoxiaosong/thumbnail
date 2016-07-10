package com.thumbnail;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ThumbnailAction {
	@Autowired
	private UploadService uploadService;
	@Autowired
	private ThumbnialService thumbnialService;
	
	@RequestMapping(value="/thumbnail",method=RequestMethod.POST)
	public ModelAndView thumbnail(@RequestParam("image")CommonsMultipartFile file, HttpSession session) throws Exception{
		//相对路径
		String uploadPath = "/images";
		//绝对路径
		String realUploadPath = session.getServletContext().getRealPath(uploadPath);
		//原图访问路径
		String imageUrl = uploadService.uploadImage(file, uploadPath, realUploadPath);
		//缩略图访问路径
		String thumbImageUrl = thumbnialService.thumbnail(file, uploadPath, realUploadPath);;
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("imageURL",imageUrl);
		mav.addObject("thumbImageURL", thumbImageUrl);
		
		mav.setViewName("thumbnail");
		return mav;
		
	}
}
