package jdbc.event;

public class MyTransactionalEvent {

    private String message;

    public MyTransactionalEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}