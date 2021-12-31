package org.example.springboard.board;

import org.example.springboard.UserUtils;
import org.example.springboard.board.model.BoardEntity;
import org.example.springboard.board.model.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// servlet 개념
@Controller
@RequestMapping("/board") // 1차 url
public class BoardController {

    // BoardService
    @Autowired
    private BoardService service;

    // mapping
    //@RequestMapping("/list")
    // list
    @GetMapping("/list") // 2차 url
    public void list(Model model) { // Model 을 사용할 것
        List<BoardVO> list = service.selBoardList();
        model.addAttribute("list", list);
        //System.out.println(" BoardController - list method called ");
        // return "board/list"; //
    }

    // detail
    @GetMapping("/detail")
    public void detail(Model model, BoardEntity entity) {
        service.upBoardHitsUp(entity); // hit
        model.addAttribute("data", service.selBoard(entity)); // detail
    }

    // write
    @GetMapping("/write")
    public void write() {} // view단에 보여주는 곳

    @PostMapping("/write")
    public String writeProc(BoardEntity entity, RedirectAttributes reAttr) { // db에 data 저장
        System.out.println(entity);

        int rs = service.insBoard(entity);

        // 예외처리
        if (rs == 0) { // 만약 글 등록에 실패 했다면?
            // addAttribute 는 쿼리스트링으로 생성
            reAttr.addFlashAttribute("msg", "글 등록에 실패하였습니다."); // message 를 보여주고 addFlashAttribute(request 에 담아서 보여줌)
            reAttr.addFlashAttribute("data", entity); // data 가 담긴 값을 보내준다.
            return "redirect:/board/write"; // 경로는 write 페이지!
        }
        return "redirect:/board/list"; // 글 등록 성공시 경로
    }

    // update
    @GetMapping("/mod")
    public void mod(Model model, BoardEntity entity) {
        model.addAttribute("data", service.selBoard(entity));
    }

    @PostMapping("/mod")
    public String modProc(BoardEntity entity, RedirectAttributes reAttr) {

        int rs = service.updBoard(entity);

        // 예외처리
        if (rs == 0) {
            reAttr.addFlashAttribute("msg", "글 수정에 실패하였습니다.");
            reAttr.addFlashAttribute("data", entity);
            return "redirect:/board/mod?iboard=" + entity.getIboard();
        }

        return "redirect:/board/detail?iboard=" + entity.getIboard();
    }

    // delete
    @GetMapping("/del")
    public String delProc(BoardEntity entity, RedirectAttributes reAttr) {
        int rs = service.delBoard(entity);

        // 예외처리
        if (rs == 0) {
            reAttr.addFlashAttribute("msg", "글 삭제에 실패하였습니다.");
            reAttr.addAttribute("iboard", entity.getIboard()); // query string iboard=pk 값
            return "redirect:/board/detail";
        }

        return "redirect:/board/list";
    }
}
