package com.example.demo.model.event;

import java.time.LocalDateTime;

import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;

import lombok.Data;

@Data
public class EboardDTO {
	private int eboardnum;
	private LocalDateTime edate;
	private int eboardcount;
	private String eboardtitle;
	private String eboardcontent;
	private String eventcon;
	private int eventnum;
	private String winner;
	private GWillBoardDTO game;
}
