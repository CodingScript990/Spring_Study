package org.example.springboard.user;

import org.example.springboard.Const;
import org.example.springboard.user.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService {

    // User
    @Autowired
    private UserMapper mapper;

    // Session
    @Autowired
    private HttpSession hs;

    public int login(UserEntity entity) {

        UserEntity loginUser = null; // initial loginUser value == null

        // login query error & login false
        try {
            loginUser = mapper.selUser(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // false(id, password)
        }

        System.out.println(loginUser);

        if (loginUser == null) {
            return 2; // not id
        }
        // 암호화 비교
        if (BCrypt.checkpw(entity.getUpw(), loginUser.getUpw())){ // join upw == login upw
            loginUser.setUpw(null);
            loginUser.setRdt(null);
            hs.setAttribute(Const.LOGIN_USER, loginUser);
            return 1; // true(id, password)
        }
        return 3; // false(password)
    }

    // insert
    public int join(UserEntity entity) {
        String plainPw = entity.getUpw();
        String hashPw = BCrypt.hashpw(plainPw, BCrypt.gensalt());
        entity.setUpw(hashPw); // 암호화
        int rs = mapper.insUser(entity);
        entity.setUpw(plainPw);
        return rs;
    }
}
