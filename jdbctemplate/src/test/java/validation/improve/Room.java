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
public class Room {

    @NotNull
    public String name;

    @AssertTrue
    public boolean finished;

    //1、字段级别约束（Field）
    //当把约束标注在Field字段上时，Bean Validation将使用字段的访问策略来校验，
    // 「不会调用任何方法」，即使你提供了对应的get/set方法也不会触碰
    public static void main(String[] args) {
        Room bean = new Room();
        bean.finished = false;
        ValidatorUtil.printViolations(ValidatorUtil.obtainValidator().validate(bean));
    }

}
