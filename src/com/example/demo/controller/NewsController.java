package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.EvaluateMapper;
import com.example.demo.mapper.NewsContentMapper;
import com.example.demo.mapper.NewsDetailsMapper;
import com.example.demo.mapper.NewsImageMapper;
import com.example.demo.mapper.NewsTypeMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Evaluate;
import com.example.demo.model.NewsContent;
import com.example.demo.model.NewsDetails;
import com.example.demo.model.NewsType;
import com.example.demo.model.User;
import com.example.demo.model.UserExample;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/news")
public class NewsController {
	//���ؽ��(S��F)
	private static final String KEY = "RESULT";
	//��ʾ
	private static final String TIPS = "TIPS";
	//���ص����ݣ�һ�������Ϊ���飩
	private static final String ROWS = "ROWS_DETAIL";
	
	@Autowired
	private UserMapper um;
	@Autowired
	private NewsContentMapper ncm;
	@Autowired
	private NewsTypeMapper ntm;
	@Autowired
	private NewsDetailsMapper ndm;
	@Autowired
	private NewsImageMapper nim;
	@Autowired
	private EvaluateMapper em;
	
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
			map.put(KEY, "S");
			map.put(ROWS, uList.get(0));
			return map;
		} else {
			map.put(KEY, "F");
			return map;
		}
	}
	
	/**
	 * �����û���Ϣ
	 * @param user
	 * @return
	 */
	@RequestMapping("updateUser")
	public Map<String, String> updateUser(@RequestBody User user) {
		User users = um.selectByPrimaryKey(user.getUserId());
		Map<String, String> map = new HashMap<String, String>();
		if (users == null) {
			map.put(KEY, "F");
			map.put(TIPS, "û�и��û���Ϣ��");
			return map;
		}
		try {//���ݿ�����쳣����
			//����ָ���е��û�����
			um.updateByPrimaryKey(user);
		} catch (Exception e) {
			map.put(KEY, "F");
			map.put(TIPS, "����ʧ�ܣ�");
			return map;
		}
		map.put(KEY, "S");
		map.put(TIPS, "���³ɹ���");
		return map;
	}
	
	/**
	 * �û�ע��
	 * @param user
	 * @return
	 */
	@RequestMapping("/userRegister")
	public Map<String, String> userRegister(@RequestBody User user) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			//����ע��ʱ��
			user.setUserRegisterTime(new Date());
			//��������
			um.insertSelective(user);
			//���±�����
			um.updateByExampleSelective(user, new UserExample());
		} catch (Exception e) {
			map.put(KEY, "F");
			map.put(TIPS, "ע��ʧ�ܣ�");
			return map;
		}
		map.put(KEY, "S");
		map.put(TIPS, "ע��ɹ���");
		return map;
	}
	
	/**
	 * �����������
	 * @param newsType
	 * @return
	 */
	@RequestMapping("/addNewsType")
	public Map<String, String> addNewsType(@RequestBody NewsType newsType) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			//��������
			ntm.insert(newsType);
			//���±�����
			ntm.updateByPrimaryKey(newsType);
		} catch (Exception e) {
			map.put(KEY, "F");
			map.put(TIPS, "���ʧ�ܣ�");
			return map;
		}
		map.put(KEY, "S");
		map.put(TIPS, "��ӳɹ���");
		return map;
	}
	
	/**
	 * ɾ��ָ�����������ͱ�ǩ
	 * @param typeId
	 * @return
	 */
	@RequestMapping("/deleteNewsType")
	public Map<String, String> deleteNewsType(int typeId) {
		NewsType type = ntm.selectByPrimaryKey(typeId);
		Map<String, String> map = new HashMap<String, String>();
		if (type == null) {
			map.put(KEY, "F");
			map.put(TIPS, "û�и����������ͣ�");
			return map;
		}
		try {
			ntm.deleteByPrimaryKey(typeId);
			ntm.updateByPrimaryKey(type);
		} catch (Exception e) {
			map.put(KEY, "F");
			map.put(TIPS, "ɾ��ʧ�ܣ�");
			return map;
		}
		map.put(KEY, "F");
		map.put(TIPS, "ɾ���ɹ���");
		return map;
	}
	
	/**
	 * ��ѯ������������
	 * @return
	 */
	@RequestMapping("/queryNewsType")
	public List<NewsType> queryNewsType(){
		return ntm.selectByExample(null);
	}
	
	/**
	 * ����������������
	 * @param newsType
	 * @param typeId
	 * @return
	 */
	@RequestMapping("/updateNewsType")
	public Map<String, String> updateNewsType(@RequestBody NewsType newsType) {
		NewsType type = ntm.selectByPrimaryKey(newsType.getTypeId());
		Map<String, String> map = new HashMap<String, String>();
		if (type == null) {
			map.put(KEY, "F");
			map.put(TIPS, "û�и����������ͣ�");
			return map;
		}
		try {
			//����ָ���е�������������
			ntm.updateByPrimaryKey(newsType);
		} catch (Exception e) {
			map.put(KEY, "F");
			map.put(TIPS, "����ʧ�ܣ�");
			return map;
		}
		map.put(KEY, "S");
		map.put(TIPS, "���³ɹ���");
		return map;
	}
	
	/**
	 * �������
	 * @param newsContent
	 * @return
	 */
	@RequestMapping("/addNewsContent")
	public Map<String, String> addNewsContent(@RequestBody NewsContent newsContent) {
		Map<String, String> map = new HashMap<String, String>();
		//����ʱ��
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		//��ȡ��ǰ����
		Calendar calendar = Calendar.getInstance();
		//��ȡ��ǰʱ��
		Date date = calendar.getTime();
		try {
			//��������
			newsContent.setNewsTime(date);
			ncm.insert(newsContent);
			//���±�����
			ncm.updateByPrimaryKey(newsContent);
		} catch (Exception e) {
			map.put(KEY, "F");
			map.put(TIPS, "���ʧ�ܣ�");
			return map;
		}
		map.put(KEY, "S");
		map.put(TIPS, "��ӳɹ���");
		return map;
	}

	/**
	 * ɾ��������Ϣ
	 * @param newsId
	 * @return
	 */
	@RequestMapping("/deleteNewsContent")
	public Map<String, String> deleteNewsContent(int newsId) {
		NewsContent content = ncm.selectByPrimaryKey(newsId);
		Map<String, String> map = new HashMap<String, String>();
		if (content == null) {
			map.put(KEY, "F");
			map.put(TIPS, "û�и���������Ϣ��");
			return map;
		}
		try {
			ncm.deleteByPrimaryKey(newsId);
			ncm.updateByPrimaryKey(content);
		} catch (Exception e) {
			map.put(KEY, "F");
			map.put(TIPS, "ɾ��ʧ�ܣ�");
			return map;
		}
		map.put(KEY, "S");
		map.put(TIPS, "ɾ���ɹ���");
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
	 * ����������������
	 * @param newsContent
	 * @param newsId
	 * @return
	 */
	@RequestMapping("/updateNewsContent")
	public Map<String, String> updateNewsContent(@RequestBody NewsContent newsContent) {
		NewsContent content = ncm.selectByPrimaryKey(newsContent.getNewsId());
		Map<String, String> map = new HashMap<String, String>();
		System.out.println(content);
		if (content == null) {
			map.put(KEY, "F");
			map.put(TIPS, "û�и���������Ϣ��");
			return map;
		}
		//����ʱ��
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		//��ȡ��ǰ����
		Calendar calendar = Calendar.getInstance();
		//��ȡ��ǰʱ��
		Date date = calendar.getTime();
		try {
			newsContent.setNewsTime(date);
			//����ָ���е�����
			ncm.updateByPrimaryKey(newsContent);
		} catch (Exception e) {
			map.put(KEY, "F");
			map.put(TIPS, "����ʧ�ܣ�");
			return map;
		}
		map.put(KEY, "S");
		map.put(TIPS, "���³ɹ���");
		return map;
	}
	
	/**
	 * �������������ϸ
	 * @param newsDetails
	 * @return
	 */
	@RequestMapping("/addNewsDetails")
	public Map<String, String> addNewsDetails(@RequestBody NewsDetails newsDetails) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			//��������
			ndm.insert(newsDetails);
			//���±�����
			ndm.updateByPrimaryKey(newsDetails);
		} catch (Exception e) {
			map.put(KEY, "F");
			map.put(TIPS, "��ӳɹ���");
			return map;
		}
		map.put(KEY, "S");
		map.put(TIPS, "��ӳɹ���");
		return map;
	}
	
	/**
	 * ɾ������������ϸ��Ϣ
	 * @param detailsId
	 * @return
	 */
	@RequestMapping("/deleteNewsDetails")
	public Map<String, String> deleteNewsDetails(int detailsId) {
		NewsDetails details = ndm.selectByPrimaryKey(detailsId);
		Map<String, String> map = new HashMap<String, String>();
		if (details == null) {
			map.put(KEY, "F");
			map.put(TIPS, "û�и�������������ϸ��");
			return map;
		}
		try {
			ndm.deleteByPrimaryKey(detailsId);
			ndm.updateByPrimaryKey(details);
		} catch (Exception e) {
			map.put(KEY, "F");
			map.put(TIPS, "ɾ��ʧ�ܣ�");
			return map;
		}
		map.put(KEY, "S");
		map.put(TIPS, "ɾ���ɹ���");
		return map;
	}
	
	/**
	 * ��������������ϸ
	 * @param newsDetails
	 * @param detailsId
	 * @return
	 */
	@RequestMapping("/updateNewsDetails")
	public Map<String, String> updateNewsDetails(@RequestBody NewsDetails newsDetails) {
		NewsDetails details = ndm.selectByPrimaryKey(newsDetails.getDetailsId());
		Map<String, String> map = new HashMap<String, String>();
		if (details == null) {
			map.put(KEY, "F");
			map.put(TIPS, "û�и�����Ϣ��");
			return map;
		}
		try {
			//����ָ��������������ϸ����
			ndm.updateByPrimaryKey(newsDetails);
		} catch (Exception e) {
			map.put(KEY, "F");
			map.put(TIPS, "����ʧ�ܣ�");
			return map;
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
	
	/**
	 * �û�������������ϸ����
	 * @param evaluate
	 * @param user
	 * @param details
	 * @return
	 */
	@RequestMapping("userComment")
	public Map<String, String> userComment(@RequestBody Evaluate evaluate, @RequestBody User user, @RequestBody NewsDetails details) {
		User users = um.selectByPrimaryKey(user.getUserId());
		NewsDetails newsDetails = ndm.selectByPrimaryKey(details.getDetailsId());
		Map<String, String> map = new HashMap<String, String>();
		if (users == null) {
			map.put(KEY, "F");
			map.put(KEY, "�û���Ϣ��Ч��");
			return map;
		} else if (newsDetails == null) {
			map.put(KEY, "F");
			map.put(KEY, "��ϸ������Ϣ��Ч��");
			return map;
		}
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		try {
			//�������۵��û�
			evaluate.setUserId(user.getUserId());
			//�������۵�������ϸ����
			evaluate.setDetailsId(details.getDetailsId());
			//�������۵�����
			evaluate.setEvaluateTheme(evaluate.getEvaluateTheme());
			//�������۵�����
			evaluate.setEvaluateContent(evaluate.getEvaluateContent());
			//�������۵�ʱ�䣨��ȡ��ǰʱ����
			evaluate.setEvalueteTime(date);
			//��������
			em.insert(evaluate);
			//���±�����
			em.updateByPrimaryKey(evaluate);
		} catch (Exception e) {
			map.put(KEY, "F");
			map.put(TIPS, "������Ϣʧ�ܣ�");
			return map;
		}
		map.put(KEY, "S");
		map.put(TIPS, "���۳ɹ���");
		return map;
	}
	
}
