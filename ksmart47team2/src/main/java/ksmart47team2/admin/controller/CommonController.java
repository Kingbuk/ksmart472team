package ksmart47team2.admin.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart47team2.admin.service.CommonService;

@Controller("adminCommonController")
@RequestMapping("/admin")
public class CommonController {
	
	private final CommonService commonService;
	
	public CommonController(@Qualifier("adminCommonService") CommonService commonService) {
		this.commonService = commonService;
		
	}
	@GetMapping("/")
	public String adminIndex(Model model) {
		model.addAttribute("title", "관리자 메인화면");
		return "admin/index";
		
	}
	
	
	
	
}