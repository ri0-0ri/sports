package com.example.demo.model.event;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EboardDTO {
	private int eboardnum;
	private LocalDateTime edate;
	private String eboardtitle;
	private String eboardcontent;
	private int eventnum;
	private String winner;
}
