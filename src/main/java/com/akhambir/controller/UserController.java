package com.akhambir.controller;

import com.akhambir.model.User;
import com.akhambir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView vm = new ModelAndView();
        vm.setViewName("login");
        vm.addObject("user", new User());
        return vm;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("user") User user) {
        ModelAndView vm = new ModelAndView();
        vm.addObject("user", userService.getByEmail(user));
        vm.setViewName("welcome");
        return vm;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView signup(@ModelAttribute User user) {
        ModelAndView vm = new ModelAndView();
        user.setFirstName("name");
        user.setLastName("lastName");
        vm.addObject("user", userService.addUser(user));
        vm.setViewName("welcome");
        return vm;
    }

    @RequestMapping(value = "/activation/{token}", method = RequestMethod.GET)
    public ModelAndView activation(@PathVariable String token) {
        ModelAndView vm = new ModelAndView();
        return vm;
    }
}
