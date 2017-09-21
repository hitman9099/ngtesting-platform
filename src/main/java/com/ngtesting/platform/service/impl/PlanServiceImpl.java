package com.ngtesting.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ngtesting.platform.entity.Dict;
import com.ngtesting.platform.entity.TestPlan;
import com.ngtesting.platform.entity.TestRun;
import com.ngtesting.platform.service.PlanService;
import com.ngtesting.platform.service.RunService;
import com.ngtesting.platform.util.BeanUtilEx;
import com.ngtesting.platform.vo.TestPlanVo;
import com.ngtesting.platform.vo.TestRunVo;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class PlanServiceImpl extends BaseServiceImpl implements PlanService {

    @Autowired
    RunService runService;

    @Override
    public List<TestPlan> query(Long projectId) {
        DetachedCriteria dc = DetachedCriteria.forClass(TestPlan.class);

        if (projectId != null) {
            dc.add(Restrictions.eq("projectId", projectId));
        }

        dc.add(Restrictions.eq("deleted", Boolean.FALSE));
        dc.add(Restrictions.eq("disabled", Boolean.FALSE));
        dc.addOrder(Order.desc("createTime"));
        dc.addOrder(Order.asc("id"));
        List<TestPlan> ls = findAllByCriteria(dc);

        return ls;
    }

    @Override
    public TestPlanVo getById(Long caseId) {
        TestPlan po = (TestPlan) get(TestPlan.class, caseId);
        TestPlanVo vo = genVo(po);

        return vo;
    }

    @Override
    public List<TestPlanVo> genVos(List<TestPlan> pos) {
        List<TestPlanVo> vos = new LinkedList<TestPlanVo>();

        for (TestPlan po : pos) {
            TestPlanVo vo = genVo(po);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public TestPlanVo genVo(TestPlan po) {
        TestPlanVo vo = new TestPlanVo();
        BeanUtilEx.copyProperties(vo, po);

        for (TestRun run : po.getRuns()) {
            TestRunVo runVo = runService.genVo(run);
            vo.getRunVos().add(runVo);
        }

        return vo;
    }

    @Override
    public TestPlan save(JSONObject json) {
        Long id = json.getLong("id");

        TestPlan po;
        TestPlanVo vo = JSON.parseObject(JSON.toJSONString(json), TestPlanVo.class);

        if (id != null) {
            po = (TestPlan)get(TestPlan.class, id);
        } else {
            po = new TestPlan();
        }
        BeanUtilEx.copyProperties(po, vo);

        saveOrUpdate(po);

        return po;
    }

    @Override
    public TestPlan delete(Long vo, Long clientId) {
        // TODO Auto-generated method stub
        return null;
    }

    private Integer getChildMaxOrderNumb(TestPlan parent) {
        String hql = "select max(ordr) from TestPlan where parentId = " + parent.getId();
        Integer maxOrder = (Integer) getByHQL(hql);

        if (maxOrder == null) {
            maxOrder = 0;
        }

        return maxOrder;
    }

    @Override
    public void dictPers() {
        List<String> fileList = new ArrayList<String>();
        traverseFolder("/Users/aaron/work/Testing/词库", fileList);

        int count = 0;
        for (String filePath : fileList) {
            if (count++ > 3) {
                return;
            }
            String catrgory = filePath.substring(0, filePath.lastIndexOf("/"));

            FileReader reader = null;
            try {
                reader = new FileReader(filePath);
                BufferedReader br = new BufferedReader(reader);
                String str = null;

                while ((str = br.readLine()) != null) {
                    String[] arr = str.split(":");
                    if (arr.length == 0) {
                        continue;
                    }

                    Dict dict = new Dict(catrgory, arr[0]);
                    saveOrUpdate(dict);

                    int i = 0;
                    for (String s : arr) {
                        if (i++ == 0) {
                            continue;
                        }
                        Dict dict2 = new Dict(catrgory, s, dict.getId());
                        saveOrUpdate(dict2);
                    }
                }

                br.close();
                reader.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public List traverseFolder(String path, List<String> fileList) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder(file2.getAbsolutePath(), fileList);
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                        if (file2.getAbsolutePath().lastIndexOf(".txt") > 0) {
                            fileList.add(file2.getAbsolutePath());
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }

        return fileList;
    }

}

