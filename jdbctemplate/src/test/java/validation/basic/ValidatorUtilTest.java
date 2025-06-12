package validation.basic;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.metadata.*;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * @author Dragon
 */
public class ValidatorUtilTest {

    //validate：校验Java Bean
    @Test
    public void test5() {
        User user = new User();
        user.setName("YourBatman");

        Set<ConstraintViolation<User>> result = ValidatorUtil.obtainValidator().validate(user);
        ValidatorUtil.printViolations(result);
    }

    //validateProperty：校验指定属性
    @Test
    public void test6() {
        User user = new User();
        user.setFullName("YourBatman");

        Set<ConstraintViolation<User>> result = ValidatorUtil.obtainValidator()
                .validateProperty(user, "fullName");
        ValidatorUtil.printViolations(result);
    }


    //validateValue：校验value值
    @Test
    public void test7() {
        Set<ConstraintViolation<User>> result = ValidatorUtil.obtainValidator()
                .validateValue(User.class, "fullName", "A哥");
        ValidatorUtil.printViolations(result);
    }

    //获取Class类型描述信息
    @Test
    public void test8() {
        BeanDescriptor beanDescriptor = ValidatorUtil.obtainValidator().getConstraintsForClass(User.class);
        System.out.println("此类是否需要校验：" + beanDescriptor.isBeanConstrained());

        // 获取属性、方法、构造器的约束
        Set<PropertyDescriptor> constrainedProperties = beanDescriptor.getConstrainedProperties();
        Set<MethodDescriptor> constrainedMethods = beanDescriptor.getConstrainedMethods(MethodType.GETTER);
        Set<ConstructorDescriptor> constrainedConstructors = beanDescriptor.getConstrainedConstructors();
        System.out.println("需要校验的属性：" + constrainedProperties);
        System.out.println("需要校验的方法：" + constrainedMethods);
        System.out.println("需要校验的构造器：" + constrainedConstructors);

        PropertyDescriptor fullNameDesc = beanDescriptor.getConstraintsForProperty("fullName");
        System.out.println(fullNameDesc);
        System.out.println("fullName属性的约束注解个数：" + fullNameDesc.getConstraintDescriptors().size());
    }

    //获得Executable校验器

}
