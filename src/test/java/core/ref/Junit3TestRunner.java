package core.ref;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Locale;

public class Junit3TestRunner {
    @Test
    public void run() throws Exception {
        String startStr = "test";
        Class<Junit3Test> clazz = Junit3Test.class;

        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods) {
            if(method.getName().toLowerCase().startsWith(startStr)) {
                method.invoke(clazz.newInstance());
            }
        }

    }
}
