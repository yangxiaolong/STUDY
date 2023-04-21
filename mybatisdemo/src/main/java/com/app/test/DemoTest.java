package com.app.test;

import com.app.test.mapper.User;
import com.app.test.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yangxiaolong
 */
public class DemoTest {

    public static void main(String[] args) throws IOException {
        // 读取到配置文件信息
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 根据配置文件构建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获取 SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession进行接下来的数据处理操作
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectUser(1L);
        System.out.println(user);
        sqlSession.close();
    }

}
