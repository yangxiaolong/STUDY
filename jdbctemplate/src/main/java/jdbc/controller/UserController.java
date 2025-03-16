package jdbc.controller;

/**
 * @author yangxiaolong
 */

import jdbc.service.UserAggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    /*@Autowired
    List<ListInterface> listInterfaceList;*/

    @Autowired
    UserAggService userAggService;

    @RequestMapping("/OUT_REQUIRED_IN_REQUIRES_NEW_EXCE")
    public String OUT_REQUIRED_IN_REQUIRES_NEW_EXCE() {
        return userAggService.OUT_REQUIRED_IN_REQUIRES_NEW_EXCE(null, null) ? "success" : "fail";
    }

    @RequestMapping("/OUT_REQUIRED_EXEC_IN_REQUIRES_NEW")
    public String OUT_REQUIRED_EXEC_IN_REQUIRES_NEW(String userName, Integer age) {
        return userAggService.OUT_REQUIRED_EXEC_IN_REQUIRES_NEW(null, null) ? "success" : "fail";
    }

    @RequestMapping("/OUT_REQUIRED_IN_NESTED_EXCE")
    public String OUT_REQUIRED_IN_NESTED_EXCE(String userName, Integer age) {
        return userAggService.OUT_REQUIRED_IN_NESTED_EXCE(null, null) ? "success" : "fail";
    }

    @RequestMapping("/OUT_REQUIRED_EXEC_IN_NESTED")
    public String OUT_REQUIRED_EXEC_IN_NESTED(String userName, Integer age) {
        return userAggService.OUT_REQUIRED_EXEC_IN_NESTED(null, null) ? "success" : "fail";
    }

    @RequestMapping("/OUT_REQUIRED_IN_REQUIRED_EXCE")
    public String OUT_REQUIRED_IN_REQUIRED_EXCE(String userName, Integer age) {
        return userAggService.OUT_REQUIRED_IN_REQUIRED_EXCE(null, null) ? "success" : "fail";
    }

    @RequestMapping("/OUT_REQUIRED_EXEC_IN_REQUIRED")
    public String OUT_REQUIRED_EXEC_IN_REQUIRED(String userName, Integer age) {
        return userAggService.OUT_REQUIRED_EXEC_IN_REQUIRED(null, null) ? "success" : "fail";
    }

    @RequestMapping("/OUT_REQUIRED_IN_NOT_SUPPORTED_ININ_REQUIRED")
    public String OUT_REQUIRED_IN_NOT_SUPPORTED_ININ_REQUIRED(String userName, Integer age) {
        return userAggService.OUT_REQUIRED_IN_NOT_SUPPORTED_ININ_REQUIRED(null, null) ? "success" : "fail";
    }

    @RequestMapping("/OUT_REQUIRED_EXEC_IN_NOT_SUPPORTED_ININ_REQUIRED")
    public String OUT_REQUIRED_EXEC_IN_NOT_SUPPORTED_ININ_REQUIRED(String userName, Integer age) {
        return userAggService.OUT_REQUIRED_EXEC_IN_NOT_SUPPORTED_ININ_REQUIRED(null, null) ? "success" : "fail";
    }


}