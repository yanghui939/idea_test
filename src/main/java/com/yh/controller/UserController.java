package com.yh.controller;

import com.yh.pojo.User;
import com.yh.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public String login(User user, HttpSession session, Model model) {
        logger.debug("进入登入方法");
        User login = userService.login(user);
        if (null != login) {
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(600);
            return "redirect:/main.html";
        }
        model.addAttribute("msg", "用户名或密码错误");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        logger.debug("进入登出方法");
        request.getSession().setAttribute("user", null);
        return "login";
    }

    @GetMapping("/users")
    public String toUserList(Model model, Integer index) {
        logger.debug("进入用户列表");
        int pageSize = 10;
        if (null == index || index < 1) {
            index = 1;
        }
        int totalNum = userService.count();
        int totalPage = totalNum % pageSize > 0 ? totalNum / pageSize + 1 : totalNum / pageSize;
        if (index > totalPage) {
            index = totalPage;
        }
        int row = (index - 1) * pageSize;
        Map<String, Object> map = new HashMap<>();
        map.put("row", row);
        map.put("pageSize", pageSize);
        List<User> users = userService.findByPag(map);
        model.addAttribute("users", users);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("index", index);
        return "userList";
    }

    @GetMapping("/toAdd")
    public String toAdd(Integer index,Model model) {
        model.addAttribute("index",index);
        return "add";
    }

    @GetMapping("/toUpdate")
    public String toUpdate(String id, Model model,Integer index) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("index",index);
        return "add";
    }

    @PostMapping("/addUser")
    public String addUser(User user,Integer index) {
        logger.debug("进入添加用户方法");
        String userName = user.getUserName();
        String password = user.getPassword();
        String id = user.getId();
        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
            if (!StringUtils.isEmpty(id)) {
                //修改用户
                boolean b = userService.updateUser(user);
                if (!b) {
                    return "add";
                }
            } else {
                //添加
                user.setId(UUID.randomUUID().toString().replace("-", ""));
                boolean b = userService.insertUser(user);
                if (!b) {
                    return "add";
                }
            }
        } else {
            return "add";
        }
        return "redirect:/users?index="+index;
    }

    @GetMapping("/deleteUser")
    public String deleteUser(String id,Integer index) {
        userService.deleteUser(id);
        return "redirect:/users?index="+index;
    }


}
