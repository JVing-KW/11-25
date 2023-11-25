package com.standout.sopang.mypage.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.standout.sopang.common.base.BaseController;
import com.standout.sopang.member.vo.MemberVO;
import com.standout.sopang.mypage.service.MyPageService;
import com.standout.sopang.order.vo.OrderVO;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("myPageController")
@RequestMapping(value="/mypage")
public class MyPageControllerImpl extends BaseController  implements MyPageController{
	@Autowired
	private MyPageService myPageService;
	
	@Autowired
	private MemberVO memberVO;
	
	//�ֹ����
	@Override
	@RequestMapping(value="/listMyOrderHistory" ,method = RequestMethod.GET)
	public String listMyOrderHistory(@RequestParam Map<String, String> dateMap, Model model, RedirectAttributes redirectAttributes,
										   HttpServletRequest request, HttpServletResponse response)  throws Exception {
		HttpSession session=request.getSession();

		//memberInfo�� member_id get
		memberVO=(MemberVO)session.getAttribute("memberInfo");
			if(memberVO != null) {
			String  member_id=memberVO.getMember_id();

			//��ȸ�Ⱓ fixedSearchPeriod get
			String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");
			//��ȸ �Ⱓ �ʱ�ȭ
			String beginDate=null,endDate=null;
			String [] tempDate=calcSearchPeriod(fixedSearchPeriod).split(",");
			beginDate=tempDate[0];
			endDate=tempDate[1];
			//��ȸ�Ⱓ�� member_id�� dateMap�� put�� ��ȸ
			dateMap.put("beginDate", beginDate);
			dateMap.put("endDate", endDate);
			dateMap.put("member_id", member_id);
			List<OrderVO> myOrderHistList=myPageService.listMyOrderHistory(dateMap);
			//�˻����ڸ� ��,��,�Ϸ� �и��ؼ� ȭ�鿡 ����
			String beginDate1[]=beginDate.split("-");
			String endDate1[]=endDate.split("-");

				model.addAttribute("beginYear",beginDate1[0]);
				model.addAttribute("beginYear",beginDate1[1]);
				model.addAttribute("beginYear",beginDate1[2]);
				model.addAttribute("endYear",endDate1[0]);
				model.addAttribute("endYear",endDate1[1]);
				model.addAttribute("endYear",endDate1[2]);
				model.addAttribute("myOrderHistList", myOrderHistList);

			return "/mypage/listMyOrderHistory";
		}
		else {
			return "redirect:/member/login";
		}
	}
	
	
	//�ֹ����
	@Override
	@RequestMapping(value="/cancelMyOrder" ,method = RequestMethod.POST)
	public String cancelMyOrder(@RequestParam("order_id")  String order_id,Model model,RedirectAttributes redirectAttributes,
			                         HttpServletRequest request, HttpServletResponse response)  throws Exception {
		ModelAndView mav = new ModelAndView();
		//�ֹ� id order_id�� db���� �� cancel_order message ����
		myPageService.cancelOrder(order_id);
		model.addAttribute("message", "cancel_order");
		mav.setViewName("redirect:/mypage/listMyOrderHistory.do");
		return "redirect:/mypage/listMyOrderHistory";
	}


	@Override
	@RequestMapping(value="/returnMyOrder" ,method = RequestMethod.POST)
	public String returnMyOrder(String order_id, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//�ֹ� id order_id�� db���� �� returning_goods message ����
		myPageService.returnOrder(order_id);
		model.addAttribute("message", "returning_goods");
		return "redirect:/mypage/listMyOrderHistory.do";
	}
	//��ǰ
//	@Override
//	@RequestMapping(value="/returnMyOrder" ,method = RequestMethod.POST)
//	public String returnMyOrder(@RequestParam("order_id")  String order_id,
//								RedirectAttributes redirectAttributes,Model model ,HttpServletRequest request, HttpServletResponse response)  throws Exception {
//		//�ֹ� id order_id�� db���� �� returning_goods message ����
//		myPageService.returnOrder(order_id);
//		model.addAttribute("message", "returning_goods");
//		return "redirect:/mypage/listMyOrderHistory.do";
//	}
	
	
	//��ȯ

	@Override
	@RequestMapping(value="/exchangeMyOrder" ,method = RequestMethod.POST)
	public String exchangeMyOrder(String order_id, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//�ֹ� id order_id�� db���� �� exchange_goods message ����
		myPageService.exchangeOrder(order_id);
		model.addAttribute("message", "exchange_goods");
		return "redirect:/mypage/listMyOrderHistory";
	}

	
	//������

	@Override
	@RequestMapping(value="/myDetailInfo" ,method = RequestMethod.GET)
	public String myDetailInfo(HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		//�ܼ��� �������� �̵�, myDetailInfo
		return "/mypage/myDetailInfo";
	}

	
	
	//�� ���� ����
	@Override
	@RequestMapping(value="/modifyMyInfo" ,method = RequestMethod.POST)
	public ResponseEntity modifyMyInfo(
			@RequestParam("member_pw")  String member_pw,
			@RequestParam("hp1")  String hp1,
			@RequestParam("zipcode")  String zipcode,
			@RequestParam("address")  String address,
			@RequestParam("subaddress")  String subaddress,
			               HttpServletRequest request, HttpServletResponse response)  throws Exception {
		Map<String,String> memberMap=new HashMap<String,String>();
		
		HttpSession session=request.getSession();
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String  member_id=memberVO.getMember_id();
		
		//�޾ƿ� ���� memberMap�� put
		memberMap.put("member_pw",member_pw);
		memberMap.put("hp1",hp1);
		memberMap.put("zipcode",zipcode);
		memberMap.put("address",address);
		memberMap.put("subaddress",subaddress);
		memberMap.put("member_id", member_id);
		
		//memberMap�� ������ db����
		memberVO=(MemberVO)myPageService.modifyMyInfo(memberMap);
		
		//������ ȸ�� ������ �ٽ� ���ǿ� �����Ѵ�.
		session.removeAttribute("memberInfo");
		session.setAttribute("memberInfo", memberVO);
		
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		
		//�Ϸ��� message mod_success ����
		message  = "mod_success";
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}	
	
	
	//ȸ��Ż��
	@Override
	@RequestMapping(value="/deleteMember" ,method = RequestMethod.POST)
	public ResponseEntity deleteMember(@RequestParam("member_id")  String member_id, HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		//@RequestParam���� member_id�� db���� ����
		myPageService.deleteMember(member_id);
		
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		
		//�Ϸ� ��  message delete_success ����
		message  = "delete_success";
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	
}