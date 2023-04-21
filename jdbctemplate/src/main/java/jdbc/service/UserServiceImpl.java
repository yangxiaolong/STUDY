package jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Boolean addUser(String userName, Integer age) {
        jdbcTemplate.update("insert into user(name,pwd,age) values('zhangsan','qwe',32);");
        return true;
    }
}
