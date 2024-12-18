package aop;

public class Parent {
    
    private final Son son;

    public Parent(Son son) {
        this.son = son;
    }

    public Son getSon() {
        return son;
    }

}
