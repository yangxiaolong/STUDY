package aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig4 {

    @Bean
    public Son son() {
        Son son = new Son();
        System.out.println("son created..." + son.hashCode());
        return son;
    }

    @Bean
    public Parent parent() {
        notBeanMethod();
        Son son = son();
        System.out.println("parent created...持有的Son是：" + son.hashCode());
        return new Parent(son);
    }

    public void notBeanMethod(){
        System.out.println("notBeanMethod invoked by 【" + this + "】");
    }

}
