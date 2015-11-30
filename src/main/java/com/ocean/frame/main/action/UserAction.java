package com.ocean.frame.main.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ocean.frame.main.entity.Test;
import com.ocean.frame.main.entity.User;
import com.ocean.frame.main.service.UserService;

@Controller
@RequestMapping(produces = { "text/html;charset=UTF-8" })
public class UserAction {

    @Autowired
    private UserService userService;
    
    @RequestMapping("login")
//    @ResponseBody
    public String login(){
        //注意拦截器
        
        //注意统一登录：用户名、邮箱、手机号码，  》》》》单点登录
        
        System.out.println("come in login contrallor@@@@@@@");
        return "manager/login";
    }
    
    //查找后台管理用户
    @RequestMapping("findUserList")
    @ResponseBody
    public String findUserList() {

        List<User> userList = this.userService.findUserList();
        for (User user : userList) {
            
            System.out.println(user.getNickName());
        }
        
        System.out.println(userList);
        return "success";
    }
    
    @RequestMapping("testAdd")
    @ResponseBody
    public String testAdd(Model model, HttpServletRequest request){
        
        //获取参数用enum来解决
        String name = request.getParameter("name");
        int sex = Integer.parseInt(request.getParameter("sex"));
        int flag = 0;
        Test test = new Test();
        test.setName(name);
        test.setSex(sex);
        try {
            flag = this.userService.testAdd(test);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return String.valueOf(flag);
    }
    
    //原生查询示例
    @RequestMapping("findByProtocal")
    @ResponseBody
    public String findByProtocal(){
        
        List<HashMap<Object, Object>> list = this.userService.findListByProtocal();
        
        for (HashMap<Object, Object> hashMap : list) {
            
            System.out.println(hashMap.get("menuRoleId"));
        }
        return list.toString();
    }
    
}
