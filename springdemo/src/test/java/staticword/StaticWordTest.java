package staticword;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * https://mp.weixin.qq.com/s/VTB9f8S6TYeuNVd4Z71zUw
 *
 * @auther yangxiaolong
 * @create 2024/12/25
 */
public class StaticWordTest {

    @Test
    public void test1() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(StaticWordAppConfig.class);
    }

    @Test
    public void test2() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(StaticWordAppConfig2.class);
    }

}
