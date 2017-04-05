package com.ngtesting.platform.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ngtesting.platform.entity.SysOrgRole;
import com.ngtesting.platform.entity.SysProjectRole;
import com.ngtesting.platform.entity.SysRole;
import com.ngtesting.platform.entity.SysUser;
import com.ngtesting.platform.service.OrgPriviledgeService;
import com.ngtesting.platform.service.OrgRoleService;
import com.ngtesting.platform.service.ProjectPriviledgeService;
import com.ngtesting.platform.service.ProjectRoleService;
import com.ngtesting.platform.service.RoleService;
import com.ngtesting.platform.util.AuthPassport;
import com.ngtesting.platform.util.Constant;
import com.ngtesting.platform.util.Constant.RespCode;
import com.ngtesting.platform.vo.OrgGroupVo;
import com.ngtesting.platform.vo.OrgPriviledgeVo;
import com.ngtesting.platform.vo.OrgRoleVo;
import com.ngtesting.platform.vo.Page;
import com.ngtesting.platform.vo.ProjectPriviledgeVo;
import com.ngtesting.platform.vo.ProjectRoleVo;
import com.ngtesting.platform.vo.RelationOrgGroupUserVo;
import com.ngtesting.platform.vo.RoleVo;
import com.ngtesting.platform.vo.UserVo;


@Controller
@RequestMapping(Constant.API_PATH_CLIENT + "project_role/")
public class ProjectRoleAction extends BaseAction {
	@Autowired
	ProjectRoleService projectRoleService;
	@Autowired
	ProjectPriviledgeService projectPriviledgeService;
	
	@AuthPassport(validate = true)
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, @RequestBody JSONObject json) {
		Map<String, Object> ret = new HashMap<String, Object>();
		
		UserVo userVo = (UserVo) request.getSession().getAttribute(Constant.HTTP_SESSION_USER_KEY);
		Long orgId = userVo.getDefaultOrgId();
		
		String keywords = json.getString("keywords");
		String disabled = json.getString("disabled");
		int currentPage = json.getInteger("currentPage") == null? 0: json.getInteger("currentPage") - 1;
		int itemsPerPage = json.getInteger("itemsPerPage") == null? Constant.PAGE_SIZE: json.getInteger("itemsPerPage");
		
		Page page = projectRoleService.listByPage(orgId, keywords, disabled, currentPage, itemsPerPage);
		List<ProjectRoleVo> vos = projectRoleService.genVos(page.getItems());
        
		ret.put("totalItems", page.getTotal());
        ret.put("data", vos);
		ret.put("code", Constant.RespCode.SUCCESS.getCode());
		return ret;
	}
	
	@AuthPassport(validate = true)
	@RequestMapping(value = "get", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get(HttpServletRequest request, @RequestBody JSONObject req) {
		Map<String, Object> ret = new HashMap<String, Object>();
		
		UserVo userVo = (UserVo) request.getSession().getAttribute(Constant.HTTP_SESSION_USER_KEY);
		Long orgId = userVo.getDefaultOrgId();
		Long orgRoleId = req.getLong("id");
		
		List<ProjectPriviledgeVo> orgPriviledges = projectPriviledgeService.listPriviledgesByOrg(orgId, orgRoleId);
		if (orgRoleId == null) {
			ret.put("projectRole", new OrgGroupVo());
	        ret.put("projectPriviledges", orgPriviledges);
			ret.put("code", Constant.RespCode.SUCCESS.getCode());
			return ret;
		}
		
		SysProjectRole po = (SysProjectRole) projectRoleService.get(SysProjectRole.class, orgRoleId);
		ProjectRoleVo vo = projectRoleService.genVo(po);
        
        ret.put("projectRole", vo);
        ret.put("projectPriviledges", orgPriviledges);
		ret.put("code", Constant.RespCode.SUCCESS.getCode());
		return ret;
	}
	
	@AuthPassport(validate = true)
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, @RequestBody JSONObject json) {
		Map<String, Object> ret = new HashMap<String, Object>();
		
		UserVo userVo = (UserVo) request.getSession().getAttribute(Constant.HTTP_SESSION_USER_KEY);
		Long orgId = userVo.getDefaultOrgId();
		
		ProjectRoleVo projectRoleVo = JSON.parseObject(JSON.toJSONString(json.get("projectRole")), ProjectRoleVo.class);
		SysProjectRole po = projectRoleService.save(projectRoleVo, orgId);
		
		List<ProjectPriviledgeVo> projectPriviledges = (List<ProjectPriviledgeVo>) json.get("projectPriviledges");
		boolean success = projectPriviledgeService.saveProjectPriviledges(po.getId(), projectPriviledges);
		
		ret.put("code", Constant.RespCode.SUCCESS.getCode());
		return ret;
	}
	
	@AuthPassport(validate = true)
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @RequestBody JSONObject to) {
		Map<String, Object> ret = new HashMap<String, Object>();
		
		boolean success = projectRoleService.delete(to.getLong("id"));
		
		ret.put("code", Constant.RespCode.SUCCESS.getCode());
		return ret;
	}

}