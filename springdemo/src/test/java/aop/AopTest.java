package aop;

import com.willow.aop.Calculate;
import com.willow.aop.DoCalculate;
import com.willow.config.AopConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by yangxiaolong on 2019\10\10 0010.
 */
public class AopTest {

    @org.junit.Test
    public void calc() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AopConfig.class);
        Calculate beanA = ac.getBean(Calculate.class);
        DoCalculate beanB = ac.getBean(DoCalculate.class);
        int divA = beanA.div(3, 1);
        int divB = beanB.doDiv(4, 2);
        System.out.println(divA);
        System.out.println(divB);
    }

}
