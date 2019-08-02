package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.NewsContentMapper;
import com.example.demo.mapper.NewsDetailsMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.NewsType;
import com.example.demo.model.User;
import com.example.demo.model.UserExample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("news")
public class NewsController {
	
	private static final String key = "RESULT";
	@Autowired
	private UserMapper um;
	@Autowired
	private NewsContentMapper ncm;
	@Autowired
	private NewsType nt;
	@Autowired
	private NewsDetailsMapper ndm;
	
	/**
	 * �û���¼
	 * @return
	 */
	@RequestMapping("/login")
	public Map<String, Object> login(@RequestBody User user) {
		UserExample userExample = new UserExample();
		//ƴ�Ӳ�ѯ
		userExample.createCriteria().andUserNameEqualTo(user.getUserName())
			.andPasswordEqualTo(user.getPassword());
		
		List<User> uList = um.selectByExample(userExample);
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (uList.size() > 0) { // �ж��û��Ƿ����
			map.put(key, "��¼�ɹ���");
			map.put(key, uList.get(0));
			return map;
		} else {
			map.put(key, "ʧ�ܣ�");
			return map;
		}
	}
	
	/**
	 * �û�ע��
	 * @param user
	 * @return
	 */
	@RequestMapping("/addUser")
	public Map<String, String> addUser(@RequestBody User user) {
		UserExample userExample = new UserExample();
		Map<String, String> map = new HashMap<String, String>();
		//��������
		try {
			um.insertSelective(user);
			//���±�����
			um.updateByExampleSelective(user, userExample);
			map.put(key, "ע��ɹ���");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return map;
	}
	
}
