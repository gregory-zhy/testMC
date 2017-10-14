package cn.no7player.controller;
//http://7player.cn/2015/08/30/%E3%80%90%E5%8E%9F%E5%88%9B%E3%80%91%E5%9F%BA%E4%BA%8Espringboot-mybatis%E5%AE%9E%E7%8E%B0springmvc-web%E9%A1%B9%E7%9B%AE/
import cn.no7player.model.User;
import cn.no7player.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zl on 2015/8/27.
 */
@Controller
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public List<User> getUserInfo() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id","5");
        map.put("name","tony5");
        map.put("age","15");
        map.put("begTime","2015-05-23");
        map.put("endTime","2018-12-31");
        List<User> userList = userService.getUserInfo(map);
//        if(user!=null){
//            System.out.println("user.getName():"+user.getName());
//            logger.info("user.getAge():"+user.getAge());
//        }
        return userList;
    }
}
