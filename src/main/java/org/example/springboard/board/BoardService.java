package org.example.springboard.board;

import org.example.springboard.UserUtils;
import org.example.springboard.board.model.BoardEntity;
import org.example.springboard.board.model.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardMapper mapper;

    @Autowired
    private UserUtils userUtils;

    // insert
    public int insBoard(BoardEntity entity) {

        try {
            entity.setWriter(userUtils.getLoginUserPk());
            return mapper.insBoard(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // list
    public List<BoardVO> selBoardList() {
        return mapper.selBoardList();
    }

    // detail
    public BoardVO selBoard(BoardEntity entity) {
        return mapper.selBoard(entity);
    }

    // update
    public int updBoard(BoardEntity entity) {
        entity.setWriter(userUtils.getLoginUserPk()); // userUtils(pk)
        try {
            return mapper.updBoard(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // hit
    public void upBoardHitsUp(BoardEntity entity) {
        entity.setHit(1); // 1씩 올려준다.
        mapper.updBoard(entity);
    }

    // delete
    public int delBoard(BoardEntity entity) {
        entity.setWriter(userUtils.getLoginUserPk()); // userUtils(pk)
        try {
            return mapper.delBoard(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
