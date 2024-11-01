package com.example.demo.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.goods.GoodsMapper;
import com.example.demo.model.goods.BuyListDTO;
import com.example.demo.model.goods.GoodsDTO;

@Service
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	private GoodsMapper gmapper;

	@Override
	public List<GoodsDTO> getgoods() {
		return gmapper.getgoods();
	}

	@Override
	public void putWish(int goodsnum, String userid) {
		gmapper.putWish(goodsnum,userid);
	}

	@Override
	public List<GoodsDTO> getWishgoods(String userid) {
		return gmapper.getWishgoods(userid);
	}

	@Override
	public void putBuy(int goodsnum, String userid, String size, int quantity) {
		gmapper.putBuy(goodsnum, userid, size, quantity);		
	}

	@Override
	public List<BuyListDTO> getBuygoods(String userid) {
		return gmapper.getBuygoods(userid);
	}

	@Override
	public GoodsDTO getgoodsBycart(int goodsnum) {
		return gmapper.getgoodsBycart(goodsnum);
	}

	@Override
	public int putBuy_modify(int goodsnum, String userid, String size, int quantity, int buynum) {
		return gmapper.putBuy_modify(goodsnum, userid, size, quantity, buynum);		
	}

	@Override
	public BuyListDTO getBuygoodsBybuynum(int buynum) {
		return gmapper.getBuygoodsBybuynum(buynum);
	}

}
