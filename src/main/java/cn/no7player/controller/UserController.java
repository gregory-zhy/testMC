package cn.no7player.controller;
//http://7player.cn/2015/08/30/%E3%80%90%E5%8E%9F%E5%88%9B%E3%80%91%E5%9F%BA%E4%BA%8Espringboot-mybatis%E5%AE%9E%E7%8E%B0springmvc-web%E9%A1%B9%E7%9B%AE/
import cn.no7player.model.User;
import cn.no7player.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

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
//        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id","5");
//        map.put("name","tony5");
//        map.put("age","15");
//        map.put("begTime","2015-05-23");
//        map.put("endTime","2018-12-31");
        List<User> userList = userService.getUserInfo(map);
//        if(user!=null){
//            System.out.println("user.getName():"+user.getName());
//            logger.info("user.getAge():"+user.getAge());
//        }
        return userList;
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public boolean addUser() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        User user = new User();
        user.setId("qwer");
        user.setName("rose");
        user.setPassword("1234");
//        user.setMoney(new BigDecimal("0.25"));
        user.setAge(20);
        user.setCreateTime(new Date());

        return userService.addUser(user);
    }

    @RequestMapping(value = "/paser2User01", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
//    public User paser2User01(@RequestBody() User user) {
    public User paser2User01(@RequestBody() String jsonStr) {
//        JSONObject.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.mmm";
//        JSONObject.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
        JSONObject paramJSON = JSON.parseObject(jsonStr);
        Date date = paramJSON.getDate("createTime");
        String strDate = paramJSON.getString("createTime");
        System.out.println("--------------------getDate: " + date);
        System.out.println("--------------------getString: " + strDate);
        User user = new User();
        user.setCreateTime(date);
        return user;
    }

    @RequestMapping(value = "/paser2User02", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
//    public String paser2User02(@RequestParam("user") User user) {
    public String paser2User02(@RequestBody() String jsonStr) {
        System.out.println("--------------------jsonStr: " + jsonStr);
//        JSONObject.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
        User user = new User();
        user = JSON.parseObject(jsonStr,User.class);
        return "success  "+user.toString();
    }




//    ISO-8601日期格式
//    yyyy-MM-dd
//    yyyy-MM-dd HH:mm:ss
//    yyyy-MM-dd HH:mm:ss.SSS
//            毫秒数字
//    毫秒数字字符串
//            .NET JSON日期格式
//    new Date(1982932381111)
}
