package jdbc.controller;

import jakarta.annotation.Resource;
import jdbc.entity.DateUser;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;

/**
 * @auther yangxiaolong
 * @create 2025/6/7
 */
@RestController
@RequestMapping("/user")
public class DateUserController {

    @Resource
    FormattingConversionService conversionService;

    @GetMapping("/date")
    public String date(@RequestParam("localDateTime")
                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        System.out.println(localDate);
        return "OK";
    }

    @PostMapping("/datetime")
    public String datetime(@RequestBody DateUser dateUser) {
        System.out.println(dateUser);
        Instant convert = conversionService.convert(System.currentTimeMillis(), Instant.class);
        System.out.println(convert);
        return "OK";
    }

}
