package com.example.demo.service.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.event.EventMapper;
import com.example.demo.model.event.EboardDTO;
import com.example.demo.model.event.EventDTO;
import com.example.demo.model.goods.Criteria;

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

	@Override
	public void deleteeboard(int eboardnum) {
		emapper.deleteeboard(eboardnum);
	}

	@Override
	public EboardDTO geteboardByeboardnum(int eboardnum) {
		return emapper.geteboardByeboardnum(eboardnum);
	}

	@Override
	public void updateeboard(EboardDTO eboard) {
		emapper.updateeboard(eboard);
	}

	@Override
	public EboardDTO geteboardbyeventnum(int eventnum) {
		return emapper.geteboardbyeventnum(eventnum);
	}

	@Override
	public void updatewinner(int eventnum, String winner) {
		emapper.updatewinner(eventnum, winner);
	}

	@Override
	public List<EboardDTO> geteboardlistbyeventnum(int eventnum) {
		return emapper.geteboardlistbyeventnum(eventnum);
	}

	@Override
	public void updateevent(int eventnum, String str) {
		emapper.updateevent(eventnum, str);
	}

	@Override
	public List<EboardDTO> geteboard(Criteria cri) {
		return emapper.geteboard(cri);
	}

	@Override
	public void updatecount(int newnum, int eboardnum) {
		emapper.updatecount(newnum, eboardnum);
	}
}
