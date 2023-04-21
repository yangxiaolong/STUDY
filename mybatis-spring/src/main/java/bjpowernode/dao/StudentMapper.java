package bjpowernode.dao;

import bjpowernode.domain.Student;

import java.util.List;

/**
 * @author yangxiaolong
 */
public interface StudentMapper {

    int insertStudent(Student s);

    List<Student> list();

}
