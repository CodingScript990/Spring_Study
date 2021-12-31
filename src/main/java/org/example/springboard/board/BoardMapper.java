package org.example.springboard.board;

import org.apache.ibatis.annotations.Mapper;
import org.example.springboard.board.model.BoardEntity;
import org.example.springboard.board.model.BoardVO;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardEntity entity); // insert
    List<BoardVO> selBoardList(); // record 안에 넣을 BoardEntity 를 넣어줘야한다.
    BoardVO selBoard(BoardEntity entity); // detail
    int updBoard(BoardEntity entity); // update
    int delBoard(BoardEntity entity); // delete
}
