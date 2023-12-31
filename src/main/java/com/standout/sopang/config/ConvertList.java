package com.standout.sopang.config;


import com.standout.sopang.cart.dto.CartDTO;
import com.standout.sopang.cart.vo.CartVO;
import com.standout.sopang.goods.dto.GoodsDTO;
import com.standout.sopang.goods.dto.ImageFileDTO;
import com.standout.sopang.goods.vo.GoodsVO;
import com.standout.sopang.goods.vo.ImageFileVO;
import com.standout.sopang.member.dto.MemberDTO;
import com.standout.sopang.member.vo.MemberVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConvertList {

    @Autowired
    ModelMapper modelMapper;

    public List<GoodsDTO> GoodsConvertDTO(List<GoodsVO> goodsList ) {

        return goodsList.stream()
                .map(goods -> modelMapper.map(goods, GoodsDTO.class))
                .collect(Collectors.toList());
    }

    public List<ImageFileDTO> ImgageConvertImageDTO(List<ImageFileVO> imageList ) {

        return imageList.stream()
                .map(image -> modelMapper.map(image, ImageFileDTO.class))
                .collect(Collectors.toList());
    }
    public List<CartDTO> CartConvertDTO(List<CartVO> goodsList ) {

        return goodsList.stream()
                .map(cart -> modelMapper.map(cart, CartDTO.class))
                .collect(Collectors.toList());
    }
    public ArrayList<MemberDTO> MemberConvertDTO(List<MemberVO> memberList ) {

        return memberList.stream()
                .map(member -> modelMapper.map(member, MemberDTO.class))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
