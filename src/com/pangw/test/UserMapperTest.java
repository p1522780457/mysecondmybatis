package com.pangw.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.pangw.dao.Order;
import com.pangw.dao.OrderUser;
import com.pangw.dao.QueryVo;
import com.pangw.dao.User;
import com.pangw.mapper.UserMapper;

public class UserMapperTest {
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
	public void testQueryUserById() {
		// 获取sqlSession，和spring整合后由spring管理
		SqlSession sqlSession = this.sqlSessionFactory.openSession();

		// 从sqlSession中获取Mapper接口的代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 执行查询方法
		User user = userMapper.queryUserById(35);
		System.out.println(user);

		// 和spring整合后由spring管理
		sqlSession.close();
	}

	@Test
	public void testQuerUserBynames() {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = userMapper.queryUserByUsername("王");
		for (User u : list) {
			System.out.println(u.toString());
		}
		sqlSession.close();
	}

	@Test
	public void saveUser() {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 创建保存对象
		User user = new User();
		user.setUsername("刘备");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("蜀国");

		userMapper.saveUser(user);

		sqlSession.commit();
		sqlSession.close();

	}

	@Test
	public void testQueryUserByQueryVo() {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		QueryVo queryVo = new QueryVo();
		User user = new User();
		user.setUsername("张");
		queryVo.setUser(user);

		List<User> list = userMapper.queryUserByQueryVo(queryVo);

		for (User u : list) {
			System.out.println(u.toString());
		}

	}

	@Test
	public void testQueryUserCount() {

		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		int count = userMapper.queryUserCount();
		System.out.println(count);

	}

	@Test
	public void testQueryUserWhere() {

		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		User user = new User();
		user.setSex("1");
		user.setUsername("张");
		List<User> list = userMapper.queryUserByWhere3(user);
		foreach(list);
	}

	@Test
	public void testQueryUserIds() {

		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		QueryVo queryVo = new QueryVo();
		List<Integer> ids = new ArrayList<>();
		ids.add(1);
		ids.add(10);
		ids.add(24);
		queryVo.setIds(ids);

		List<User> list = userMapper.queryUserByIds(queryVo);

		foreach(list);

	}

	@Test
	public void testQueryOrderUser() {

		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		List<OrderUser> list = userMapper.queryOrderUser();
		for (OrderUser ou : list) {
			System.out.println(ou.toString());
		}
		sqlSession.close();
	}

	@Test
	public void testQueryOrderUser2() {

		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		List<Order> list = userMapper.queryOrderUserResultMap();
		for (Order ou : list) {
			System.out.println(ou.toString());
		}
		sqlSession.close();
	}

	@Test
	public void testQueryUserOrder() {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		List<User> list = userMapper.queryUserOrder();

		foreach(list);
		sqlSession.close();

	}

	public void foreach(List<User> list) {
		for (User user : list) {
			System.out.println(user.toString());
		}
	}

}
