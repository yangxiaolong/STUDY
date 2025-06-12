package jdbc.validation;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

/**
 * @author Dragon
 */
@AllArgsConstructor
public class Room {

    @NotNull
    public String name;

    @AssertTrue
    public boolean finished;

}
