package aop;

import org.springframework.beans.factory.FactoryBean;

public class SonFactoryBean implements FactoryBean<Son> {

    @Override
    public Son getObject() throws Exception {
        return new Son();
    }

    @Override
    public Class<?> getObjectType() {
        return Son.class;
    }

}
