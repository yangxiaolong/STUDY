package validation.selfdefine;

import jakarta.validation.Validator;
import jdbc.validation.Result;
import jdbc.validation.ResultValueExtractor;
import jdbc.validation.Room;
import validation.basic.ValidatorUtil;

import java.lang.reflect.Method;

/**
 * @author Dragon
 */
public class Test {

    public static void main(String[] args) throws NoSuchMethodException {
        Result<Room> result = new Result<>();
        result.setData(new Room("YourBatman", false));

        ResultDemo resultDemo = new ResultDemo(result);

        // 注册自定义的值提取器
        Validator validator = ValidatorUtil.obtainValidatorFactory()
                .usingContext()
                .addValueExtractor(new ResultValueExtractor())
                .getValidator();
        ValidatorUtil.printViolations(validator.validate(resultDemo));


        Method currMethod = ResultDemo.class.getMethod("getRoomResult");
        Validator validator1 = ValidatorUtil.obtainValidatorFactory()
                .usingContext()
                .addValueExtractor(new ResultValueExtractor())
                .getValidator();
        ValidatorUtil.printViolations(validator1.forExecutables()
                .validateReturnValue(resultDemo, currMethod, result));
    }

}
