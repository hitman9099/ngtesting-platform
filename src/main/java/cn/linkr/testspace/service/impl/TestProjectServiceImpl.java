package cn.linkr.testspace.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.linkr.testspace.dao.BaseDao;
import cn.linkr.testspace.dao.ProjectDao;
import cn.linkr.testspace.entity.EvtEvent;
import cn.linkr.testspace.entity.EvtGuest;
import cn.linkr.testspace.entity.EvtScheduleItem;
import cn.linkr.testspace.entity.EvtSession;
import cn.linkr.testspace.entity.TestCase;
import cn.linkr.testspace.entity.TestProject;
import cn.linkr.testspace.entity.EvtEvent.EventStatus;
import cn.linkr.testspace.service.GuestService;
import cn.linkr.testspace.service.TestCaseService;
import cn.linkr.testspace.service.TestProjectService;
import cn.linkr.testspace.util.BeanUtilEx;
import cn.linkr.testspace.util.Constant;
import cn.linkr.testspace.util.Constant.TreeNodeType;
import cn.linkr.testspace.util.StringUtil;
import cn.linkr.testspace.vo.GuestVo;
import cn.linkr.testspace.vo.Page;
import cn.linkr.testspace.vo.SessionVo;
import cn.linkr.testspace.vo.TestCaseTreeVo;
import cn.linkr.testspace.vo.TestCaseVo;
import cn.linkr.testspace.vo.TestProjectVo;

@Service
public class TestProjectServiceImpl extends BaseServiceImpl implements
		TestProjectService {

	@Autowired
	private ProjectDao projectDao;
	
	@Override
//	@Cacheable(value="companyProjects",key="#companyId.concat('-').concat(#isActive)")
	public Map<String, Object> listCache(Long companyId, String isActive) {
		DetachedCriteria dc = DetachedCriteria.forClass(TestProject.class);

		if (StringUtil.isNotEmpty(isActive)) {
			dc.add(Restrictions.eq("isActive", Boolean.valueOf(isActive)));
		}

		dc.add(Restrictions.eq("deleted", Boolean.FALSE));
		dc.add(Restrictions.eq("disabled", Boolean.FALSE));
		dc.addOrder(Order.asc("path"));
		dc.addOrder(Order.asc("id"));
		List<TestProject> pos = findAllByCriteria(dc);
		
		Map<String, Integer> out = new HashMap<String, Integer>();
		LinkedList<TestProjectVo> vos = this.genVos(pos, out);

		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("models", vos);
		ret.put("maxLevel", out.get("maxLevel"));
		return ret;
	}

	@Override
    public TestProject getDetail(Long id) {
    	TestProject po = (TestProject) get(TestProject.class, id);
		
		return po;
    }

	@Override
	public TestProject delete(Long vo, Long clientId) {

		return null;
	}

	@Override
	public TestProject save(Long id, String value, Integer type, Long pid,
			Long id2) {

		return null;
	}
	
	@Override
	public LinkedList<TestProjectVo> genVos(List<TestProject> pos, Map<String, Integer> ret) {
		TestProjectVo root = null;
		int maxLevel = 0;
		
		String childrenPath = "";
		Map<Long, TestProjectVo> nodeMap = new HashMap<Long, TestProjectVo>();
	      for (TestProject po : pos) {
	        	Long id = po.getId();
	        	TreeNodeType type = po.getType();
	        	Long pid = po.getParentId();
	        	
	        	TestProjectVo newNode = genVo(po);
	        	
	        	nodeMap.put(id, newNode);
	        	
	        	if (type.equals(Constant.TreeNodeType.root)) {
	        		root = newNode;
	        		continue;
	        	}
	        	
	        	TestProjectVo pvo = nodeMap.get(pid);
	        	LinkedList<TestProjectVo> children = pvo.getChildren();
	        	children.add(newNode);
	        	
	        	childrenPath += newNode.getPath() + ",";
	        	if (po.getLevel() > maxLevel) {
					maxLevel = po.getLevel();
				}
	        }
	        
	      LinkedList<TestProjectVo> voList = new LinkedList<TestProjectVo>();
	      this.toOrderList(root, childrenPath, voList);
		
        ret.put("maxLevel", maxLevel);
        return voList;
	}

	@Override
	public TestProjectVo genVo(TestProject po) {
		TestProjectVo vo = new TestProjectVo();
		BeanUtilEx.copyProperties(vo, po);
		if (po.getParentId() == null) {
			vo.setParentId(null);
		}
		return vo;
	}
	
	@Override
	public int countDescendantsNumb(Long id, String childrenPath) {
		int count = 0;  
        Pattern p = Pattern.compile("/"  + id + "/");  
        Matcher m = p.matcher(childrenPath);  
        while (m.find()) {  
            count++;  
        }  
        return count;
	}

	@Override
	public void toOrderList(TestProjectVo root, String childrenPath, LinkedList<TestProjectVo> resultList) {
		Iterator<TestProjectVo> it = root.getChildren().iterator();
		while (it.hasNext()) {
			TestProjectVo vo = it.next();
			
        	if (!vo.getType().toString().equals(Constant.TreeNodeType.root.toString())) {
        		resultList.add(vo);
        		if (vo.getChildren().size() > 0) {
					TestProjectVo firstChild = vo.getChildren().get(0);
					
					firstChild.setIsFirstChild(true);
					
					int count = this.countDescendantsNumb(vo.getId(), childrenPath);
					firstChild.setParentDescendantNumber(count);
					firstChild.setBrotherNumb(count);
        		}
			}
        	
        	this.toOrderList(vo, childrenPath, resultList);
		}
	}
}
