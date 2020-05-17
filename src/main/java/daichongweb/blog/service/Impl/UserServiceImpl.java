package daichongweb.blog.service.Impl;

import daichongweb.blog.dao.UserDao;
import daichongweb.blog.entity.UserEntity;
import daichongweb.blog.exception.BusinessException;
import daichongweb.blog.service.UserService;
import daichongweb.blog.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    // 用户注册
    @Override
    public void userRegister(UserEntity userEntity) throws NoSuchAlgorithmException {
        // 查询用户是否已经注册
        UserEntity userExits = getUserByUsername(userEntity.getUsername());
        if (userExits != null) {
            throw new BusinessException(400, "用户名已被使用");
        }
        Helper helper = new Helper();
        userEntity.setUsername(userEntity.getUsername());
        userEntity.setPassword(helper.md5(userEntity.getPassword()));
        userEntity.setEmail(userEntity.getEmail());
        userDao.save(userEntity);
    }

    // 用户登陆
    @Override
    public void userLogin(String username, String password) throws NoSuchAlgorithmException {
        // 检测是否登陆
        isLogin(username, "logind");
        // 检测用户是否存在
        UserEntity userEntity = getUserByUsername(username);
        // 对比密码
        Helper helper = new Helper();
        String loginPassword = helper.md5(password);
        if (!userEntity.getPassword().equals(loginPassword)) {
            throw new BusinessException(400, "登陆密码错误");
        }
        Date dNow = new Date();
        SimpleDateFormat currentDateObject = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentDate = currentDateObject.format(dNow);

        String reidsUserKey = "blog:user:" + username;
        Map<String, Object> map = new HashMap<>();
        map.put("id", String.valueOf(userEntity.getId()));
        map.put("username", userEntity.getUsername());
        map.put("password", userEntity.getPassword());
        map.put("email", userEntity.getEmail());
        map.put("loginTime", currentDate);

        stringRedisTemplate.opsForHash().putAll(reidsUserKey, map);
        stringRedisTemplate.expire(reidsUserKey, 86400, TimeUnit.SECONDS);
    }

    // 根据用户名获取用户
    @Override
    public UserEntity getUserByUsername(String username) {
        UserEntity userInfo = userDao.findByUsername(username);
        if (userInfo == null) {
            throw new BusinessException(400, "账户不存在");
        }
        return userInfo;
    }

    @Override
    public void isLogin(String username, String type) {

        String reidsUserKey = "blog:user:" + username;
        List<Object> values = stringRedisTemplate.opsForHash().values(reidsUserKey);
        if (type.equals("logind")) {
            if (!values.isEmpty()) {
                throw new BusinessException(400, "该账户已登陆");
            }
        } else if (type.equals("login")) {
            if (values.isEmpty()) {
                throw new BusinessException(400, "请先登陆");
            }
        }
    }

}
