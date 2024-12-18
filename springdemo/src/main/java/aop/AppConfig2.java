package aop;

import org.springframework.context.annotation.Bean;

public class AppConfig2 {

    @Bean
    public Son son() {
        Son son = new Son();
        System.out.println("son created..." + son.hashCode());
        return son;
    }

    @Bean
    public Parent parent() {
        Son son = son();
        System.out.println("parent created...持有的Son是：" + son.hashCode());
        return new Parent(son);
    }

}
