package com.zhongruan.etc.controller;

import com.github.pagehelper.PageInfo;
import com.zhongruan.etc.bean.Role;
import com.zhongruan.etc.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/allRole")
    public String allRole(Model model,
                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "size", defaultValue = "3") Integer size) {
        List<Role> roles = roleService.findAll(page, size);
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        model.addAttribute("pageInfo", pageInfo);
        return "role-list";
    }

    @RequestMapping("/toAddRole")
    public String toAddRole() {
        return "role-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(String roleName,
                      String roleDesc) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setRoleDesc(roleDesc);
        roleService.saveRole(role);
        return "redirect:/role/allRole.do";
    }

    @RequestMapping("/remove")
    public String delete(Integer id, HttpServletRequest request) {
        roleService.removeById(id);
        return "redirect:" + request.getHeader("referer");
    }

}
