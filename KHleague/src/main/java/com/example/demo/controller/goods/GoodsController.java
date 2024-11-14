package com.example.demo.controller.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.goods.Criteria;
import com.example.demo.model.goods.GoodsDTO;
import com.example.demo.service.goods.GoodsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("goods/*")
public class GoodsController {
	
	@Autowired
	private GoodsService service;
	
	@GetMapping("goods")
	public void goods(Model model, HttpSession session, HttpServletRequest req, Criteria cri) {
		String loginUser = (String)session.getAttribute("loginUser");		
		// view_type 설정
//		String viewType = req.getParameter("view_type");
//		cri.setView_type(viewType);
//		
	    // view_type 파라미터를 제대로 가져오는지 확인
	    String viewType = req.getParameter("view_type");
	    if (viewType != null) {
	        System.out.println("view_type: " + viewType);  // 확인용 로그
	    } else {
	        System.out.println("view_type is null");
	    }

	    // view_type을 Criteria에 설정
	    cri.setView_type(viewType);
	    
		List<GoodsDTO> goodsList = service.getgoods(cri);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("cri", cri);
	}
	
	@GetMapping("prize")
	public void prize() {
		
	}
}
