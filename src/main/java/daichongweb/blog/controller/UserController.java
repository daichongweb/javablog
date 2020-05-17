package daichongweb.blog.controller;

import daichongweb.blog.entity.UserEntity;
import daichongweb.blog.service.UserService;
import daichongweb.blog.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

/**/
@Transactional
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/userRegister")
    public JsonResult index(@RequestBody UserEntity userEntity) throws NoSuchAlgorithmException {
        userService.userRegister(userEntity);
        return new JsonResult<>("200", "注册成功");
    }

    @PostMapping("/userLogin")
    public JsonResult userLogin(String username, String password) throws NoSuchAlgorithmException {
        userService.userLogin(username, password);
        return new JsonResult<>("200", "登陆成功");
    }
}
