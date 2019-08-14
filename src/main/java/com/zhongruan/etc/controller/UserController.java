package com.zhongruan.etc.controller;

import com.github.pagehelper.PageInfo;
import com.zhongruan.etc.bean.Role;
import com.zhongruan.etc.bean.UserInfo;
import com.zhongruan.etc.bean.UserRole;
import com.zhongruan.etc.service.IRoleService;
import com.zhongruan.etc.service.IUserInfoService;
import com.zhongruan.etc.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserRoleService userRoleService;

    /**
     * 添加用户页面
     *
     * @return
     */
    @RequestMapping("/toAddUser")
    public String toAddUser(Model model,
                            HttpServletRequest request) throws Exception {
        //设置回调地址
        model.addAttribute("returnPage", URLEncoder.encode(request.getHeader("referer"), "utf-8"));
        return "user-add";
    }

    /**
     * 更新用户页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toUpdateUser")
    public String toUpdateUser(Integer id,
                               Model model,
                               HttpServletRequest request) throws Exception {
        UserInfo userInfo = userInfoService.findById(id);
        model.addAttribute("user", userInfo);
        model.addAttribute("returnPage", URLEncoder.encode(request.getHeader("referer"), "utf-8"));
        return "user-update";
    }

    /**
     * 用户列表页面
     *
     * @param page
     * @param size
     * @param model
     * @return
     */
    @RequestMapping("/allUser")
    public String allUser(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "size", defaultValue = "5") Integer size,
                          Model model) {
        List<UserInfo> userInfos = userInfoService.findAllUser(keyword, page, size);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfos);

        if (keyword == null) {
            keyword = "";
        }
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("keyword", keyword);
        return "user-list";
    }

    /**
     * 用户角色添加页面
     *
     * @param userId
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/toUserRoleAdd")
    public String toUserRoleAdd(Integer userId,
                                Model model,
                                HttpServletRequest request) throws Exception {
        UserInfo userInfo = userInfoService.findById(userId);
        List<Role> roles = roleService.findAll(1, 0);

        List<UserRole> userRoles = userRoleService.findByUid(userId);
        Set<Integer> userRoleSet = new HashSet<>();

        for (UserRole item : userRoles) {
            userRoleSet.add(item.getRid());
        }

        model.addAttribute("user", userInfo);
        model.addAttribute("roles", roles);
        model.addAttribute("userRoles", userRoleSet);
        model.addAttribute("returnPage", URLEncoder.encode(request.getHeader("referer"), "utf-8"));
        return "user-role-add";
    }

    /**
     * POST保存用户
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(String username,
                          String password,
                          @RequestParam(value = "returnPage", required = false) String returnPage) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfoService.save(userInfo);
        if (returnPage == null) {
            return "redirect:/user/allUser.do";
        } else {
            return "redirect:" + returnPage;
        }
    }

    /**
     * GET删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    public String delete(Integer id,
                         HttpServletRequest request) {
        userInfoService.removeById(id);
        return "redirect:" + request.getHeader("referer");
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
    @ResponseBody
    public String deleteAll(@RequestParam("ids") Integer[] ids) {
        try {
            int[] int_ids = new int[ids.length];
            for (int i = 0; i < ids.length; i++) {
                int_ids[i] = ids[i];
            }
            userInfoService.batchDelete(int_ids);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    /**
     * POST更新用户
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(UserInfo userInfo,
                             @RequestParam(value = "returnPage", required = false) String returnPage) {
        userInfoService.modify(userInfo);
        if (returnPage == null) {
            return "redirect:/user/allUser.do";
        } else {
            return "redirect:" + returnPage;
        }
    }

    /**
     * POST添加用户角色
     *
     * @param userId
     * @param roleIds
     * @param returnPage
     * @param request
     * @return
     */
    @RequestMapping(value = "/addUserRole", method = RequestMethod.POST)
    public String addUserRole(Integer userId,
                              @RequestParam(value = "roleIds", required = false) Integer[] roleIds,
                              //返回列表时的页面位置
                              @RequestParam(value = "returnPage", required = false) String returnPage,
                              HttpServletRequest request) {
        if (roleIds == null) {
            roleIds = new Integer[0];
        }
        int[] roles = new int[roleIds.length];
        for (int i = 0; i < roleIds.length; i++) {
            roles[i] = roleIds[i];
        }
        userRoleService.saveUserRoles(userId, roles);
        if (returnPage == null) {
            return "redirect:/user/allUser.do";
        } else {
            return "redirect:" + returnPage;
        }
    }


}
