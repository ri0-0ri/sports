package com.example.demo.service.fighting;

import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;

import java.util.List;

public interface FightingService {
    List<GWillBoardDTO> getTop3FightingSchedules();
}
