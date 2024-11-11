package com.example.demo.service.money;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.money.MoneyMapper;
import com.example.demo.mapper.user.UserMapper;
import com.example.demo.model.moneyDTO.MoneyDTO;

@Service
public class MoneyServiceImpl implements MoneyService{

	@Autowired
	MoneyMapper mmapper;
	
	@Autowired
	UserMapper umapper;
	
	@Override
	public void putmoney(MoneyDTO money) {
		mmapper.putmoney(money);
	}

	@Override
	public List<MoneyDTO> getmoney(String userid) {
		return mmapper.getmoney(userid);
	}

	@Override
	public void change_money() {
		List<MoneyDTO> moneys = mmapper.getallmoney();
		LocalDateTime now = LocalDateTime.now();
		MoneyDTO newmoney = new MoneyDTO();
		for(MoneyDTO money : moneys) {
			if("이벤트 적립금".equals(money.getMoneyname())) {
				LocalDateTime moneydate = money.getMoneydate();
				long daysBetween = ChronoUnit.DAYS.between(moneydate, now);
				
				String premoney = money.getChangeMoney().replace("+", "-");
				int premoneyint = Integer.parseInt(money.getChangeMoney().replace("+", ""));

				if(daysBetween>10) {
					// 해당 유저의 적립금 확인해보고
					String userid = money.getUserid();
					int userReward = umapper.findUserById(userid).getUserReward();
					if(userReward>premoneyint) {
						newmoney.setMoneytype("적립금");
			            newmoney.setMoneyname("적립금 소멸");
			            newmoney.setChangeMoney(premoney);
			            newmoney.setUserid(money.getUserid());
			            mmapper.putmoney(newmoney);
			            
			            money.setMoneyname("이벤트 적립금(소멸완료)");
			            mmapper.updatemoney(money);
			            
			            int newReward = userReward-premoneyint;
			            umapper.updateUserReward(newReward, userid);
			            System.out.println("적립금 삭제 완료~");
					}
				}
			}
		}
	}

}
