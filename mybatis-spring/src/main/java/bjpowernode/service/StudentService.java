package bjpowernode.service;


import bjpowernode.domain.Student;

import java.util.List;

public interface StudentService {

    int insertStudent(Student s);

    List<Student> list();

}
