import bjpowernode.domain.Student;
import bjpowernode.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author yangxiaong
 */
public class MyTest {

    @Test
    public void test03() {
        //读取spring主配置文件
        String in = "app.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(in);
        StudentService service = ac.getBean(StudentService.class);
        Student student = new Student();
        student.setName("裴元虎");
        student.setPwd("peiyuanhu@163.com");
        student.setAge(26);
        int i = service.insertStudent(student);
        System.out.println("添加" + i + "行数据");
    }

    @Test
    public void test04() {
        //读取spring主配置文件
        String in = "app.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(in);
        StudentService service = ac.getBean(StudentService.class);
        List<Student> studentList = service.list();
        for (Student stu : studentList) {
            System.out.println(">>>>" + stu);
        }
    }

}
