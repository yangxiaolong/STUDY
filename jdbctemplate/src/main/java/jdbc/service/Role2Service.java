package jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Role2Service {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean ININ_REQUIRED_EXEC(String userName, Integer age) {
        jdbcTemplate.update("insert into role(name) values('lisi2');");
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean ININ_REQUIRED(String userName, Integer age) {
        jdbcTemplate.update("insert into role(name) values('lisi2');");
        return true;
    }

}
