package com.standout.sopang.member.dao;

import java.util.Map;

import com.standout.sopang.member.dto.MemberDTO;
import org.springframework.dao.DataAccessException;

import com.standout.sopang.member.vo.MemberVO;

public interface MemberDAO {
	//�α���
	public MemberVO login(Map loginMap) throws DataAccessException;

	//ȸ������
	public void insertNewMember(MemberVO memberVO) throws DataAccessException;
	
	//���̵� �ߺ�Ȯ��
	public String selectOverlappedID(String id) throws DataAccessException;
}