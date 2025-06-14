package propertyeditor;

/**
 * @auther yangxiaolong
 * @create 2024/12/21
 */
public class Customer {

    private Long id;
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /*public Person toPerson() {
        Person person = new Person();
        person.setId(getId());
        person.setName("MyPerson-".concat(getAddress()));
        return person;
    }*/

}