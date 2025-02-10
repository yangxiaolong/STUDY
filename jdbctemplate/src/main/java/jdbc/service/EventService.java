package jdbc.service;

import jdbc.event.MyEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dragon
 */
@Service
public class EventService {

    @Autowired
    MyEventPublisher myEventPublisher;

    @Transactional(propagation = Propagation.REQUIRED)
    public String publishEvent() {
        // 发布事件
        myEventPublisher.publishEvent("12121");

        return "ok";
    }

}