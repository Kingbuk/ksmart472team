package ksmart47team2.user.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ksmart47team2.user.service.CommonService;

@Controller(value ="userCommonController")
public class CommonController {
	
	private final CommonService commonService;
	//@Qualifier 매개변수의 이름 정함.
	public CommonController(@Qualifier("userCommonService")CommonService commonService) {
		this.commonService = commonService;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("title", "메인화면");
		return "user/main";
	}
}
