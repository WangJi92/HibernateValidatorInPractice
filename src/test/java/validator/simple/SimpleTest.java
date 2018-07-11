package validator.simple;

import com.common.validator.Simple.SimpleValidator;
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
 * 简单校验测试类（快速失败模式-一旦发现错误就返回）
 *
 * @author: wangji
 * @date: 2018/07/11 10:01
 */
@Slf4j
public class SimpleTest {

    private   Validator validator;

    private SimpleValidator simpleValidator;

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
         this.simpleValidator = new SimpleValidator();
        simpleValidator.setEmail("983433479@qq.com")
                 .setId(5)
                 .setPassWord("password")
                 .setUserName("汪吉");
        //endregion
    }


    @Test
    public  void testMin() {
        this.simpleValidator.setId(-1);
        pringValidateStr(validator.validate(this.simpleValidator));
    }

    @Test
    public  void testMax() {
        this.simpleValidator.setId(20);
        pringValidateStr(validator.validate(this.simpleValidator));
    }

    @Test
    public  void testNull() {
        this.simpleValidator.setId(null);
        pringValidateStr(validator.validate(this.simpleValidator));
    }

    @Test
    public  void testEmail() {
        this.simpleValidator.setEmail("test.com");
        pringValidateStr(validator.validate(this.simpleValidator));
    }

    @Test
    public  void testChinese() {
        this.simpleValidator.setUserName("wangji");
        pringValidateStr(validator.validate(this.simpleValidator));
    }

    @Test
    public  void testSize() {
        this.simpleValidator.setUserName("汪");
        pringValidateStr(validator.validate(this.simpleValidator));
    }
    @Test
    public  void testPassword() {
        this.simpleValidator.setPassWord(null);
        pringValidateStr(validator.validate(this.simpleValidator));
    }

    /**
     * 针对某个Object 进行校验
     */
    @Test
    public  void testValidate() {
        this.simpleValidator.setPassWord(null);
        pringValidateStr(validator.validate(this.simpleValidator));
    }

    /**
     * 针对某个class上的字段进行校验
     */
    @Test
    public  void testValidateValue() {
        Set<ConstraintViolation<SimpleValidator>> constraintViolations = validator.validateValue(SimpleValidator.class,"userName","中");
        pringValidateStrSimpleValidator(constraintViolations);
    }

    /**
     * 针对某个实例的属性进行校验
     */
    @Test
    public  void testValidateProperty() {
        this.simpleValidator.setUserName("中");
        Set<ConstraintViolation<SimpleValidator>> constraintViolations = validator.validateProperty(simpleValidator,"userName");;
        pringValidateStrSimpleValidator(constraintViolations);
    }


    public static void pringValidateStr(Set<ConstraintViolation<Object>> set2) {
        for (ConstraintViolation<Object> constraintViolation : set2){
            log.info("错误：" + constraintViolation.getMessage());
            log.info("字段："+constraintViolation.getPropertyPath().toString());

        }
    }

    public static void pringValidateStrSimpleValidator(Set<ConstraintViolation<SimpleValidator>> set2) {
        for (ConstraintViolation<SimpleValidator> constraintViolation : set2){
            log.info("错误：" + constraintViolation.getMessage());
            log.info("字段："+constraintViolation.getPropertyPath().toString());

        }
    }



}
