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

}
