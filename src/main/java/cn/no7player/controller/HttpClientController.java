package cn.no7player.controller;

import cn.no7player.service.HttpAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/http")
public class HttpClientController {

    @Autowired
    HttpAPIService httpAPIService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "test";
    }

    @RequestMapping(value = "/doGet",method = RequestMethod.GET)
    public String doGet() throws Exception{
        return httpAPIService.doGet("http://www.baidu.com/",null,null);
    }
}
