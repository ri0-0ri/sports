package com.example.demo.controller.refund;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.UserDTO.UserDTO;
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
@RequestMapping("refund/*")
public class RefundController {
	
	@Autowired
	PaymentService pservice;
	
	@Autowired
	UserService uservice;
	
	@Autowired
	GoodsService gservice;
	
	@Autowired
	MoneyService mservice;

	@GetMapping("refund")
    public void refund(@RequestParam int ordernum, Model model) {
		OrderDTO order = pservice.getorderByordernum(ordernum);
		System.out.println("받아온order "+order);
		
		String userid = order.getUserid();
		UserDTO user = uservice.findUserById(userid);
		System.out.println("받아온user "+user);
		
		List<OrderListDTO> orderLists = pservice.getorderlistByordernum(ordernum, userid);
		System.out.println("받아온orderlists "+orderLists);
		
		List<MoneyDTO> moneylist = mservice.getmoneyByordernum(userid, ordernum);
		System.out.println("받아온money "+moneylist);
		
		List<GoodsDTO> goodsList = new ArrayList<>();
		for(OrderListDTO orderlist : orderLists) {
			int goodsnum = orderlist.getGoodsnum();
			List<GoodsDTO> goods = gservice.getgoodsBygoodsnum(goodsnum);
			goodsList.addAll(goods);
			System.out.println("받아온 goods "+goods);
		}
		
		model.addAttribute("order", order);
		model.addAttribute("user", user);
		model.addAttribute("orderLists", orderLists);
		model.addAttribute("moneylist", moneylist);
		model.addAttribute("goods", goodsList);
    }
	
	@PostMapping("okrefund")
	public ResponseEntity<String> okrefund(@RequestParam int ordernum, @RequestParam String userid, @RequestParam String reason) {
		System.out.println("오케이리펀드"+ordernum+userid+" 이유 : "+reason);
		// orders에서 해당 컬럼 찾아서 state > 주문취소로 바꾸기
		OrderDTO order = pservice.getorderByordernum(ordernum);
		order.setState("주문취소 "+reason);
		pservice.updateorder(order);
		
//		// orderList에서 해당 컬럼 삭제해주기
//		pservice.deleteorderList(ordernum);
		
		// money에서 ordernum들 다 찾아와서 +>-로, ->+로 바꿔주기
		List<MoneyDTO> moneys = mservice.getmoneyByordernum(userid, ordernum);
		System.out.println(moneys);
		MoneyDTO newMoney = new MoneyDTO();
		for(MoneyDTO money : moneys) {
			if(money.getMoneyname().contains("사용")) {
				newMoney.setMoneytype(money.getMoneytype());
				newMoney.setMoneyname(money.getMoneyname().replace("사용", "취소"));
				newMoney.setChangeMoney(money.getChangeMoney().replace("-", "+"));
				newMoney.setUserid(userid);
				newMoney.setOrdernum(ordernum);
				mservice.putmoney(newMoney);				
				// 유저 테이블 money 업데이트
				UserDTO user = uservice.findUserById(userid);
				if(money.getMoneytype().equals("포인트")) {				
					int pluspoint = Integer.parseInt(money.getChangeMoney().replace("-", "").trim());
					user.setUserpoint(user.getUserpoint()+pluspoint);;
				}
				else {					
					int plusreward = Integer.parseInt(money.getChangeMoney().replace("-", "").trim());
					user.setUserReward(user.getUserReward()+plusreward);
				}
				uservice.updateUser(user);				
			}
			
			if(money.getMoneyname().equals("결제 적립금")) {
				newMoney.setMoneytype(money.getMoneytype());
				newMoney.setMoneyname("결제 적립금 취소");
				newMoney.setChangeMoney(money.getChangeMoney().replace("+", "-"));
				newMoney.setUserid(userid);
				newMoney.setOrdernum(ordernum);
				mservice.putmoney(newMoney);
				// 유저 테이블 money 업데이트
				UserDTO user = uservice.findUserById(userid);
				int plusreward = Integer.parseInt(money.getChangeMoney().replace("-", "").trim());
				user.setUserReward(user.getUserReward()-plusreward);
				uservice.updateUser(user);				
			}
		}
		return ResponseEntity.ok("환불이 완료되었습니다.");
	}
	
}
