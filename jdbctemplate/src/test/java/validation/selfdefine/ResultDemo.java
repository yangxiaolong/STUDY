package validation.selfdefine;

import jakarta.validation.Valid;
import jdbc.validation.Result;
import jdbc.validation.Room;
import lombok.AllArgsConstructor;

/**
 * @author Dragon
 */
@AllArgsConstructor
public class ResultDemo {

    private Result<@Valid Room> roomResult;

    public Result<@Valid Room> getRoomResult() {
        return roomResult;
    }

}