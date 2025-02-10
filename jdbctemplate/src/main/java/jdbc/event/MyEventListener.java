
package jdbc.event;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class MyEventListener {

    //  @Transactional(propagation = Propagation.REQUIRED)
//   @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void handleMyTransactionalEvent(MyTransactionalEvent event) {
        // 处理 MyTransactionalEvent
        System.out.println("Received MyTransactionalEvent: " + event);
    }

}