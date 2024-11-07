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
import com.example.demo.model.moneyDTO.MoneyDTO;
import com.example.demo.model.payment.OrderDTO;
import com.example.demo.model.payment.OrderListDTO;
import com.example.demo.service.goods.GoodsService;
import com.example.demo.service.money.MoneyService;
import com.example.demo.service.payment.PaymentService;
import com.example.demo.service.user.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("payment/*")
public class PaymentController {
	
	@Autowired
	GoodsService gservice;
	
	@Autowired
	UserService uservice;
	
	@Autowired
	PaymentService pservice;
	
	@Autowired
	MoneyService mservice;
	
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
//		    System.out.println(gservice.getgoodsBycart(buyListDTO.getGoodsnum()).getGoodsprice());
//		    System.out.println(gservice.getBuygoodsBybuynum(singleBuynum).getQuantity());
		}	    
	    model.addAttribute("goodsBuyinfo", goodsBuyinfo);
	    model.addAttribute("goodsMap", goodsMap);
	    model.addAttribute("total", totalAmount);

	    String userid = (String) session.getAttribute("loginUser");
	    UserDTO user = uservice.findUserById(userid);
	    model.addAttribute("user", user);
		
		
        return "payment/payment";
    }
	
	@GetMapping("single_payment")
	public void single_payment(@RequestParam String userid, @RequestParam String goodsnum, @RequestParam String size, @RequestParam String quantity, Model model){
		System.out.println(userid);
		System.out.println(goodsnum);
		System.out.println(size);
		System.out.println(quantity);
		
		int intgoodsnum = Integer.parseInt(goodsnum);		
		UserDTO user = uservice.findUserById(userid);
		model.addAttribute("user", user);
		GoodsDTO goods = gservice.getgoodsBycart(intgoodsnum);
		model.addAttribute("goods", goods);
		
		model.addAttribute("size", size);
		model.addAttribute("quantity", quantity);
//		return "payment/single_payment?userid=" + userid + "&goodsnum=" + goodsnum + "&size=" + size + "&quantity=" + quantity;
	}
	
	@PostMapping("okpayment")
	public String okpayment(OrderDTO order,  @RequestParam String userpoint, @RequestParam String userReward,
			@RequestParam String size, @RequestParam String quantity, @RequestParam String goodsnums) {
		System.out.println("Received buynum: " + order.getBuynum());
		String userid = order.getUserid();
		int point = 0;
		// sudannum 1은 포인트결제
		if(order.getSudannum()==1) {
			MoneyDTO money = new MoneyDTO();
			money.setUserid(userid);
			money.setMoneytype("포인트");
			// 넘어온 userpoint가 0보다 크면 충전했다는 뜻
			// 기존 유저가 가지고있는 포인트가 결제할 금액보다 작아서 그만큼 충전해야한다는의미
			// 기존 유저포인트 불러와서 충전할 포인트 더하고 총 금액 마이너스해야함
			if(Integer.parseInt(userpoint)>0) {			
				point = (uservice.findUserById(userid).getUserpoint()+Integer.parseInt(userpoint))-order.getTotalPrice();
				// money DB 바꿔주기(충전)
				money.setMoneyname("포인트 충전");
				money.setChangeMoney("+"+userpoint);
				mservice.putmoney(money);
			}
			// 넘어온 userpoint가 0보다 작거나 같다면 충전하지 않았다는뜻
			// 기존 유저포인트가져와서 결제할 금액 빼면 나머지 포인트
			else {
				point = uservice.findUserById(userid).getUserpoint()-order.getTotalPrice();
			}
			// money DB 바꿔주기(사용)
			money.setMoneyname("포인트 사용");
			money.setChangeMoney("-"+order.getTotalPrice());
			mservice.putmoney(money);
			System.out.println("남은포인트"+point);
			
			// 오더테이블에 추가
			pservice.putorder(order);
			// 포인트 추가
			uservice.putpoint(point, userid);
		}
		// sudannum 2는 간편결제
		else {
			// 오더테이블에 추가
			pservice.putorder(order);
		}
		
		// 유저 적립금 변경
		int minusReward = Integer.parseInt(userReward); // 삭제할 적립금
		int plusReward = (int)(order.getTotalPrice()*(10.0 / 100)); // 추가할 적립금
		int newReward = uservice.findUserById(userid).getUserReward()-minusReward+plusReward;
		System.out.println("삭제된 적립금:"+minusReward+" 추가할적립금:"+plusReward+" 최종적립금:"+newReward);
		uservice.updateUserReward(newReward, userid);
		
		// 적립금 테이블 변경		
		MoneyDTO money = new MoneyDTO();
		money.setUserid(userid);
		money.setMoneytype("적립금");
		// 만약 유저가 적립금을 사용했다면
		if(minusReward>0) {
			money.setMoneyname("적립금 사용");
			money.setChangeMoney("-"+minusReward);
			mservice.putmoney(money);
		}
		// 적립금을 추가만 한 경우
		money.setMoneyname("결제 적립금");
		money.setChangeMoney("+"+plusReward);
		mservice.putmoney(money);
		
		// buynums가 있다? 장바구니에서 결제한것
		// 장바구니(buylist) 객체를 결제했으면 주문내역(orderlist)로 옮겨주기
		if(order.getBuynum() != null && !order.getBuynum().isEmpty()) {		
			String buynums = order.getBuynum();
			String[] buynumArray = buynums.split("//");
			
			List<Integer> buynumList = new ArrayList<>();
			for (String buynum : buynumArray) {
				buynumList.add(Integer.parseInt(buynum));
			}
			
			for (int buynum : buynumList) {
				BuyListDTO buyListDTO = gservice.getBuygoodsBybuynum(buynum);
				System.out.println(buyListDTO);
				// 가져온 리스트 주문내역으로 옮기기
				OrderListDTO orderListDTO = new OrderListDTO();
				int ordernum = pservice.getorderlastnum();
				orderListDTO.setOrdernum(ordernum);
				orderListDTO.setBuynum(buynum);
				orderListDTO.setGoodsnum(buyListDTO.getGoodsnum());
				orderListDTO.setUserid(userid);
				orderListDTO.setSize(buyListDTO.getSize());
				orderListDTO.setQuantity(buyListDTO.getQuantity());				
				pservice.putorderList(orderListDTO);
				
				// 장바구니에서 삭제
				pservice.deletebuyList(buynum);
			}
		}
		// buynums가 없으면 직접결제한것
		else {			
			 OrderListDTO orderListDTOsin = new OrderListDTO();
			 int ordernum = pservice.getorderlastnum();
			 orderListDTOsin.setOrdernum(ordernum);
			 orderListDTOsin.setGoodsnum(Integer.parseInt(goodsnums));
			 orderListDTOsin.setUserid(userid);
			 orderListDTOsin.setSize(size);
			 orderListDTOsin.setQuantity(Integer.parseInt(quantity));
			 pservice.putorderList(orderListDTOsin);		 
		}
		
		return "redirect:/mypage/mypage_order";		
	}
	
}
