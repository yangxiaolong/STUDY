package jdbc.controller;

import jakarta.validation.Valid;
import jdbc.validation.Result;
import jdbc.validation.Room;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dragon
 */
@RestController
@RequestMapping("/validation")
@Validated
public class ValidationController {

    @GetMapping("/invalid-return")
    public Result<@Valid Room> invalid() {
        Room room = new Room("YourBatman", false);
        Result<Room> result = new Result<>();
        result.setData(room);
        return result;
    }

    @GetMapping("/valid-return")
    public Result<@Valid Room> valid() {
        Room room = new Room("YourBatman", true);
        Result<Room> result = new Result<>();
        result.setData(room);
        return result;
    }

}
