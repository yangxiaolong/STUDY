package propertyeditor;

/**
 * @author Dragon
 */
public class Person {
    private Long id;
    private String name;
    private Cat cat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 方法名称可以是：valueOf、of、from
     */
    public static Person valueOf(Customer customer) {
        Person person = new Person();
        person.setId(customer.getId());
        person.setName("YourBatman-".concat(customer.getAddress()));
        return person;
    }

}
