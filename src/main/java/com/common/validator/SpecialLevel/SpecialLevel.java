package com.common.validator.SpecialLevel;

import com.common.validator.SpecialLevel.constraints.CharLength;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

/**
 * 特殊层级校验
 * <P>Hibernate Validator官方文档上说需要自定义校验且Java1.8以上版本支持的注解 ElementType.TYPE_USE，参考自定义校验</P>
 * <P>改变， ElementType.TYPE_USE支持放在泛型的内部，这样也是可以通过使用校验</P>

    @NotNull
    @Valid //深度校验
    private List<@CharLength String> addressList;
 *
 * @author: wangji
 * @date: 2018/07/11 11:07
 */
public class SpecialLevel {

    @Valid
    @NotEmpty(message = "数据不能为空")
    private List<@CharLength(message = "长度超出限制",length = 2) String> addressList;

    public List<String> getAddressList() {
        return addressList;
    }

    public SpecialLevel setAddressList(List<String> addressList) {
        this.addressList = addressList;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("addressList", addressList)
                .toString();
    }
}
