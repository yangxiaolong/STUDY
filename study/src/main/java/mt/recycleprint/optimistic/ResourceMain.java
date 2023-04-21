package mt.recycleprint.optimistic;

public class ResourceMain {

    public static void main(String[] args) {
        ResourceLock resourceLock = new ResourceLock("A");
        ResourceThread a = new ResourceThread("A", 5, resourceLock);
        ResourceThread b = new ResourceThread("B", 10, resourceLock);
        ResourceThread c = new ResourceThread("C", 15, resourceLock);
        a.setNextThread(b);
        b.setNextThread(c);
        c.setNextThread(a);
        new Thread(a).start();
        new Thread(b).start();
        new Thread(c).start();
    }

}
