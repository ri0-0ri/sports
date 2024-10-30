package com.example.demo.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.goods.BuyListDTO;
import com.example.demo.model.goods.GoodsDTO;

@Mapper
public interface GoodsMapper {

	List<GoodsDTO> getgoods();

	void putWish(int goodsnum, String userid);

	List<GoodsDTO> getWishgoods(String userid);

	void putBuy(int goodsnum, String userid, String size, int quantity);

	List<BuyListDTO> getBuygoods(String userid);
	
}
