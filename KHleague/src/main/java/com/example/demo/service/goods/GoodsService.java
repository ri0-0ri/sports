package com.example.demo.service.goods;

import java.util.List;

import com.example.demo.model.goods.GoodsDTO;

public interface GoodsService {
	List<GoodsDTO> getgoods();

	void putWish(int goodsnum, String userid);
}
