package com.module.userinfo.service;

import com.common.validator.Aop.EgValidator.EgValidate;
import com.common.validator.Group.Identification.First;
import com.module.userinfo.entity.User;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * descrption:
 * authohr: wangji
 * date: 2017-07-31 18:13
 */
public interface IUserInfoService {

    @NotNull(message = "不能为空")
    User getUserInfoById(@NotNull(message = "不能为空") Integer id);

    @EgValidate(groups = {First.class})
    void save(User user);

     List<User> findAll();

     void test(String address,String name);
}
