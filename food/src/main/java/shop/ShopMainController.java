package shop;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import comment.CommentService;
import comment.CommentVo;
import hensuUserMypage.HensuMyService;
import hensuUserMypage.VisitVo;
import restaurant.RestaurantService;
import restaurant.RestaurantVo;
import restaurant.WishlistVo;
import user.UserVo;

@Controller
public class ShopMainController {

	@Autowired
	CommentService cService;
	@Autowired
	RestaurantService service; 
	@Autowired
	HensuMyService hservice;
	
	@GetMapping("shop/shopmain.do")
	public String main(Model model, @RequestParam int r_no,HttpSession sess,WishlistVo wvo) {
		
		if(sess.getAttribute("userInfo") != null) {
			VisitVo vo = new VisitVo();
			vo.setR_no(r_no);
			vo.setU_no(((UserVo)sess.getAttribute("userInfo")).getU_no());
			hservice.insert(vo);
			wvo.setU_no(((UserVo)sess.getAttribute("userInfo")).getU_no());
			wvo.setR_no(r_no);
			model.addAttribute("listcount", service.wishCount(wvo));
		}
		model.addAttribute("data", service.selectone(r_no));
		comment.CommentVo cv = new comment.CommentVo();
		cv.setR_no(r_no);
		cv.setTablename("restaurant");
		System.out.println(cv.getR_no());
		model.addAttribute("list",cService.restselectList(cv));
		return "shop/shopmain";
	}
	
	@GetMapping("shop/join.do")
	public String shopJoin() {
		return "shop/shopJoin";
	}
	@PostMapping("/shop/insert.do")
	public String insert(RestaurantVo vo, HttpServletRequest req, MultipartFile file,MultipartFile file1,MultipartFile file2, HttpSession sess) {
		vo.setU_no(((UserVo)sess.getAttribute("userInfo")).getU_no());
		//파일저장 
		
		if (!file.isEmpty() && !file1.isEmpty()&& !file2.isEmpty()) { // 사용자가 파일을 첨부했다면 
			try {
				String path = req.getRealPath("/upload/");
				String filename = file.getOriginalFilename(); // 사용자가 업로드한 원본 파일
				String filename1 = file1.getOriginalFilename(); // 사용자가 업로드한 원본 파일
				String filename2 = file2.getOriginalFilename(); // 사용자가 업로드한 원본 파일
				String ext = filename.substring(filename.lastIndexOf(".")); // 확장자 (.jpg)
				String ext1 = filename1.substring(filename1.lastIndexOf(".")); // 확장자 (.jpg)
				String ext2 = filename2.substring(filename2.lastIndexOf(".")); // 확장자 (.jpg)
				String filename_real = System.currentTimeMillis() + ext;
				String filename_real1 = System.currentTimeMillis()-1 + ext1;
				String filename_real2 = System.currentTimeMillis()-2 + ext2;
				
				file.transferTo(new File(path+filename_real)); // 경로에 파일을 저장 
				file1.transferTo(new File(path+filename_real1)); // 경로에 파일을 저장 
				file2.transferTo(new File(path+filename_real2)); // 경로에 파일을 저장 
				
				vo.setR_filename_org(filename);
				vo.setR_filename_org1(filename1);
				vo.setR_filename_org2(filename2);
				vo.setR_filename_real(filename_real);
				vo.setR_filename_real1(filename_real1);
				vo.setR_filename_real2(filename_real2);
				
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		int r = service.restinsert(vo);
		
		// 정상적으로 등록되었습니다. alert 띄우고 
		// index.do 로 이동 
		if(r > 0) {
		req.setAttribute("msg", "정상적으로 등록되었습니다");
		req.setAttribute("url", "/res/user/main.do");
		} else {
			req.setAttribute("msg", "등록 오류 ");
			req.setAttribute("url", "join.do");
		}
		
		return "include/return";
		}
		@GetMapping("/shop/edit.do")
		public String edit(Model model, @RequestParam int r_no) {
			model.addAttribute("data", service.selectone(r_no));
			return "shop/shopUpdate";
		}
		@PostMapping("/shop/update.do")
		public String update(RestaurantVo vo, HttpServletRequest req, MultipartFile file,MultipartFile file1,MultipartFile file2,MultipartFile file3, HttpSession sess) {
			
			if ((file != null && !file.isEmpty())) { // 사용자가 파일을 첨부했다면 
				try {
					String path = req.getRealPath("/upload/");
					String filename = file.getOriginalFilename(); // 사용자가 업로드한 원본 파일
					String ext = filename.substring(filename.lastIndexOf(".")); // 확장자 (.jpg)
					String filename_real = System.currentTimeMillis() + ext;
					
					file.transferTo(new File(path+filename_real)); // 경로에 파일을 저장 
					
					vo.setR_filename_org(filename);
					vo.setR_filename_real(filename_real);
					
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			if ((file1 != null && !file1.isEmpty())) { // 사용자가 파일을 첨부했다면 
				try {
					String path = req.getRealPath("/upload/");
					String filename1 = file1.getOriginalFilename(); // 사용자가 업로드한 원본 파일
					String ext1 = filename1.substring(filename1.lastIndexOf(".")); // 확장자 (.jpg)
					String filename_real1 = System.currentTimeMillis()-100 + ext1;
					
					file1.transferTo(new File(path+filename_real1)); // 경로에 파일을 저장 
					
					vo.setR_filename_org1(filename1);
					vo.setR_filename_real1(filename_real1);
					
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			if ((file2 != null && !file2.isEmpty())) { // 사용자가 파일을 첨부했다면 
				try {
					String path = req.getRealPath("/upload/");
					String filename2 = file2.getOriginalFilename(); // 사용자가 업로드한 원본 파일
					String ext2 = filename2.substring(filename2.lastIndexOf(".")); // 확장자 (.jpg)
					String filename_real2 = System.currentTimeMillis()-200 + ext2;
					
					file2.transferTo(new File(path+filename_real2)); // 경로에 파일을 저장 
					
					vo.setR_filename_org2(filename2);
					vo.setR_filename_real2(filename_real2);
					
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			int r = service.update(vo);
			System.out.println(vo.getR_no());
			// 정상적으로 등록되었습니다. alert 띄우고 
			// index.do 로 이동 
			if(r > 0) {
			req.setAttribute("msg", "정상적으로 등록되었습니다");
			req.setAttribute("url", "/res/shop/shopmain.do?r_no="+vo.getR_no());
			} else {
				req.setAttribute("msg", "등록 오류 ");
				req.setAttribute("url", "/res/shop/edit.do?r_no="+vo.getR_no());
			}
			
			return "include/return";
			}
		
}