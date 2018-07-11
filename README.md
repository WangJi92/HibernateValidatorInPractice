##Hibernate Validator 校验框架的使用
> Hibernate Validator 用来验证实体类是否满足需求。Validator实现了Java的一项标准Bean Validation。

##简单了解
[Hibernate-validator校验框架](https://blog.csdn.net/liuchuanhong1/article/details/52042294) 这个链接中对于Hibernate整体
的使用进行了说明，平时开发过程中可以参考，了解相应的注解的使用，以及定义自定义校验的注解满足平时开发过程中的需求

[这里记录了Hibernate Validator的学习过程，以及如何使用@EgValidate](https://blog.csdn.net/u012881904/article/details/79538895)

##使用
```java
@Controller
@RequestMapping("/")
public class DoorPersonInfoController extends ExceptionHandleController {

 //    public class User implements Serializable {
//
//        @NotBlank(message = "用户名不能为空",groups = {ValidationDP1.class})
//        private String name;
//
//        @NotNull(message="地址不能为空")
//        private String address;
//        .....
//    }

    /**
     * 不使用校验分组，使用默认的分组Default.class(Bean中没有编写校验分组的信息)
     *
     * @param user
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/test0")
    @EgValidate
    public AjaxResponse testValidator0(User user) throws Exception {
        AjaxResponse response = new AjaxResponse();
        response.setResult(true);
        response.setResultMsg("校验通过");
        return response;
    }

    /**
     * 使用方法上的分组校验
     *
     * @param user
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/test1")
    @EgValidate(groups = {ValidationDP1.class})
    public AjaxResponse testValidator1(User user) throws Exception {
        AjaxResponse response = new AjaxResponse();
        response.setResult(true);
        response.setResultMsg("校验通过");
        return response;
    }

    /**
     * 使用参数上的分组校验
     *
     * @param user
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/test2")
    @EgValidate
    public AjaxResponse testValidator2(@EgValidate(groups = {ValidationDP1.class, Default.class}) User user) throws Exception {
        AjaxResponse response = new AjaxResponse();
        response.setResult(true);
        response.setResultMsg("校验通过");
        return response;
    }


    /**
     * 先校验基本的Hibernate validator 注解，通过后使用参数上的分组校验Bean
     *
     * @param user
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/test3")
    @EgValidate
    public AjaxResponse testValidator3(@EgValidate(groups = {ValidationDP1.class, Default.class}) User user, @NotNull(message = "不能为空") Integer id) throws Exception {
        AjaxResponse response = new AjaxResponse();
        response.setResult(true);
        response.setResultMsg("校验通过");
        return response;
    }
}
```

##层级校验
###简单的
1. 定义了User  Address 
2. 在需要多层校验的地方加上 @Valid
3. 在校验 User的时候，在任何的方法上加上 @EgValidate 
```
@EgValidate 
void  testValidator1(User user)
```

```java
public class User {
    
    @NotNull
    private String name;
    
    @NotNull
    @Valid //深度校验
    private List<Address> addressList;
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```
```java
public class Address {
    
    @NotBlank
    private String  addressName;

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
}
```
###如果内部是List < String >，怎么办？
1. Hibernate Validator官方文档上说需要自定义校验且Java1.8以上版本支持的注解 ElementType.TYPE_USE，参考自定义校验
2. 使用 
之前的版本
```
    @NotNull
    @Valid //深度校验
    private List<Address> addressList;
```
改变， ElementType.TYPE_USE支持放在泛型的内部，这样也是可以通过使用校验
```
    @NotNull
    @Valid //深度校验
    private List<@CharLength String> addressList;
```
自定义的校验注解
```
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CharLengthCustomeValidate.class)//处理的类
public @interface CharLength {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int length() default 64;
}

```

3. 同理如果在方法参数中有List< String> 或者其他类型的List< address >
先校验不为空，然后使用自己定义的校验处理List 内部的数据，对于其他类型的类也是可以通过自定义校验解决
```
@EgValidate 
void  testValidator1(@NotNull List<@CharLength String> address)
```
