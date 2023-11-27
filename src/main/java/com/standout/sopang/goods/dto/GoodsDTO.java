package com.standout.sopang.goods.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;



@Getter
@Setter
public class GoodsDTO {
    private int goods_id;
    private String goods_sort;
    private String goods_status;
    private String goods_title;
    private String goods_intro;
    private int goods_sales_price;
    private String goods_delivery_price;
    private Date goods_delivery_date;
    private Date creDate;
    private String goods_fileName;



}
