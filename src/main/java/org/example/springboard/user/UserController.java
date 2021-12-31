package org.example.springboard.user;

import org.example.springboard.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    // login(Get)
    @GetMapping("/login")
    public void login() {}

    // login(Post)
    @PostMapping("/login")
    public String loginProc(UserEntity entity, RedirectAttributes reAttr) {
        int rs = service.login(entity);

        // 예외 처리
        switch (rs) {
            case 0 : { // All false[id, password]
                reAttr.addFlashAttribute("msg", "에러가 발생했습니다.");
                break;
            }
            case 1 : { // All true[id, password]
                return "redirect:/board/list";
            }
            case 2 : { // false[id]
                reAttr.addFlashAttribute("msg", "아이디를 확인해 주세요.");
                break;
            }
            case 3 : { // false[password]
                reAttr.addFlashAttribute("msg", "비밀번호를 확인해 주세요.");
                break;
            }
        }
        return "redirect:/user/login"; // false[0,2,3(index)]
    }

    // logout
    @GetMapping("/logout")
    public String logout(HttpSession hs, HttpServletRequest req) {
        hs.invalidate();

        // 전 페이지로 이돌
        String referer = req.getHeader("Referer");

        if(referer == null) {
            referer = "/user/login"; // url
        }
        return "redirect:" + referer; // 이전 페이지로 이동
    }

    // join(Get)
    @GetMapping("/join")
    public void join() {}

    // join(Post)
    @PostMapping("/join")
    public String joinProc(UserEntity entity, RedirectAttributes reAttr) {

        int rs = service.join(entity);

        switch (rs) {
            case 1 : {
                int loginResult = service.login(entity); // login 처리

                // login successful 이면
                if (loginResult == 1) {
                    return "redirect:/board/list"; // 바로 login 상태에서 url 로 이동!
                }

                return "redirect:/user/login"; // 아니라면 login url 유지!
            }
        }
        // false(join)
        reAttr.addFlashAttribute("msg", "회원가입에 실패 하였습니다."); // error msg
        return "redirect:/user/join"; // url(false)
    }
}
