package com.example.demo.service.gWillBoard;

import java.util.List;

import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;

public interface GWillBoardService {
    void addGWillBoard(GWillBoardDTO gWillBoardDTO); // 일정 추가 메서드
    
    List<GWillBoardDTO> getgWillList();
    void deleteGWillBoard(int gWnum); // 일정 삭제 메서드
}
