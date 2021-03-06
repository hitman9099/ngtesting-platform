package com.ngtesting.platform.action;

import com.alibaba.fastjson.JSONObject;
import com.ngtesting.platform.bean.websocket.OptFacade;
import com.ngtesting.platform.config.Constant;
import com.ngtesting.platform.config.WsConstant;
import com.ngtesting.platform.service.AlertService;
import com.ngtesting.platform.util.AuthPassport;
import com.ngtesting.platform.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(Constant.API_PATH_CLIENT + "alert/")
public class AlertAction extends BaseAction {
    @Autowired
    private OptFacade optFacade;

	@Autowired
	AlertService alertService;

    @AuthPassport(validate = true)
    @RequestMapping(value = "markAllRead", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> markAllRead(HttpServletRequest request, @RequestBody JSONObject json) {
        Map<String, Object> ret = new HashMap<String, Object>();

        UserVo userVo = (UserVo) request.getSession().getAttribute(Constant.HTTP_SESSION_USER_KEY);

		alertService.markAllReadPers(json.getString("ids"));
        optFacade.opt(WsConstant.WS_TODO, userVo.getId().toString());

        ret.put("code", Constant.RespCode.SUCCESS.getCode());
        return ret;
    }

}
