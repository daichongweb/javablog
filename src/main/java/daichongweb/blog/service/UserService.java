package daichongweb.blog.service;

import daichongweb.blog.entity.UserEntity;

import java.security.NoSuchAlgorithmException;

public interface UserService {

    void userRegister(UserEntity userEntity) throws NoSuchAlgorithmException;

    void userLogin(String username, String password) throws NoSuchAlgorithmException;

    UserEntity getUserByUsername(String username);

    void isLogin(String username, String type);
}
