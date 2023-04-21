package bjpowernode.service;


import bjpowernode.dao.StudentMapper;
import bjpowernode.domain.Student;

import java.util.List;

/**
 * @author yangxiaolong
 */
public class StudentServiceImpl implements StudentService {

    private StudentMapper studentMapper;

    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public int insertStudent(Student s) {
        return studentMapper.insertStudent(s);
    }

    public List<Student> list() {
        return studentMapper.list();
    }
}
