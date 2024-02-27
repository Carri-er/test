package com.ex.springboot.controller;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ex.springboot.dto.EventCommentDTO;
import com.ex.springboot.dto.EventDTO;

import jakarta.servlet.http.HttpServletRequest;

import com.ex.springboot.dao.IEventDAO;

@Controller
@RequestMapping
public class AIController {
	public static String UPLOAD_EVENT_DIRECTORY = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\static\\img\\info";

	@Autowired
	private com.ex.springboot.dao.IAiDAO AiDAO; // 다형성

	@GetMapping("/aicc")
	public String aicc() {
		return "thymeleaf/aicc/aicc";
	}

	@GetMapping("/aiccShow")
	public String aiccShow(HttpServletRequest request, Model model) {
		String area = request.getParameter("areaChk");
		String day = request.getParameter("day");
		day = day !=null? day:"0";
		String cafe = "카페";
		String food = "맛집";
		String thema = request.getParameter("thema");// 산
		String thema1 = request.getParameter("thema1");// 실내
		String thema2 = request.getParameter("thema2");// 엑티비티
		String thema3 = request.getParameter("thema3");// 테마파크
		String thema4 = request.getParameter("thema4");// 카페
		String thema5 = request.getParameter("thema5");// 바다
		String thema6 = request.getParameter("thema6");// 축제
		String thema7 = request.getParameter("thema7");// 맛집
		String thema8 = request.getParameter("thema8");// 전통시장
		model.addAttribute("day", day);
		thema = thema != null ? thema : " ";
		thema2 = thema2 != null ? thema2 : " ";
		thema3 = thema3 != null ? thema3 : " ";
		thema4 = thema4 != null ? thema4 : " ";
		thema5 = thema5 != null ? thema5 : " ";
		thema6 = thema6 != null ? thema6 : " ";
		thema7 = thema7 != null ? thema7 : " ";
		thema8 = thema8 != null ? thema8 : " ";
		thema1 = thema1 != null ? thema1 : " ";

		model.addAttribute("thema", thema);
		model.addAttribute("thema", thema1);
		model.addAttribute("thema2", thema2);
		model.addAttribute("thema3", thema3);
		model.addAttribute("thema4", thema4);
		model.addAttribute("thema5", thema5);
		model.addAttribute("thema6", thema6);
		model.addAttribute("thema7", thema7);
		model.addAttribute("thema8", thema8);

		String area2 = "";
		
		if (AiDAO.list(area, thema, thema2, thema3, thema5, thema6, thema8, thema1).isEmpty()) {
			model.addAttribute("aicc", AiDAO.listAll(area, cafe, food));
			System.out.println("aicc isEmpty 에서 출력");
			area2 = AiDAO.listAll(area, cafe, food).get(0).getEvent_area2();
			System.out.println("area2 isEmpty에서 출력"+area2);
		} else {
			model.addAttribute("aicc", AiDAO.list(area, thema, thema1, thema2, thema3, thema5, thema6, thema8));
			area2 = AiDAO.list(area, thema, thema2, thema3, thema5, thema6, thema8, thema1).get(0).getEvent_area2();
		}

		System.out.println("area2 : " + area2);
		if (AiDAO.list2(area, thema, thema2, thema3, thema5, thema6, thema8, thema1, area2).isEmpty()) {

			model.addAttribute("aicc2", AiDAO.listAll2(area, cafe, food));
			System.out.println("aicc2 isEmpty 에서 출력");
		} else {
			model.addAttribute("aicc2",
					AiDAO.list2(area, thema, thema1, thema2, thema3, thema5, thema6, thema8, area2));
		}
		if (AiDAO.listCafeArea2(area2, cafe).isEmpty()) {
			System.out.println("aiccCafe isEmpty 에서 출력");
			
			model.addAttribute("aiccCafe", AiDAO.listCafe(area, cafe));
		} else {
			model.addAttribute("aiccCafe", AiDAO.listCafeArea2(area2, cafe));
		}
		if (AiDAO.listFoodArea2(area2, food).isEmpty()) {
			System.out.println("aiccFood isEmpty 에서 출력");
			model.addAttribute("aiccFood", AiDAO.listFood(area, food));
			model.addAttribute("aiccFood2", AiDAO.listFood(area, food));
		} else {
			model.addAttribute("aiccFood", AiDAO.listFoodArea2(area2, food));
			model.addAttribute("aiccFood2", AiDAO.listFoodArea2(area2, food));
		}

		return "thymeleaf/map/map";
	}

