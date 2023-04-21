package com.atguigu.tx;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangxiaolong
 */
@Repository
public class UserDao {

    final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void insert() {
        String sql = "INSERT INTO yxl.user (name, pwd, age) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, "李斯德", "lside@163.com", 12);
        int i = 10 / 0;
    }

}
