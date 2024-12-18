package aop;

import org.junit.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * https://mp.weixin.qq.com/s/i1kpJP4pYEVSfqhz7AJPgg
 * --不懂SpringApplication生命周期事件？那就等于不会Spring Boot嘛
 *
 *
 * <p>
 * https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzI0MTUwOTgyOQ==&action=getalbum&album_id=1727585247963201537&scene=173&subscene=&sessionid=svr_c307f6227ee&enterid=1734527720&from_msgid=2247484810&from_itemidx=1&count=3&nolastread=1#wechat_redirect
 * -- [YourBatman]-Spring配置类
 *
 *
 * <p>
 * https://mp.weixin.qq.com/s/0Qo8bEHp-oUQ-n11NiIo9Q
 * --【方向盘】YourBatman原创技术专栏
 */
public class AppConfigTest1 {

    @Test
    public void test0() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig appConfig = context.getBean(AppConfig.class);

        System.out.println(appConfig.getClass());
        System.out.println(appConfig.getClass().getSuperclass() == AppConfig.class);
        System.out.println(AopUtils.isCglibProxy(appConfig));
    }

    @Test
    public void test1() throws IllegalAccessException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig appConfig = context.getBean(AppConfig.class);

        Field $$beanFactoryField = ReflectionUtils.findField(appConfig.getClass(), "$$beanFactory");
        assert $$beanFactoryField != null;
        BeanFactory beanFactory = (BeanFactory) $$beanFactoryField.get(appConfig);

        System.out.println(beanFactory == context.getAutowireCapableBeanFactory());
        System.out.println(beanFactory == context);
        System.out.println(appConfig instanceof BeanFactoryAware);
        System.out.println(appConfig.getClass().getInterfaces()[0]);
    }


    @Test
    public void test2() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig2.class);
        AppConfig2 appConfig = context.getBean(AppConfig2.class);
        System.out.println();

        System.out.println(appConfig);

        // bean情况
        Son son = context.getBean(Son.class);
        Parent parent = context.getBean(Parent.class);

        System.out.println("容器内的Son实例：" + son.hashCode());
        System.out.println("容器内Person持有的Son实例：" + parent.getSon().hashCode());
        System.out.println(parent.getSon() == son);
    }

    @Test
    public void test3() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig3.class);
        AppConfig3 appConfig = context.getBean(AppConfig3.class);
        System.out.println();

        System.out.println(appConfig);

        // bean情况
        Son son = context.getBean(Son.class);
        Parent parent = context.getBean(Parent.class);

        System.out.println("容器内的Son实例：" + son.hashCode());
        System.out.println("容器内Person持有的Son实例：" + parent.getSon().hashCode());
        System.out.println(parent.getSon() == son);
    }

    @Test
    public void test4() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig4.class);
        AppConfig4 appConfig = context.getBean(AppConfig4.class);
        System.out.println();

        System.out.println(appConfig);

        // bean情况
        Son son = context.getBean(Son.class);
        Parent parent = context.getBean(Parent.class);

        System.out.println("容器内的Son实例：" + son.hashCode());
        System.out.println("容器内Person持有的Son实例：" + parent.getSon().hashCode());
        System.out.println(parent.getSon() == son);
    }

    @Test
    public void test5() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig5.class);
        System.out.println();

        SonFactoryBean sonFactoryBean = context.getBean("&sonFactoryBean", SonFactoryBean.class);
        System.out.println("Spring容器内的SonFactoryBean：" + sonFactoryBean.hashCode());
        System.out.println("Spring容器内的SonFactoryBean：" + System.identityHashCode(sonFactoryBean));
        System.out.println("Spring容器内的SonFactoryBean：" + sonFactoryBean.getClass());

        System.out.println("Spring容器内的Son：" + context.getBean("sonFactoryBean").hashCode());
    }

}
