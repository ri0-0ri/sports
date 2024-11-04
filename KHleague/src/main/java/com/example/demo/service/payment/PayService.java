package com.example.demo.service.payment;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.payment.KakaoPayReadyDTO;

@Service
public class PayService {

	public KakaoPayReadyDTO kakaopay(Map<String, Object> params) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "SECRET_KEY DEV082C51D29EDB518D5AB58F83F9F9249C1B2A8");
		
		MultiValueMap<String, Object> payParams = new LinkedMultiValueMap<String, Object>();
		
        if (params.get("item_name") == null || params.get("quantity") == null || 
                params.get("total_amount") == null || params.get("vat_amount") == null ||
                params.get("tax_free_amount") == null) {
                throw new IllegalArgumentException("필수 파라미터가 누락되었습니다.");
            }
		
		payParams.add("cid", "TC0ONETIME");
		payParams.add("partner_order_id", "partner_order_id");
		payParams.add("partner_user_id", "partner_user_id");
		payParams.add("item_name", params.get("item_name"));
		payParams.add("quantity", params.get("quantity"));
		payParams.add("total_amount", params.get("total_amount"));
		payParams.add("vat_amount", params.get("vat_amount"));
		payParams.add("tax_free_amount", params.get("tax_free_amount"));
		payParams.add("approval_url", "http://localhost:8080/");
		payParams.add("fail_url", "http://localhost:8080/");
		payParams.add("cancel_url", "http://localhost:8080/");		
		
		System.out.println("Request Params: " + payParams);
		
		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(payParams, headers);
		
		RestTemplate template = new RestTemplate();
		String url = "https://open-api.kakaopay.com/online/v1/payment/ready";
		KakaoPayReadyDTO res = template.postForObject(url, request, KakaoPayReadyDTO.class);
		
		return res;
	}

}
