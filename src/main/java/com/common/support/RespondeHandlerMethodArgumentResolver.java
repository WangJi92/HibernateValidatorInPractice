package com.common.support;

import com.common.utils.ActionResult;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * AjaxResponse 参数注入HandlerMethod中，方便Controller中的
 * Note:当前使用通过spring mvc 配置文件中设置RequestMappingHandlerAdapter的customArgumentResolvers属性，添加自定义的参数解析器
 * {@link org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter 自定义参数解析器 customArgumentResolvers}
 *
 * @author: wangji
 * @date: 2018/06/04 16:57
 */
public class RespondeHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return ActionResult.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer
            , NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        ActionResult result = new ActionResult();
       result.setSuccess(true);
        return result;
    }
}
