package com.example.demo.service.event;

import java.util.List;

import com.example.demo.model.event.EboardDTO;
import com.example.demo.model.event.EventDTO;

public interface EventService {

	void putevent(EventDTO events);

	List<EventDTO> geteventlist();

	void deleteevent(int eventnum);

	EventDTO geteventByeventnum(int eventnum);

	void puteboard(EboardDTO eboard);

	List<EboardDTO> geteboard();

	void deleteeboard(int eboardnum);

	EboardDTO geteboardByeboardnum(int eboardnum);

	void updateeboard(EboardDTO eboard);

	void updatecount(int eboardnum);

}
