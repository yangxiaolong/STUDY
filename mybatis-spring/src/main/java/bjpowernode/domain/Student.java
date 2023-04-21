package bjpowernode.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Student {

    private Integer id;
    private String name;
    private String pwd;
    private Integer age;

}