package validator.SpecialLevelTest;

import com.common.validator.SpecialLevel.SpecialLevel;
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
 * 特殊层级校验
 *
 * @author: wangji
 * @date: 2018/07/11 11:18
 */
@Slf4j
public class SpecialLevelTest {

    private Validator validator;

    private SpecialLevel specialLevel;
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
        this.specialLevel = new SpecialLevel();
        specialLevel.setAddressList(Lists.newArrayList("生活不止眼前和苟且","还有诗和远方","哦"));
        //endregion
    }


    /**
     * 特殊层级校验 可以针对 基本类型放置在List中的数据进行校验，这样就不用进行包装啦
     */
    @Test
    public  void testSspecialLevel() {
        pringValidateStr(validator.validate(this.specialLevel));

    }

    public static void pringValidateStr(Set<ConstraintViolation<Object>> set2) {
        for (ConstraintViolation<Object> constraintViolation : set2){
            log.info("错误：" + constraintViolation.getMessage());
            log.info("字段："+constraintViolation.getPropertyPath().toString());

        }
    }
}
