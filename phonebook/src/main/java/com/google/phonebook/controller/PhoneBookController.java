package com.google.phonebook.controller;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.phonebook.dao.PhoneBookDao;
import com.google.phonebook.dao.UserTableDao;
import com.google.phonebook.dto.PhoneBookDto;
import com.google.phonebook.dto.UserTableDto;
import com.google.phonebook.service.UserTableService;

@Controller
@RequestMapping("/phonebook")
public class PhoneBookController {

	PhoneBookDao phonebookDao;
	UserTableDao usertableDao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public PhoneBookController(PhoneBookDao phonebookDao, UserTableDao usertableDao) {
		this.phonebookDao = phonebookDao;
		this.usertableDao = usertableDao;
	}

	@Value("${phonebook.imgdir}")
	String fdir;

//  회원가입 화면
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

//  회원가입 처리
	@PostMapping("/up")
	public String signup(@ModelAttribute UserTableDto usertableDto, Model model,
			@RequestParam("file") MultipartFile file) {
		// 2.0 파일 처리 => 파일 객체 생성, 저장
		File dest = new File(fdir + "/" + file.getOriginalFilename()); // 파일 생성x(경로+파일명)

		try {
			// 2.1 정상처리
			file.transferTo(dest);// 파일 저장
			usertableDto.setImgnm("/images/" + dest.getName());// 중복이미지일시 변경된이름으로get
			logger.info(dest.getName());
			usertableDao.insert(usertableDto);
		} catch (Exception e) {
			// 2.2 비정상처리 => 화면에 비정상처리 경고메세지보내야함
			e.printStackTrace();
			logger.warn("회원가입 과정에서의 문제 발생");
			model.addAttribute("error", "회원가입이 정상적으로되지 않았습니다.");
		}

		return "redirect:/phonebook/signin";
	}

// 	연락처 추가 
	@GetMapping("/login/add")
	public String add() {
		return "addmember";
	}

	@PostMapping("/add")
	public String adddto(@ModelAttribute PhoneBookDto phonebookdto, Model model, HttpServletRequest request) {
		PhoneBookDao phonebookdao = new PhoneBookDao();
		try {
			HttpSession session = request.getSession();
			String insertsession = (String) session.getAttribute("userid");
			phonebookdto.setUserid(insertsession);
			phonebookdao.insert(phonebookdto);
		} catch (Exception e) {
			// 2.2 비정상처리 => 화면에 비정상처리 경고메세지보내야함
			e.printStackTrace();
			logger.warn("회원가입 과정에서의 문제 발생");
			model.addAttribute("error", "회원가입이 정상적으로 되지 않았습니다.");
		}
		return "redirect:/phonebook/login/searchall";
	}

//  연락처 수정하기
	   
  @PostMapping("/login/update")
  public String update(@ModelAttribute PhoneBookDto phonebookdto
                 , @RequestParam(value = "phonenumber", required = false) String phonenumber
                 , Model model
                 , ServletRequest request ) {
  
     PhoneBookDao phonebookDao = new PhoneBookDao();
     
     HttpServletRequest httpRequest = (HttpServletRequest) request;
     HttpSession session = httpRequest.getSession(false);

     phonebookdto.setUserid((String)session.getAttribute("userid"));
     phonebookdto.setPhonenumber(phonenumber);
     System.out.println(phonebookdto);
     
     try {
        phonebookDao.update(phonebookdto);
        logger.info("연락처 수정 성공");

     }catch(Exception e) {
        e.printStackTrace();
        logger.info("연락처 수정 과정에서 문제 발생!!");
        model.addAttribute("error", "연락처가 정상적으로 수정되지 않았습니다!!");
     }
     
     return "redirect:/phonebook/login/searchall";
  }
  
	
//  로그인 화면
	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}

//  로그인 처리
	@PostMapping("/in")
	public String signin(@RequestParam(value = "userid", required = false) String userid
					   , @RequestParam(value = "userpassword", required = false) String userpassword
					   , RedirectAttributes red
					   , Model model
					   , HttpServletRequest request) {
		
		UserTableService usertableservice = new UserTableService();
		int check;
		try {
			usertableservice.equalcheck(userid, userpassword);
			check = usertableservice.equalcheck(userid, userpassword);
			if (check == 1) {
				red.addFlashAttribute("msg", "<script>alert('로그인 성공');</script>");
				HttpSession session = request.getSession();
				session.setAttribute("userid", userid);
			} else {
				red.addFlashAttribute("msg", "<script>alert('로그인 실패');</script>");
				return "redirect:/phonebook/signin";
			}
		} catch (Exception e) {
			// 2.2 비정상처리 => 화면에 비정상처리 경고메세지보내야함
			e.printStackTrace();
			logger.warn("로그인 과정에서의 문제 발생");
			model.addAttribute("error", "로그인이 정상적으로되지 않았습니다.");
		}
		return "redirect:/phonebook/login/afterloginmain";

	}