	@PostMapping("/cours_Save")
	public String cours_Save(HttpServletRequest request, Model model) {
		String cours1 = request.getParameter("cours1");
		String cours2 = request.getParameter("cours2");
		String cours3 = request.getParameter("cours3");
		String cours4 = request.getParameter("cours4");
		String cours5 = request.getParameter("cours5");
		String cours6 = request.getParameter("cours6");
		String memberId = request.getParameter("memberId");
		String day = request.getParameter("day");
		
		model.addAttribute("aicc", AiDAO.listCourse(cours1));
		model.addAttribute("aiccFood", AiDAO.listCourse(cours2));
		model.addAttribute("aicc2", AiDAO.listCourse(cours3));
		model.addAttribute("aiccFood2", AiDAO.listCourse(cours4));
		model.addAttribute("aiccCafe", AiDAO.listCourse(cours5));
		model.addAttribute("totalDistance", cours6);
		model.addAttribute("memberId", memberId);
		model.addAttribute("day", day);
		if (day==null || day=="") {
			day="0";
		}
		return "thymeleaf/aicc/saveCourse";
	}
	@GetMapping("/Course_view")
	public String Course_view(HttpServletRequest request, Model model) {
		
		String num = request.getParameter("num");
		model.addAttribute("list",AiDAO.Course_view_list(num));
		String number = AiDAO.Course_view_list(num).get(0).getCourse_number();
		System.out.println("number"+number);
		String[] values = number.split(",");
		model.addAttribute("aicc", AiDAO.listCourse(values[0]));
		model.addAttribute("aiccFood", AiDAO.listCourse(values[1]));
		model.addAttribute("aicc2", AiDAO.listCourse(values[2]));
		model.addAttribute("aiccFood2", AiDAO.listCourse(values[3]));
		model.addAttribute("aiccCafe", AiDAO.listCourse(values[4]));
		model.addAttribute("totalDistance", AiDAO.Course_view_list(num).get(0).getCourse_distance());
		model.addAttribute("memberId", AiDAO.Course_view_list(num).get(0).getMember_Id());
		
		return "thymeleaf/aicc/Course_view";
	}
	@GetMapping("/Course_delete")
	public String Course_delete(HttpServletRequest request, Model model) {
		
		String num = request.getParameter("num");
		model.addAttribute("list",AiDAO.Course_delete(num));
		String msg = "1";
	    if (msg != null && msg.equals("1")) {
	        model.addAttribute("confirmMessage", "코스 삭제가 완료되었습니다.");
	    }
		
		return "thymeleaf/home/home";
	}
	@PostMapping("/cours_Save_insert")
	public String cours_Save_insert(HttpServletRequest request, Model model) {
		String cours1 = request.getParameter("cours1");
		String cours2 = request.getParameter("cours2");
		String cours3 = request.getParameter("cours3");
		String cours4 = request.getParameter("cours4");
		String cours5 = request.getParameter("cours5");
		String numder=cours1+","+cours2+","+cours3+","+cours4+","+cours5;
		String memberId = request.getParameter("memberId");
		String day = request.getParameter("day");
		if (day==null || day=="") {
			day="0";
		}
		String Course_name = request.getParameter("Course_name");
		String Course_thema = request.getParameter("Course_thema");
		String Course_Area = request.getParameter("Course_Area");
		String Course_content = request.getParameter("Course_content");
		String Course_distance = request.getParameter("Course_distance");
	
		System.out.println(AiDAO.save_course_insert(memberId,Course_name,Course_thema,Course_Area,Course_content,Course_distance,day,numder));
		model.addAttribute("memberId", memberId);
		System.out.println("memberId : " +memberId);
		String msg = "1";
	    if (msg != null && msg.equals("1")) {
	        model.addAttribute("confirmMessage", "코스를 확인하러 마이페이지로 이동하시겠습니까?");
	    }
		
		return "thymeleaf/aicc/aicc";
	}
	
