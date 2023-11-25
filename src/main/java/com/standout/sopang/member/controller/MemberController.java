package com.standout.sopang.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.standout.sopang.member.vo.MemberVO;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface MemberController {
	//�α���
	public String login(@RequestParam Map<String, String> loginMap, HttpServletRequest request,
							  HttpServletResponse response, Model model) throws Exception;

	//ȸ������
	public ResponseEntity addMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	//���̵� �ߺ�Ȯ��
	public ResponseEntity overlapped(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	//�α׾ƿ�
	public String logout(Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response)
			throws Exception;
}