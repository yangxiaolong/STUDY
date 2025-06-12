package validation.basic;

import jakarta.validation.ParameterNameProvider;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorContext;
import org.hibernate.validator.internal.engine.DefaultClockProvider;
import org.hibernate.validator.internal.engine.DefaultParameterNameProvider;
import org.hibernate.validator.internal.engine.ValidatorContextImpl;
import org.hibernate.validator.internal.engine.ValidatorFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author Dragon
 */
public class ValidatorContextTest {

    //方式一：自己new
    @Test
    public void test2() {
        ValidatorFactoryImpl validatorFactory = (ValidatorFactoryImpl)
                ValidatorUtil.obtainValidatorFactory();
        // 使用默认的Context上下文，并且初始化一个Validator实例
        // 必须传入一个校验器工厂实例哦
        ValidatorContext validatorContext = new ValidatorContextImpl(validatorFactory)
                .parameterNameProvider(new DefaultParameterNameProvider())
                .clockProvider(DefaultClockProvider.INSTANCE);

        // 通过该上下文，生成校验器实例（注意：调用多次，生成实例是多个哟）
        System.out.println(validatorContext.getValidator());
    }

    //方式二：工厂生成
    @Test
    public void test3() {
        Validator validator = ValidatorUtil.obtainValidatorFactory().usingContext()
                .parameterNameProvider(new DefaultParameterNameProvider())
                .clockProvider(DefaultClockProvider.INSTANCE)
                .getValidator();
        System.out.println(validator);
    }

    @Test
    public void test9() {
        ParameterNameProvider parameterNameProvider = new DefaultParameterNameProvider();

        // 拿到Person的无参构造和有参构造（@NoArgsConstructor和@AllArgsConstructor）
        Arrays.stream(Person.class.getConstructors())
                .forEach(c -> System.out.println(parameterNameProvider.getParameterNames(c)));
    }

}