	@PostMapping("/cours_Save_update")
	public String cours_Save_update(HttpServletRequest request, Model model) {
		
		String num = request.getParameter("num");
		model.addAttribute("list",AiDAO.Course_view_list(num));
		String number = AiDAO.Course_view_list(num).get(0).getCourse_number();
		System.out.println("number"+number);
		String[] values = number.split(",");
		model.addAttribute("aicc", AiDAO.listCourse(values[0]));
		model.addAttribute("aiccFood", AiDAO.listCourse(values[1]));
		model.addAttribute("aicc2", AiDAO.listCourse(values[2]));
		model.addAttribute("aiccFood2", AiDAO.listCourse(values[3]));
		model.addAttribute("aiccCafe", AiDAO.listCourse(values[4]));
		model.addAttribute("totalDistance", AiDAO.Course_view_list(num).get(0).getCourse_distance());
		model.addAttribute("memberId", AiDAO.Course_view_list(num).get(0).getMember_Id());
		
		return "thymeleaf/aicc/Course_update";
	}
	@PostMapping("/cours_Save_update_result")
	public String cours_Save_update_result(HttpServletRequest request, Model model) {
		
		String num = request.getParameter("num");
		String Course_name = request.getParameter("Course_name");
		String Course_content = request.getParameter("Course_content");
		
		model.addAttribute("update",AiDAO.Course_update(Course_name,Course_content,num));
		model.addAttribute("list",AiDAO.Course_view_list(num));
		
		String number = AiDAO.Course_view_list(num).get(0).getCourse_number();
		System.out.println("number"+number);
		String[] values = number.split(",");
		model.addAttribute("aicc", AiDAO.listCourse(values[0]));
		model.addAttribute("aiccFood", AiDAO.listCourse(values[1]));
		model.addAttribute("aicc2", AiDAO.listCourse(values[2]));
		model.addAttribute("aiccFood2", AiDAO.listCourse(values[3]));
		model.addAttribute("aiccCafe", AiDAO.listCourse(values[4]));
		model.addAttribute("totalDistance", AiDAO.Course_view_list(num).get(0).getCourse_distance());
		model.addAttribute("memberId", AiDAO.Course_view_list(num).get(0).getMember_Id());
		
		return "redirect:/Course_view?num="+num;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 여행 글 업로드
	@PostMapping("/Event_write222")

	public String Event_write(HttpServletRequest request, Model model,
			@RequestParam("Event_thumbnail") MultipartFile file) {
		EventDTO dto = new EventDTO();
		try {

			StringBuilder fileNames = new StringBuilder();

			String fileEmpty = "file_empty";
			String thumbnail = "user_rabbit.jpg";

			// 사용자가 파일을 넣었을 때
			if (!file.isEmpty()) {

				Path fileNameAndPath = Paths.get(UPLOAD_EVENT_DIRECTORY, file.getOriginalFilename());
				// 설정한 디렉토리에 파일 업로드
				fileNames.append(file.getOriginalFilename());
				byte[] fileSize = file.getBytes(); // 이미지에 대한 정보 값을 바이트 배열로 가져온다.
				Files.write(fileNameAndPath, fileSize);

				thumbnail = fileNames.toString();

			} else {
				model.addAttribute("Member_profileimage", fileEmpty); // 이미지 이름 저장
			}

			dto.setEvent_address(request.getParameter("Event_address"));
			dto.setEvent_area(request.getParameter("Event_area"));
			dto.setEvent_area2(request.getParameter("Event_area2"));
			dto.setEvent_category(request.getParameter("Event_category"));
			dto.setEvent_content(request.getParameter("Event_content"));
			dto.setEvent_endtime(request.getParameter("Event_endtime"));
			dto.setEvent_host(request.getParameter("Event_host"));
			dto.setEvent_mapX(request.getParameter("Event_mapX"));
			dto.setEvent_mapY(request.getParameter("Event_mapY"));
			dto.setEvent_name(request.getParameter("Event_name"));
			dto.setEvent_parking(request.getParameter("Event_parking"));
			dto.setEvent_rest(request.getParameter("Event_rest"));
			dto.setEvent_phone(request.getParameter("Event_phone"));
			dto.setEvent_starttime(request.getParameter("Event_starttime"));
			dto.setEvent_tag(request.getParameter("Event_tag"));
			dto.setEvent_tag2(request.getParameter("Event_tag2"));
			dto.setEvent_tag3(request.getParameter("Event_tag3"));
			dto.setEvent_tag4(request.getParameter("Event_tag4"));
			dto.setEvent_tag5(request.getParameter("Event_tag5"));
			dto.setEvent_thumbnail(thumbnail);
			dto.setEvent_time(request.getParameter("Event_time"));
			dto.setEvent_title(request.getParameter("Event_title"));
			// eventDAO.event_write(dto);

			System.out.println("--글작성 완료--");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/info?msg=1";
	}

	// 여행 글 수정 업로드
	@PostMapping("/Event_write_update_write222")

	public String Event_write_update_write(HttpServletRequest request, Model model,
			@RequestParam("Event_thumbnail") MultipartFile file) {
		EventDTO dto = new EventDTO();
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println(num);
		try {

			StringBuilder fileNames = new StringBuilder();

			String fileEmpty = "file_empty";
			String thumbnail = "user_rabbit.jpg";

			// 사용자가 파일을 넣었을 때
			if (!file.isEmpty()) {

				Path fileNameAndPath = Paths.get(UPLOAD_EVENT_DIRECTORY, file.getOriginalFilename());
				// 설정한 디렉토리에 파일 업로드
				fileNames.append(file.getOriginalFilename());
				byte[] fileSize = file.getBytes(); // 이미지에 대한 정보 값을 바이트 배열로 가져온다.
				Files.write(fileNameAndPath, fileSize);

				thumbnail = fileNames.toString();

			} else {
				model.addAttribute("Member_profileimage", fileEmpty); // 이미지 이름 저장
			}
			dto.setEvent_num(num);
			dto.setEvent_address(request.getParameter("Event_address"));
			dto.setEvent_area(request.getParameter("Event_area"));
			dto.setEvent_area2(request.getParameter("Event_area2"));
			dto.setEvent_category(request.getParameter("Event_category"));
			dto.setEvent_content(request.getParameter("Event_content"));
			dto.setEvent_endtime(request.getParameter("Event_endtime"));
			dto.setEvent_host(request.getParameter("Event_host"));
			dto.setEvent_mapX(request.getParameter("Event_mapX"));
			dto.setEvent_mapY(request.getParameter("Event_mapY"));
			dto.setEvent_name(request.getParameter("Event_name"));
			dto.setEvent_parking(request.getParameter("Event_parking"));
			dto.setEvent_rest(request.getParameter("Event_rest"));
			dto.setEvent_phone(request.getParameter("Event_phone"));
			dto.setEvent_starttime(request.getParameter("Event_starttime"));
			dto.setEvent_tag(request.getParameter("Event_tag"));
			dto.setEvent_tag2(request.getParameter("Event_tag2"));
			dto.setEvent_tag3(request.getParameter("Event_tag3"));
			dto.setEvent_tag4(request.getParameter("Event_tag4"));
			dto.setEvent_tag5(request.getParameter("Event_tag5"));
			dto.setEvent_thumbnail(thumbnail);
			dto.setEvent_time(request.getParameter("Event_time"));
			dto.setEvent_title(request.getParameter("Event_title"));

			// eventDAO.event_write_update(dto);
			System.out.println("뭐가 들어있는지 보자" + dto.getEvent_num());
			System.out.println("--글수정 완료--");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/info";
	}

}