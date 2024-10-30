package com.example.demo.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.goods.GoodsDTO;

@Mapper
public interface GoodsMapper {

	List<GoodsDTO> getgoods();

	void putWish(int goodsnum, String userid);
	
}
