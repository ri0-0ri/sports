package com.example.demo.controller.payment;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.UserDTO.UserDTO;
import com.example.demo.model.goods.BuyListDTO;
import com.example.demo.model.goods.GoodsDTO;
import com.example.demo.model.payment.KakaoPayReadyDTO;
import com.example.demo.service.goods.GoodsService;
import com.example.demo.service.payment.PayService;
import com.example.demo.service.user.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("payment/*")
public class PaymentController {
	
	@Autowired
	GoodsService gservice;
	
	@Autowired
	UserService uservice;
	
	@GetMapping("payment")
	public String payment(@RequestParam List<Integer> buynum, Model model, HttpSession session) {	
		long totalAmount = 5000;
		List<BuyListDTO> goodsBuyinfo = new ArrayList<>();
		Map<Integer, GoodsDTO> goodsMap = new HashMap<>();
		for (Integer singleBuynum : buynum) {
		    BuyListDTO buyListDTO = gservice.getBuygoodsBybuynum(singleBuynum);
		    GoodsDTO goodsDTO = gservice.getgoodsBycart(buyListDTO.getGoodsnum());
		    goodsBuyinfo.add(buyListDTO);
		    goodsMap.put(buyListDTO.getGoodsnum(), goodsDTO);
		    totalAmount += gservice.getgoodsBycart(buyListDTO.getGoodsnum()).getGoodsprice() * gservice.getBuygoodsBybuynum(singleBuynum).getQuantity();
		    System.out.println(gservice.getgoodsBycart(buyListDTO.getGoodsnum()).getGoodsprice());
		    System.out.println(gservice.getBuygoodsBybuynum(singleBuynum).getQuantity());
		}	    
	    model.addAttribute("goodsBuyinfo", goodsBuyinfo);
	    model.addAttribute("goodsMap", goodsMap);
	    model.addAttribute("total", totalAmount);

	    String userid = (String) session.getAttribute("loginUser");
	    UserDTO user = uservice.findUserById(userid);
	    model.addAttribute("user", user);
		
		
        return "payment/payment";
    }
	
	@Autowired
	PayService pservice;
	
	 @PostMapping("open_kakao")
	 public @ResponseBody KakaoPayReadyDTO kakaopay(@RequestBody Map<String, Object> params){
		 System.out.println(params);
		 KakaoPayReadyDTO res = pservice.kakaopay(params);
		 // log.info(res.toString());
		 return res;
	 }
	 	
//	@PostMapping("open_kakao")
//	@ResponseBody
//	public String kakaopay() {
//		try {
//			URL url = new URL("https://open-api.kakaopay.com/online/v1/payment/ready");
//			HttpURLConnection con = (HttpURLConnection) url.openConnection();
//			con.setRequestMethod("POST");
//			con.setRequestProperty("Authorization", "SECRET_KEY DEV082C51D29EDB518D5AB58F83F9F9249C1B2A8");
//			con.setRequestProperty("Content-Type", "application/json");
//			con.setDoOutput(true);
//			
//			String paramiter = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=초코파이&quantity=1&total_amount=200&vat_amount=200&tax_free_amount=0&approval_url=https://developers.kakao.com/success&fail_url=https://developers.kakao.com/fail&cancel_url=https://developers.kakao.com/cancel";
//
//			
//			OutputStream give = con.getOutputStream(); // 전깃줄만들고
//			DataOutputStream givedata = new DataOutputStream(give); // 데이터주는애
//			givedata.writeBytes(paramiter); // 데이터주는애한테 파라미터를 쥐어주고
//			givedata.close(); // 닫아주면서 데이터주는애가 가지고있는 파라미터를 전깃줄에 태워 보내요
//			
//			// 실제 통신하는부분
//			int result = con.getResponseCode();
//			InputStream receive; // 데이터 받는애
//			// 결과가 정상이면
//			if(result==200) {
//				receive = con.getInputStream();
//			}
//			else {
//				receive = con.getErrorStream();
//			}
//			InputStreamReader reader = new InputStreamReader(receive); // 받는애가 받은걸 읽을 줄 아는애
//			BufferedReader change = new BufferedReader(reader); // 형변환하는애
//			return change.readLine(); // 문자열로 받습니다
//			
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
}