//  로그인 전 페이지 
	@GetMapping("/beforelogin")
	public String loginbefore() {
		return "loginbeforemainpage";
	}

//  로그인 후 페이지 	
	@GetMapping("/login/afterloginmain")
	public String loginafter(Model model
						   , HttpServletRequest request) {
		UserTableDto userdto;
		try {
			HttpSession session = request.getSession();
			String insertsession = (String) session.getAttribute("userid");
			userdto = usertableDao.searchAll(insertsession);
			session.setAttribute("image", userdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loginaftermainpage";
	}

// 연락처 목록 처리
	@GetMapping("/login/searchall")
	public String searchall(Model model
						  , HttpServletRequest request) {
		
		List<PhoneBookDto> list;
		try {
			HttpSession session = request.getSession();
			String sessionuserid = (String) session.getAttribute("userid");
			list = phonebookDao.searchByID(sessionuserid);
			model.addAttribute("memberlist", list);
			System.out.println(model.getAttribute("memberlist"));
			int listsize = list.size();
			model.addAttribute("listsize", listsize);
		} catch (Exception e) {
			e.printStackTrace(); // 개발시에는 필요하다.
			logger.warn("목록 생성 과정에서 문제 발생"); // logger에는 level이 5개가 있다. // 서버쪽 콘솔에 출력
			model.addAttribute("error", "목록이 정상적으로 처리되지 않았습니다."); // 브라우저 화면에 출력

		}
		return "searchall";
	}

//  연락처 삭제 처리
	@GetMapping("/delete/{phonenumber}")
	public String delete(@PathVariable String phonenumber
					   , Model model
					   , HttpServletRequest request) {
		
		try {
			HttpSession session = request.getSession();
			String sessionuserid = (String) session.getAttribute("userid");			
			System.out.println(sessionuserid);
			phonebookDao.delete(phonenumber, sessionuserid);
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("연락처 삭제 과정에서 문제 발생");
			model.addAttribute("error", "연락처가 정상적으로 삭제되지 않았습니다.");
		}
		return "redirect:/phonebook/login/searchall";
	}
	
//  로그아웃 처리 
	@GetMapping("/removesession")
	public String remove(HttpServletRequest request) {
			HttpSession session = request.getSession();
			session.removeAttribute("userid");
			session.removeAttribute("membernm");
			session.removeAttribute("phonenumber");
		return "redirect:/phonebook/beforelogin";
	}

//  연락처 검색 처리 
	@PostMapping("/search")
	public String search(@RequestParam(value = "membernm", required = false) String membernm
						, Model model
						, HttpServletRequest request) {
		List<PhoneBookDto> list;
		try {
			HttpSession session = request.getSession();
			String sessionuserid = (String) session.getAttribute("userid");
			list = phonebookDao.searchByName(sessionuserid, membernm);
			int searchlist = list.size();
			model.addAttribute("searchlist", list);
			model.addAttribute("listsize", searchlist);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("연락처 검색 과정에서 문제 발생");
			model.addAttribute("error", "연락처가 정상적으로 검색되지 않았습니다.");
		}
		return "searchbyname";
	}
	
	@ResponseBody
	@RequestMapping(value="/idcheck" , method=RequestMethod.POST)
	public int check(@RequestParam(value = "userid", required = false) String userid) {
		int check = 0;
		try {
			ArrayList<UserTableDto> list = usertableDao.signupidcheck(userid);
			if(list.size() > 0) {
				check = 1;
			}else {
				check = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
	@ResponseBody
	@RequestMapping(value="/pncheck" , method=RequestMethod.POST)
	public int pncheck(@RequestParam(value = "phonenumber", required = false) String phonenumber) {
		int check = 0;
		try {
			if(phonenumber.length()!=11) {
				check = 1;
			}else {
				check = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
	@ResponseBody
	@RequestMapping(value="/namecheck" , method=RequestMethod.POST)
	public int namecheck(@RequestParam(value = "membernm", required = false) String membernm) {
		int check = 0;
		try {
			if(membernm.length()<2 || membernm.length()==0) {
				check = 1;
			}
			else {
				check = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
	@ResponseBody
	@RequestMapping(value="/addresscheck" , method=RequestMethod.POST)
	public int addresscheck(@RequestParam(value = "address", required = false) String address) {
		int check = 0;
		try {
			if(address.length()<2 || address.length()==0) {
				check = 1;
			}
			else {
				check = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
}