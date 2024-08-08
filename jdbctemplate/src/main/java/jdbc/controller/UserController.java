package jdbc.controller;

/**
 * @author yangxiaolong
 */

import jdbc.list.ListInterface;
import jdbc.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    List<ListInterface> listInterfaceList;

    /*@Autowired
    private UserServiceImpl userService;

    @RequestMapping("/add")
    public String addUser(String userName, Integer age) {
        return userService.addUser(userName, age) ? "success" : "fail";
    }*/
}