package jdbc.controller;

import jdbc.entity.DateUser;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * @auther yangxiaolong
 * @create 2025/6/7
 */
@RestController
@RequestMapping("/user")
public class DateUserController {

    @GetMapping("/date")
    public String date(@RequestParam("localDateTime")
                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        System.out.println(localDate);
        return "OK";
    }

    @PostMapping("/datetime")
    public String datetime(@RequestBody DateUser dateUser) {
        System.out.println(dateUser);
        return "OK";
    }

}
