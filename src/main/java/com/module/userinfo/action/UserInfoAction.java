package com.module.userinfo.action;

import com.common.utils.ActionResult;
import com.common.validator.Aop.EgValidator.EgValidate;
import com.common.validator.Group.Identification.First;
import com.module.userinfo.entity.User;
import com.module.userinfo.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;



@Controller
@RequestMapping("/")
@Slf4j
public class UserInfoAction {

    @Autowired
    public IUserInfoService userInfoService;

    @RequestMapping("/findById")
    @ResponseBody
    @EgValidate(groups = {First.class})
    public ActionResult findById(@NotNull(message = "不能为空啊")Integer id) {
        ActionResult result = new ActionResult(true);
        User user = userInfoService.getUserInfoById(id);
        if (user != null) {
            result.setData(user);
        } else {
            result.setMessage("error");
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("/error")
    @ResponseBody
    public ActionResult saveUser( User user) {
        ActionResult result = new ActionResult(true);
        userInfoService.save(new User());
        return result;
    }

}
