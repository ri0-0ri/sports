package com.example.demo.mapper.event;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.event.EboardDTO;
import com.example.demo.model.event.EventDTO;
import com.example.demo.model.goods.Criteria;

@Mapper
public interface EventMapper {

	void putevent(EventDTO events);

	List<EventDTO> geteventlist();

	void deleteevent(int eventnum);

	EventDTO geteventByeventnum(int eventnum);

	void puteboard(EboardDTO eboard);

	List<EboardDTO> geteboard();

	void deleteeboard(int eboardnum);

	EboardDTO geteboardByeboardnum(int eboardnum);

	void updateeboard(EboardDTO eboard);

	EboardDTO geteboardbyeventnum(int eventnum);

	void updatewinner(int eventnum, String winner);

	void updatewinnertoboard(int eventnum, String winner);

	List<EboardDTO> geteboardlistbyeventnum(int eventnum);

	void updateevent(int eventnum, String str);

	List<EboardDTO> geteboard(Criteria cri);

	void updatecount(int newnum, int eboardnum);

}
