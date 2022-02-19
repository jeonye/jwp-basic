package core.ref;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.model.Question;
import next.model.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass() {
        Class<Question> clazz = Question.class;
        logger.debug("Class Name : {}", clazz.getName());

        // 필드 정보 출력
        logger.debug(">>>> Field Info <<<<");
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            logger.debug("Field Type/Name : {} {}", field.getType(), field.getName());
        }

        // 생성자 정보 출력
        logger.debug(">>>> Constructor Info <<<<");
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            logger.debug("Constructor Name : {}", constructor.getName());

            Parameter[] parameters = constructor.getParameters();
            for(Parameter parameter : parameters) {
                logger.debug("Parameter Type/Name : {} {}", parameter.getType(), parameter.getName());
            }
        }

        // 메소드 정보 출력
        logger.debug(">>>> Method Info <<<<");
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods) {
            logger.debug("Method Return Type/Name : {} {}", method.getReturnType(), method.getName());

            Parameter[] parameters = method.getParameters();
            for(Parameter parameter : parameters) {
                logger.debug("Parameter Type/Name : {} {}", parameter.getType(), parameter.getName());
            }
        }
    }
    
    @Test
    public void newInstanceWithConstructorArgs() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        String userId = "userId";
        String password = "password";
        String name = "name";
        String email = "email";

        try {
            for(Constructor<?> constructor : constructors) {
                User user = (User) constructor.newInstance(userId, password, name, email);
                logger.debug("User ID : {}", user.getUserId());
                logger.debug("User Password : {}", user.getPassword());
                logger.debug("User Name : {}", user.getName());
                logger.debug("User Email : {}", user.getEmail());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    
    @Test
    public void privateFieldAccess() {
        Class<Student> clazz = Student.class;
        logger.debug(clazz.getName());

        try {
            Student student = new Student();
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields) {
                // private 필드에 접근할 수 있도록 설정
                field.setAccessible(true);
                // 필드 값 설정
                switch (field.getName()) {
                    case "name" :
                        field.set(student, "주한");
                        break;
                    case "age" :
                        field.setInt(student, 14);
                        break;
                }

                logger.debug("Field - {} : {}", field.getName(), field.get(student));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }
}
