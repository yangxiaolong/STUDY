package jdbc.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAggService {

    private static final Log log = LogFactory.getLog(UserAggService.class);
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    RoleService roleService;
    @Autowired
    Role2Service role2Service;

    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean OUT_REQUIRED_IN_REQUIRES_NEW_EXCE(String userName, Integer age) {
        jdbcTemplate.update("insert into USER_MY(name,pwd,age) values('zhangsan','qwe',32);");
        try {
            roleService.IN_REQUIRES_NEW_EXCE(userName, age);
        } catch (Throwable e) {
            log.error("error", e);
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean OUT_REQUIRED_EXEC_IN_REQUIRES_NEW(String userName, Integer age) {
        jdbcTemplate.update("insert into USER_MY(name,pwd,age) values('zhangsan','qwe',32);");
        roleService.IN_REQUIRES_NEW(userName, age);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean OUT_REQUIRED_IN_NESTED_EXCE(String userName, Integer age) {
        jdbcTemplate.update("insert into USER_MY(name,pwd,age) values('zhangsan','qwe',32);");
        try {
            roleService.IN_NESTED_EXCE(userName, age);
        } catch (Throwable e) {
            log.error("error", e);
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean OUT_REQUIRED_EXEC_IN_NESTED(String userName, Integer age) {
        jdbcTemplate.update("insert into USER_MY(name,pwd,age) values('zhangsan','qwe',32);");
        roleService.IN_NESTED(userName, age);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean OUT_REQUIRED_IN_REQUIRED_EXCE(String userName, Integer age) {
        jdbcTemplate.update("insert into USER_MY(name,pwd,age) values('zhangsan','qwe',32);");
        try {
            roleService.IN_REQUIRED_EXCE(userName, age);
        } catch (Throwable e) {
            log.error("error", e);
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean OUT_REQUIRED_EXEC_IN_REQUIRED(String userName, Integer age) {
        jdbcTemplate.update("insert into USER_MY(name,pwd,age) values('zhangsan','qwe',32);");
        roleService.IN_REQUIRED(userName, age);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean OUT_REQUIRED_IN_NOT_SUPPORTED_ININ_REQUIRED(String userName, Integer age) {
        jdbcTemplate.update("insert into USER_MY(name,pwd,age) values('zhangsan','qwe',32);");
        try {
            roleService.IN_NOT_SUPPORTED(userName, age);
        } catch (Throwable e) {
            log.error("error", e);
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean OUT_REQUIRED_EXEC_IN_NOT_SUPPORTED_ININ_REQUIRED(String userName, Integer age) {
        jdbcTemplate.update("insert into USER_MY(name,pwd,age) values('zhangsan','qwe',32);");
        roleService.IN_NOT_SUPPORTED_NO_EXEC(userName, age);
        throw new RuntimeException();
    }

}
