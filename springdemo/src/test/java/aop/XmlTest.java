package aop;

import distconfig.HelloMessage;
import distconfig.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangxiaolong024
 */
public class XmlTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        HelloMessage bean = context.getBean(HelloMessage.class);
        System.out.println(bean);

        User user = (User) context.getBean("test");
        System.out.println(user);
    }

}
