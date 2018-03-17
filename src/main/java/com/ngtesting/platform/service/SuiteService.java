package com.ngtesting.platform.service;

import com.alibaba.fastjson.JSONObject;
import com.ngtesting.platform.entity.TestCaseInSuite;
import com.ngtesting.platform.entity.TestSuite;
import com.ngtesting.platform.vo.TestCaseInSuiteVo;
import com.ngtesting.platform.vo.TestSuiteVo;
import com.ngtesting.platform.vo.UserVo;

import java.util.List;

public interface SuiteService extends BaseService {

	List<TestSuite> query(JSONObject json);
	TestSuiteVo getById(Long caseId);
	TestSuite save(JSONObject json, UserVo optUser);
	TestSuite delete(Long vo, Long userId);

	List<TestSuite> list(Long projectId, String type);

	List<TestSuiteVo> genVos(List<TestSuite> pos);

    TestSuite saveCases(JSONObject json, UserVo optUser);

	TestSuite saveCases(Long planId, Long runId, Object[] ids, UserVo optUser);

	TestSuiteVo genVo(TestSuite po);

    TestCaseInSuiteVo genCaseVo(TestCaseInSuite po);

    TestSuite updatePo(TestSuiteVo vo);
}
