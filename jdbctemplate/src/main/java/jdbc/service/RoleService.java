package jdbc.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    private static final Log log = LogFactory.getLog(RoleService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    Role2Service role2Service;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Boolean IN_REQUIRES_NEW_EXCE(String userName, Integer age) {
        jdbcTemplate.update("insert into ROLE(name) values('lisi');");
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Boolean IN_REQUIRES_NEW(String userName, Integer age) {
        int update = jdbcTemplate.update("insert into ROLE(name) values('lisi');");
        return update == 1;
    }

    @Transactional(propagation = Propagation.NESTED)
    public Boolean IN_NESTED_EXCE(String userName, Integer age) {
        jdbcTemplate.update("insert into ROLE(name) values('lisi');");
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NESTED)
    public Boolean IN_NESTED(String userName, Integer age) {
        int update = jdbcTemplate.update("insert into ROLE(name) values('lisi');");
        return update == 1;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean IN_REQUIRED_EXCE(String userName, Integer age) {
        jdbcTemplate.update("insert into ROLE(name) values('lisi');");
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean IN_REQUIRED(String userName, Integer age) {
        int update = jdbcTemplate.update("insert into ROLE(name) values('lisi');");
        return update == 1;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Boolean IN_NOT_SUPPORTED(String userName, Integer age) {
        jdbcTemplate.update("insert into ROLE(name) values('lisi');");
        // 这里catch与否都不影响结果
//        try {
        role2Service.ININ_REQUIRED_EXEC(userName, age);
//        } catch (Throwable e) {
//            log.error("error", e);
//        }
        return true;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Boolean IN_NOT_SUPPORTED_NO_EXEC(String userName, Integer age) {
        jdbcTemplate.update("insert into ROLE(name) values('lisi');");
        role2Service.ININ_REQUIRED(userName, age);
        return true;
    }

}
