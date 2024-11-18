package com.example.demo.model.event;

import lombok.Data;

@Data
public class EventDTO {
	private int eventnum;
	private int gwnum;
	private String eventtype;
	private String eventitem;
	private String winner;
	private String str;
}
