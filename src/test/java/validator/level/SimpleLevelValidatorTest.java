package validator.level;

import com.common.validator.Level.Address1;
import com.common.validator.Level.SimpleLevelValidator;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 简单层级校验
 *
 * @author: wangji
 * @date: 2018/07/11 10:43
 */
@Slf4j
public class SimpleLevelValidatorTest {

    private Validator validator;

    private SimpleLevelValidator simpleLevelValidator;
    @Before
    public void before(){
        //region 设置校验工厂
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .failFast( false ) //快速失败模式
                .buildValidatorFactory();
        this.validator = validatorFactory.getValidator();
        //endregion
        //region 设置校验实体默认值
        this.simpleLevelValidator = new SimpleLevelValidator();
        Address1 address1 = new Address1();
        address1.setAddressName("杭州");
        this.simpleLevelValidator.setAddress1(address1)
                .setMessages(Lists.newArrayList("生活不止眼前和苟且","还有诗和远方"))
                .setAddress1s(Lists.newArrayList(address1));
        //endregion
    }

    @Test
    public  void testLevelValid() {
        //region 第一层校验
        this.simpleLevelValidator.setAddress1(null);
        pringValidateStr(validator.validate(this.simpleLevelValidator));
        //endregion

        //region 第二层校验 Address1包装校验
        Address1 address1 = new Address1();
        address1.setAddressName("");
        this.simpleLevelValidator.setAddress1(address1);
        pringValidateStr(validator.validate(this.simpleLevelValidator));
        //endregion

        //region 第二层校验 List<Address1> 层级校验
        Address1 address2 = new Address1();
        address1.setAddressName("");
        this.simpleLevelValidator.setAddress1s(Lists.newArrayList(address2));
        pringValidateStr(validator.validate(this.simpleLevelValidator));
        //endregion
    }

    /**
     * 判断集合、Map、字符串等等  @NotEmpty
     */
    @Test
    public  void testCollection() {
        this.simpleLevelValidator.setMessages(Lists.newArrayList());
        pringValidateStr(validator.validate(this.simpleLevelValidator));

        this.simpleLevelValidator.setMessages(null);
        pringValidateStr(validator.validate(this.simpleLevelValidator));

    }


    public static void pringValidateStr(Set<ConstraintViolation<Object>> set2) {
        for (ConstraintViolation<Object> constraintViolation : set2){
            log.info("错误：" + constraintViolation.getMessage());
            log.info("字段："+constraintViolation.getPropertyPath().toString());

        }
    }
}
