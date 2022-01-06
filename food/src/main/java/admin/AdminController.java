package admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

	@Autowired
	AdminService service;
	
	@GetMapping("/admin/login.do")
	public String adminIndex() {
		return "admin/index";
	}
	
	@PostMapping("/admin/login.do")
	public String loginProcess(Model model, AdminVo vo, HttpSession sess) {
		if (service.login(vo, sess)) {
			model.addAttribute("url", "/res/admin/board/index.do");
			return "include/return";
		} else {
			model.addAttribute("msg", "이메일, 비밀번호를 확인해 주세요");
			model.addAttribute("url", "/res/admin/login.do");
			return "include/return";
		}
	}
	@GetMapping("/admin/board/index.do")
	public String adminBoardIndex() {
		return "admin/board/index";
	} 
	@GetMapping("/admin/board/view.do")
	public String adminBoardView() {
		return "admin/board/view";
	}
	@GetMapping("/admin/board/write.do")
	public String adminBoardWrite() {
		return "admin/board/write";
	}
	@GetMapping("/admin/logout.do")
	public String logOut(Model model, HttpSession sess) {
		model.addAttribute("msg", "로그아웃되었습니다.");
		model.addAttribute("url", "/res/admin/login.do");
		sess.invalidate();
		return "include/return";
	}
	
}


