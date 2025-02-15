package com.example.demo.mapper.goods;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.goods.BuyListDTO;
import com.example.demo.model.goods.Criteria;
import com.example.demo.model.goods.GoodsDTO;

@Mapper
public interface GoodsMapper {

	List<GoodsDTO> getgoods();

	void putWish(int goodsnum, String userid);

	List<GoodsDTO> getWishgoods(String userid);

	void putBuy(int goodsnum, String userid, String size, int quantity);

	List<BuyListDTO> getBuygoods(String userid);

	GoodsDTO getgoodsBycart(int goodsnum);

	int putBuy_modify(int goodsnum, String userid, String size, int quantity, int buynum);

	BuyListDTO getBuygoodsBybuynum(int buynum);

	void deleteWish(int wishnum);

	Integer getwishnumBygoodsnum(int goodsnum, String userid); 

	void deleteBuy(int buynum);

	Integer getbuynumBygoodsnum(int goodsnum, String userid, String size);

	List<GoodsDTO> getgoodsBygoodsnum(int goodsnum);

	List<GoodsDTO> getgoods(Criteria cri);

	GoodsDTO getgoodsbygoodsname(String eventitem);
	
}
