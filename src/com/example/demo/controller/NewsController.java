package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.NewsContentMapper;
import com.example.demo.mapper.NewsDetailsMapper;
import com.example.demo.mapper.NewsTypeMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.NewsContent;
import com.example.demo.model.NewsDetails;
import com.example.demo.model.NewsType;
import com.example.demo.model.User;
import com.example.demo.model.UserExample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/news")
public class NewsController {
	
	private static final String key = "RESULT";
	private static final String rows = "ROWS_DETAIL";
	
	@Autowired
	private UserMapper um;
	@Autowired
	private NewsContentMapper ncm;
	@Autowired
	private NewsTypeMapper ntm;
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
			map.put(rows, uList.get(0));
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
	
	/**
	 * �����������
	 * @param newsContent
	 * @return
	 */
	@RequestMapping("/addNewsType")
	public Map<String, String> addNewsType(@RequestBody NewsType newsType) {
		Map<String, String> map = new HashMap<String, String>();
		//��������
		try {
			ntm.insert(newsType);
			//���±�����
			ntm.updateByPrimaryKey(newsType);
			map.put(key, "��ӳɹ���");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return map;
	}
	
	@RequestMapping("/queryNewsType")
	public List<NewsType> queryNewsType(){
		return ntm.selectByExample(null);
	}
	
	/**
	 * �������
	 * @param newsContent
	 * @return
	 */
	@RequestMapping("/addNewsContent")
	public Map<String, String> addNewsContent(@RequestBody NewsContent newsContent) {
		Map<String, String> map = new HashMap<String, String>();
		//��������
		try {
			ncm.insert(newsContent);
			//���±�����
			ncm.updateByPrimaryKey(newsContent);
			map.put(key, "��ӳɹ���");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * ��ѯ������������
	 * @return
	 */
	@RequestMapping("/queryNewsContent")
	public List<NewsContent> queryNewsContent(){
		return ncm.selectByExample(null);
	}
	
	/**
	 * �������������ϸ
	 * @param newsContent
	 * @return
	 */
	@RequestMapping("/addNewsDetails")
	public Map<String, String> addNewsDetails(@RequestBody NewsDetails newsDetails) {
		Map<String, String> map = new HashMap<String, String>();
		//��������
		try {
			ndm.insert(newsDetails);
			//���±�����
			ndm.updateByPrimaryKey(newsDetails);
			map.put(key, "��ӳɹ���");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return map;
	}

	/**
	 * ��ѯ����������ϸ����
	 * @return
	 */
	@RequestMapping("/queryNewsDetails")
	public List<NewsDetails> queryNewsDetails(){
		return ndm.selectByExample(null);
	}
	
}
