package validation.basic;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;

/**
 * @author Dragon
 */
//@ScriptAssert(script = "_this.name==_this.fullName", lang = "javascript")
@Data
public class User {

    @NotNull
    private String name;

    @Length(min = 20)
    @NotNull
    private String fullName;

}

