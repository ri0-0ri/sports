package com.example.demo.service.goods;

import java.util.List;

import com.example.demo.model.goods.BuyListDTO;
import com.example.demo.model.goods.GoodsDTO;

public interface GoodsService {
	List<GoodsDTO> getgoods();

	void putWish(int goodsnum, String userid);

	List<GoodsDTO> getWishgoods(String userid);

	void putBuy(int goodsnum, String userid, String size, int quantity);

	List<BuyListDTO> getBuygoods(String userid);
}
