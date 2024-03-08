
package com.ex.springboot.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ex.springboot.dao.IFeedDAO;
import com.ex.springboot.dto.CourseDTO;
import com.ex.springboot.dto.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class CarrierController {

   @Autowired
   private com.ex.springboot.dao.IBandDAO bandDao; 
   @Autowired
   IFeedDAO feed_dao;
   @Autowired
   private com.ex.springboot.dao.IAiDAO AiDAO; 
   @Autowired
   private com.ex.springboot.dao.IEventDAO eventDAO;
   @Autowired
   private com.ex.springboot.dao.IMemberDAO member_dao;
   @GetMapping("/")   
   public String main(Model model) {
	   
	    //홈에 밴드 불러오는 코드
	     model.addAttribute("randomBandList_home", bandDao.randomBandList());
	   //홈에 피드 불러오는 코드
	     model.addAttribute("feedList", feed_dao.feedList_random());
	   //홈에 여행 정보 불러오는 코드
	     model.addAttribute("infoHome2", eventDAO.infoHome2());
	   //홈에 코스정보 불러오는 코드
	     List<CourseDTO> courseList = AiDAO.CourseListHome();
	     List<CourseDTO> courseList2 = AiDAO.CourseListHome2();
	     model.addAttribute("CourseListHome", courseList);
	     model.addAttribute("infoHome", courseList2);
	     model.addAttribute("infoHome2", eventDAO.infoHome2());

	     int number = courseList2.get(0).getCourse_num();
	     String numm = String.valueOf(number);
	     int number2 = courseList2.get(1).getCourse_num();
	     String numm2 = String.valueOf(number2);
	     int number3 = courseList2.get(2).getCourse_num();
	     String numm3 = String.valueOf(number3);
	     
	     System.out.println("number값 확인 : "+number);
	     System.out.println("number2값 확인 : "+number2);
	     System.out.println("number3값 확인 : "+number3);
	     String num = AiDAO.Course_view_list(numm).get(0).getCourse_number();
	     String[] values = num.split(",");
	     model.addAttribute("text1", AiDAO.listCourse(values[0]));
		 model.addAttribute("text2", AiDAO.listCourse(values[1]));
		 model.addAttribute("text3", AiDAO.listCourse(values[2]));
		 model.addAttribute("text4", AiDAO.listCourse(values[3]));
		 model.addAttribute("text5", AiDAO.listCourse(values[4]));
		 
		 String num2 = AiDAO.Course_view_list(numm2).get(0).getCourse_number();
	     String[] values2 = num2.split(",");
	     model.addAttribute("text11", AiDAO.listCourse(values2[0]));
		 model.addAttribute("text12", AiDAO.listCourse(values2[1]));
		 model.addAttribute("text13", AiDAO.listCourse(values2[2]));
		 model.addAttribute("text14", AiDAO.listCourse(values2[3]));
		 model.addAttribute("text15", AiDAO.listCourse(values2[4]));
		 
		 String num3 = AiDAO.Course_view_list(numm3).get(0).getCourse_number();
		 System.out.println("num3의 정체는 "+num3);
	     String[] values3 = num3.split(",");
	     model.addAttribute("text21", AiDAO.listCourse(values3[0]));
		 model.addAttribute("text22", AiDAO.listCourse(values3[1]));
		 model.addAttribute("text23", AiDAO.listCourse(values3[2]));
		 model.addAttribute("text24", AiDAO.listCourse(values3[3]));
		 model.addAttribute("text25", AiDAO.listCourse(values3[4]));
		
      return "thymeleaf/home/home";
   }
   
	@RequestMapping("/checkout")
	public String checkout(HttpServletRequest request, Model model, MemberDTO dto) {
		/* 결제에 필요한 항목 */
		// 주문번호 = 아이디+시간
		// 이름
		// 이메일
		// 코스 제목
		// 핸드폰 번호
		// 결제 금액
		
		// day1 파라미터 값
		String day1aicc = request.getParameter("day1aicc");
		String day1aiccFood = request.getParameter("day1aiccFood");
		String day1aicc2 = request.getParameter("day1aicc2");
		String day1aiccCafe = request.getParameter("day1aiccCafe");
		String day1aiccFood2 = request.getParameter("day1aiccFood2");
		String day1hotel = request.getParameter("day1hotel");
		
		// day2 파라미터 값
		String day2aicc = request.getParameter("day2aicc");
		String day2aiccFood = request.getParameter("day2aiccFood");
		String day2aicc2 = request.getParameter("day2aicc2");
		String day2aiccCafe = request.getParameter("day2aiccCafe");
		String day2aiccFood2 = request.getParameter("day2aiccFood2");
		String day2hotel = request.getParameter("day2hotel");
		
		// day3 파라미터 값
		String day3aicc = request.getParameter("day3aicc");
		String day3aiccFood = request.getParameter("day3aiccFood");
		String day3aicc2 = request.getParameter("day3aicc2");
		String day3aiccCafe = request.getParameter("day3aiccCafe");
		String day3aiccFood2 = request.getParameter("day3aiccFood2");
		String number="";
		
		String Course_name = request.getParameter("Course_name");
		String Course_thema = request.getParameter("Course_thema");
		String Course_Area = request.getParameter("Course_Area");
		String Course_content = request.getParameter("Course_content");
		String Course_distance = request.getParameter("Course_distance");
		String img = request.getParameter("img");
		
		// 결제 파라미터
		String Member_Id = request.getParameter("Member_Id");
		String Event_area = request.getParameter("Event_area"); // 코스 제목 뽑기 위함
		String day = request.getParameter("day"); // 코스 제목 뽑기 위함
		String title = Member_Id+"님의 "+Event_area+" "+day+"코스";
		int amount = Integer.parseInt(request.getParameter("amount"));
		String phone;
		
		// 데이터 가공 - 하이픈 제거
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String getTime = currentDateTime.format(formatter);
		
		dto = member_dao.memberList(Member_Id);
		phone = dto.getMember_Phone().replace("-","");
		
		
		model.addAttribute("orderId", Member_Id+getTime);
		model.addAttribute("title", title);
		model.addAttribute("amount", amount);
		model.addAttribute("member", dto);
		model.addAttribute("customerMobilePhone", phone);
		
		if(day.equals("2박3일")) {
			 number = day1aicc+","+day1aiccFood+","+day1aicc2+","+day1aiccCafe+","+day1aiccFood2+","+day1hotel+","+
					day2aicc+","+day2aiccFood+","+day2aicc2+","+day2aiccCafe+","+day2aiccFood2+","+day2hotel+","+
							day3aicc+","+day3aiccFood+","+day3aicc2+","+day3aiccCafe+","+day3aiccFood2;
			 System.out.println(AiDAO.save_course_insert(Member_Id,Course_name,Course_thema,Course_Area,Course_content,Course_distance,day,number,img,0));
		}
		if(day.equals("1박2일")) {
			number = day1aicc+","+day1aiccFood+","+day1aicc2+","+day1aiccCafe+","+day1aiccFood2+","+day1hotel+","+
					day2aicc+","+day2aiccFood+","+day2aicc2+","+day2aiccCafe+","+day2aiccFood2;
			System.out.println(AiDAO.save_course_insert(Member_Id,Course_name,Course_thema,Course_Area,Course_content,Course_distance,day,number,img,0));
		}
		
		if(day.equals("당일 치기")) {
			number = day1aicc+","+day1aiccFood+","+day1aicc2+","+day1aiccCafe+","+day1aiccFood2;
			System.out.println(AiDAO.save_course_insert(Member_Id,Course_name,Course_thema,Course_Area,Course_content,Course_distance,day,number,img,0));
		}

		return "thymeleaf/member/checkout";
	}
	
	@GetMapping("/fail")
	public String fail() {
		return "thymeleaf/member/fail";
	}
	
	@GetMapping("/success")
	public String success(HttpServletRequest request, Model model) {
		
		
		return "thymeleaf/member/success";
	}
   
   @GetMapping("/home")
   public String home(Model model) {
	 //홈에 밴드 불러오는 코드
	     model.addAttribute("randomBandList_home", bandDao.randomBandList());
	   //홈에 피드 불러오는 코드
	     model.addAttribute("feedList", feed_dao.feedList_random());
	   //홈에 여행 정보 불러오는 코드
	     model.addAttribute("infoHome2", eventDAO.infoHome2());
	   //홈에 코스정보 불러오는 코드
	     List<CourseDTO> courseList = AiDAO.CourseListHome();
	     List<CourseDTO> courseList2 = AiDAO.CourseListHome2();
	     model.addAttribute("CourseListHome", courseList);
	     model.addAttribute("infoHome", courseList2);
	     int number = courseList2.get(0).getCourse_num();
	     String numm = String.valueOf(number);
	     int number2 = courseList2.get(1).getCourse_num();
	     String numm2 = String.valueOf(number2);
	     int number3 = courseList2.get(2).getCourse_num();
	     String numm3 = String.valueOf(number3);
	     
	     System.out.println("number값 확인 : "+number);
	     System.out.println("number2값 확인 : "+number2);
	     System.out.println("number3값 확인 : "+number3);
	     String num = AiDAO.Course_view_list(numm).get(0).getCourse_number();
	     String[] values = num.split(",");
	     model.addAttribute("text1", AiDAO.listCourse(values[0]));
		 model.addAttribute("text2", AiDAO.listCourse(values[1]));
		 model.addAttribute("text3", AiDAO.listCourse(values[2]));
		 model.addAttribute("text4", AiDAO.listCourse(values[3]));
		 model.addAttribute("text5", AiDAO.listCourse(values[4]));
		 
		 String num2 = AiDAO.Course_view_list(numm2).get(0).getCourse_number();
	     String[] values2 = num2.split(",");
	     model.addAttribute("text11", AiDAO.listCourse(values2[0]));
		 model.addAttribute("text12", AiDAO.listCourse(values2[1]));
		 model.addAttribute("text13", AiDAO.listCourse(values2[2]));
		 model.addAttribute("text14", AiDAO.listCourse(values2[3]));
		 model.addAttribute("text15", AiDAO.listCourse(values2[4]));
		 
		 String num3 = AiDAO.Course_view_list(numm3).get(0).getCourse_number();
		 System.out.println("num3의 정체는 "+num3);
	     String[] values3 = num3.split(",");
	     model.addAttribute("text21", AiDAO.listCourse(values3[0]));
		 model.addAttribute("text22", AiDAO.listCourse(values3[1]));
		 model.addAttribute("text23", AiDAO.listCourse(values3[2]));
		 model.addAttribute("text24", AiDAO.listCourse(values3[3]));
		 model.addAttribute("text25", AiDAO.listCourse(values3[4]));
      
      return "thymeleaf/home/home";
   }


   
   

}