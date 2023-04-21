package aop;

import com.atguigu.tx.TxConfig;
import com.atguigu.tx.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yangxiaolong
 */
public class TxTest {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
        UserDao userDao = ac.getBean(UserDao.class);
        userDao.insert();
    }

}
