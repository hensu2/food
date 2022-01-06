package admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDao dao;
	
	@Override
	public boolean login(AdminVo vo, HttpSession sess) {
		AdminVo av = dao.login(vo);
		if (av != null) { // 로그인 성공
			sess.setAttribute("userInfo", av);
			return true;
		}
		return false;
	}
}