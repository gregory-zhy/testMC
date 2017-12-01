package cn.no7player.mapper;

import cn.no7player.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by zl on 2015/8/27.
 */
public interface UserMapper {
    public List<User> findUserInfo(Map<String,Object> map);

    public boolean addUser(User user);
}
