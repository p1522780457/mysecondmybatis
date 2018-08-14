package com.pangw.mapper;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import com.pangw.dao.Order;
import com.pangw.dao.OrderUser;
import com.pangw.dao.QueryVo;
import com.pangw.dao.User;

public interface UserMapper {
	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	User queryUserById(int id);

	/**
	 * 根据用户名查询用户
	 * 
	 * @param username
	 * @return
	 */
	List<User> queryUserByUsername(String username);

	/**
	 * 保存用户
	 * 
	 * @param user
	 */
	void saveUser(User user);

	/**
	 * 查找客户
	 * 
	 * @param queryVo
	 * @return
	 */

	List<User> queryUserByQueryVo(QueryVo queryVo);

	/**
	 * 获取用户数量
	 */
	int queryUserCount();

	/**
	 * 
	 * 
	 * @param user
	 * @return
	 */
	List<User> queryUserByWhere(User user);

	List<User> queryUserByWhere2(User user);

	List<User> queryUserByWhere3(User user);

	List<User> queryUserByIds(QueryVo queryVo);
	
	List<OrderUser> queryOrderUser();
	
	List<Order> queryOrderUserResultMap();
	
	List<User> queryUserOrder ();

}
