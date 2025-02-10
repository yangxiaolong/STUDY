package jdbc.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MyEventPublisher {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void publishEvent(String message) {
        MyTransactionalEvent event = new MyTransactionalEvent(message);
        System.out.println("publishEvent== " + Thread.currentThread().getName());
        eventPublisher.publishEvent(event);
    }

}