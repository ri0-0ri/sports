package com.example.demo.service.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.event.EventMapper;
import com.example.demo.model.event.EboardDTO;
import com.example.demo.model.event.EventDTO;

@Service
public class EventServiceImpl implements EventService{

	@Autowired
	EventMapper emapper;
	
	@Override
	public void putevent(EventDTO events) {
		emapper.putevent(events);
	}

	@Override
	public List<EventDTO> geteventlist() {
		return emapper.geteventlist();
	}

	@Override
	public void deleteevent(int eventnum) {
		emapper.deleteevent(eventnum);
	}

	@Override
	public EventDTO geteventByeventnum(int eventnum) {
		return emapper.geteventByeventnum(eventnum);
	}

	@Override
	public void puteboard(EboardDTO eboard) {
		emapper.puteboard(eboard);
	}

	@Override
	public List<EboardDTO> geteboard() {
		return emapper.geteboard();
	}

}
