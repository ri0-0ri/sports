package com.example.demo.service.gWillBoard;

import com.example.demo.mapper.gWillBoard.GWillBoardMapper;
import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GWillBoardServiceImpl implements GWillBoardService {
    @Autowired
    private GWillBoardMapper gWillBoardMapper;

    @Override
    public void addGWillBoard(GWillBoardDTO gWillBoardDTO) {
        gWillBoardMapper.insertGWillBoard(gWillBoardDTO); // 데이터 삽입
    }
}
