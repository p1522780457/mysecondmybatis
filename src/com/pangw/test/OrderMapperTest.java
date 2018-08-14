package com.pangw.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.pangw.dao.Order;
import com.pangw.dao.User;
import com.pangw.mapper.OrderMapper;
import com.pangw.mapper.UserMapper;

public class OrderMapperTest {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void init() throws IOException {
		
		// 创建SqlSessionFactoryBuilder
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
		
		// 加载SqlMapConfig.xml配置文件
		InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		
		// 创建SqlsessionFactory
		this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

	}

	@Test
	public void testQueryOrderAll() {
		
		// 获取sqlSession，和spring整合后由spring管理
		SqlSession sqlSession = this.sqlSessionFactory.openSession();

		// 从sqlSession中获取Mapper接口的代理对象
		OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

		List<Order> list = orderMapper.queryOrderAll();

		for (Order o : list) {
			System.out.println(o.toString());
		}

		// 和spring整合后由spring管理
		sqlSession.close();
	}

}
