package importselector;

public interface StringBean {
    String getString();

    enum StringType {
        HELLO_WORLD,
        WORLD_HELLO
    }

}

class HelloWorldBean implements StringBean {
    @Override
    public String getString() {
        return "HELLO_WORLD";
    }
}

class WorldHelloBean implements StringBean {
    @Override
    public String getString() {
        return "WORLD_HELLO";
    }
}