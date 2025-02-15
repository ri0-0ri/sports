package com.example.demo.service.gWillBoard;

import com.example.demo.mapper.gWillBoard.GWillBoardMapper;
import com.example.demo.modal.gWillBoardDTO.GWillBoardDTO;

import java.util.List;

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
    
    @Override
    public List<GWillBoardDTO> getgWillList() {
       List<GWillBoardDTO> boardList = gWillBoardMapper.getgWillList();
       return boardList;
    } 
    @Override
    public void deleteGWillBoard(int gWnum) {
        gWillBoardMapper.deleteGWillBoard(gWnum); // 일정 삭제
    }

	@Override
	public GWillBoardDTO getgame(int gwnum) {
		return gWillBoardMapper.getgame(gwnum);
	}

	@Override
	public void updateendgame(GWillBoardDTO game) {
		gWillBoardMapper.updateendgame(game);
	}

}
