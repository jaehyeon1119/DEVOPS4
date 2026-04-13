package org.cloud.controller;

import org.cloud.dto.MemberDto;
import org.cloud.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/")
	public String main() {
		return "member/index";
	}
	
	@GetMapping("/login.do")
	public String loginPage() {
		return "member/index";
	}
	
	@GetMapping("/join.do")
	public String openJoin() {
		return "member/join";
	}
	
	@PostMapping("/insertMember.do")
	public String insertMember(MemberDto member) throws Exception {
		memberService.insertMember(member);
		return "redirect:member/login.do";
	}
}






