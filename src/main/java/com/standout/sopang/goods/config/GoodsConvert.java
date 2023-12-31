package com.standout.sopang.goods.config;

import com.standout.sopang.goods.dto.GoodsDTO;
import com.standout.sopang.goods.dto.ImageFileDTO;
import com.standout.sopang.goods.vo.GoodsVO;
import com.standout.sopang.goods.vo.ImageFileVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsConvert {
    @Autowired
    ModelMapper modelMapper;
    public List<GoodsDTO> convertDTO(List<GoodsVO> goodsList ) {

        return goodsList.stream()
                .map(goods -> modelMapper.map(goods, GoodsDTO.class))
                .collect(Collectors.toList());
    }

    public List<ImageFileDTO> convertImageDTO(List<ImageFileVO> imageList ) {

        return imageList.stream()
                .map(image -> modelMapper.map(image, ImageFileDTO.class))
                .collect(Collectors.toList());
    }



}
