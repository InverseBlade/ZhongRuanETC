package com.zhongruan.etc.controller;

import com.zhongruan.etc.bean.UserInfo;
import com.zhongruan.etc.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = {"", "/"})
public class IndexController {

    @Autowired
    IUserInfoService userInfoService;

    @RequestMapping(value = {"main", "/main"})
    public String main(HttpSession session, Model model) {
        //获取当前登录用户
//        UserInfo userInfo = (UserInfo) session.getAttribute("user");
//        if (userInfo == null) {
//            return "redirect:/index.jsp";
//        }
//        return "main";
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetails", userDetails);
        return "main";
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public String verify(String username,
                         String password,
                         HttpSession session) {
        UserInfo userInfo = userInfoService.findByUserName(username);
        System.out.println(userInfo);
        if (userInfo != null && userInfo.getPassword().equals(password)) {
            session.setAttribute("user", userInfo);
            return "redirect:/main.do";
        }
        return "redirect:/failer.jsp";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

}
