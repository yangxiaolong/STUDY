package validation.basic;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @NotNull
    public String name;

    @NotNull
    @Min(0)
    public Integer age;

}

