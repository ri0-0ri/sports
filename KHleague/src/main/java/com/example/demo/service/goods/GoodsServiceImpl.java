package com.example.demo.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.goods.GoodsMapper;
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
		System.out.println(userid);
		gmapper.putWish(goodsnum,userid);
	}

}
