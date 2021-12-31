package org.example.springboard.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO extends BoardEntity{
    // 자료를 추가 할때는 VO로 받아온다.
    private String writerNm;
    private String profileImg;
}
