package validation.improve;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import validation.basic.ValidatorUtil;

/**
 * @author Dragon
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Room2 {

    public String name;
    public boolean finished;

    @NotNull
    public String getName() {
        return name;
    }

    @AssertTrue
    public boolean isFinished() {
        return finished;
    }

    //2、属性级别约束（Property）
    //当把约束标注在Property属性上时，将采用属性访问策略来获取要验证的值。说白了：会调用你的Method来获取待校验的值
    public static void main(String[] args) {
        Room2 bean = new Room2();
        bean.finished = false;
        ValidatorUtil.printViolations(ValidatorUtil.obtainValidator().validate(bean));
    }

}
