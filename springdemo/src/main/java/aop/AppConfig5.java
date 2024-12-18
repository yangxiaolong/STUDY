package aop;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig5 {

    @Bean
    public FactoryBean<Son> sonFactoryBean() {
        SonFactoryBean sonFactoryBean = new SonFactoryBean();
        System.out.println("我使用@Bean定义sonFactoryBean：" + sonFactoryBean.hashCode());
        System.out.println("我使用@Bean定义sonFactoryBean identityHashCode：" + System.identityHashCode(sonFactoryBean));
        return sonFactoryBean;
    }

    @Bean
    public Parent parent(Son son) throws Exception {
        // 根据前面所学，sonFactoryBean肯定是去容器拿
        FactoryBean<Son> sonFactoryBean = sonFactoryBean();
        System.out.println("parent流程使用的sonFactoryBean：" + sonFactoryBean.hashCode());
        System.out.println("parent流程使用的sonFactoryBean identityHashCode：" + System.identityHashCode(sonFactoryBean));
        System.out.println("parent流程使用的sonFactoryBean：" + sonFactoryBean.getClass());
        // 虽然sonFactoryBean是从容器拿的，但是getObject()你可不能保证每次都返回单例哦~
        Son sonFromFactory1 = sonFactoryBean.getObject();
        Son sonFromFactory2 = sonFactoryBean.getObject();
        assert sonFromFactory1 != null;
        System.out.println("parent流程使用的sonFromFactory1：" + sonFromFactory1.hashCode());
        assert sonFromFactory2 != null;
        System.out.println("parent流程使用的sonFromFactory2：" + sonFromFactory2.hashCode());
        System.out.println("parent流程使用的son和容器内的son是否相等：" + (son == sonFromFactory1));

        return new Parent(sonFromFactory1);
    }

}
