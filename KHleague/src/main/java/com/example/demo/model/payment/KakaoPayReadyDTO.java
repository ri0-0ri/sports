package com.example.demo.model.payment;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class KakaoPayReadyDTO {
	private String tid;
	private String next_redirect_pc_url;
}
