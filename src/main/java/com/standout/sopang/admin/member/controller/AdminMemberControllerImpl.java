package com.standout.sopang.admin.member.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.standout.sopang.member.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.standout.sopang.admin.member.service.AdminMemberService;
import com.standout.sopang.common.base.BaseController;
import com.standout.sopang.member.vo.MemberVO;

@Controller("adminMemberController")
@RequestMapping(value="/admin/member")
public class AdminMemberControllerImpl extends BaseController  implements AdminMemberController{
	@Autowired
	private AdminMemberService adminMemberService;


	@Override
	public String adminGoodsMain(Map<String, String> dateMap, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		//fixedSearchPeriod값을 받아 저장
		String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");
		//기간 초기화
		String beginDate=null,endDate=null;

		//fixedSearchPeriod값 가공해 dateMap에 put
		String [] tempDate=calcSearchPeriod(fixedSearchPeriod).split(",");
		beginDate=tempDate[0];
		endDate=tempDate[1];
		dateMap.put("beginDate", beginDate);
		dateMap.put("endDate", endDate);

		//condMap에 put 후 listMember수행.
		HashMap<String,Object> condMap=new HashMap<String,Object>();
		condMap.put("beginDate",beginDate);
		condMap.put("endDate", endDate);
		ArrayList<MemberDTO> member_list=adminMemberService.listMember(condMap);

		//리턴된 회원리스트 member_list를  mav의 member_list에 부여

		model.addAttribute("member_list", member_list);

		//날짜형식지정
		String beginDate1[]=beginDate.split("-");
		String endDate2[]=endDate.split("-");

		model.addAttribute("beginYear",beginDate1[0]);
		model.addAttribute("beginMonth",beginDate1[1]);
		model.addAttribute("beginDay",beginDate1[2]);
		model.addAttribute("endYear",endDate2[0]);
		model.addAttribute("endMonth",endDate2[1]);
		model.addAttribute("endDay",endDate2[2]);

		return "/admin/member/adminMemberMain";
	}

	//회원관리
//	@RequestMapping(value="/adminMemberMain" ,method={RequestMethod.POST,RequestMethod.GET})
//	public String adminGoodsMain(@RequestParam Map<String, String> dateMap, Model model,
//			                           HttpServletRequest request, HttpServletResponse response)  throws Exception{
//		String viewName=(String)request.getAttribute("viewName");
//		ModelAndView mav = new ModelAndView(viewName);
//
//		//fixedSearchPeriod값을 받아 저장
//		String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");
//
//		//기간 초기화
//		String beginDate=null,endDate=null;
//
//		//fixedSearchPeriod값 가공해 dateMap에 put
//		String [] tempDate=calcSearchPeriod(fixedSearchPeriod).split(",");
//		beginDate=tempDate[0];
//		endDate=tempDate[1];
//		dateMap.put("beginDate", beginDate);
//		dateMap.put("endDate", endDate);
//
//
//		//condMap에 put 후 listMember수행.
//		HashMap<String,Object> condMap=new HashMap<String,Object>();
//		condMap.put("beginDate",beginDate);
//		condMap.put("endDate", endDate);
//		ArrayList<MemberVO> member_list=adminMemberService.listMember(condMap);
//
//		//리턴된 회원리스트 member_list를  mav의 member_list에 부여
//		mav.addObject("member_list", member_list);
//
//		//날짜형식지정
//		String beginDate1[]=beginDate.split("-");
//		String endDate2[]=endDate.split("-");
//		mav.addObject("beginYear",beginDate1[0]);
//		mav.addObject("beginMonth",beginDate1[1]);
//		mav.addObject("beginDay",beginDate1[2]);
//		mav.addObject("endYear",endDate2[0]);
//		mav.addObject("endMonth",endDate2[1]);
//		mav.addObject("endDay",endDate2[2]);
//
//		return "/admin/member/adminMemberMain";
//	}
	
}
