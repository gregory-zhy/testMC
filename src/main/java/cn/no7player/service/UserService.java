package cn.no7player.service;

import cn.no7player.mapper.UserMapper;
import cn.no7player.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zl on 2015/8/27.
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUserInfo(Map<String,Object> map){
        List<User> user=userMapper.findUserInfo(map);
        //User user=null;
        return user;
    }

}
