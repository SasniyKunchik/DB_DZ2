package reflection;

import javax.sound.midi.MetaEventListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("reflection.Human");

//        System.out.println(aClass.getName());

        Field fields[] = aClass.getDeclaredFields();
//        System.out.println(fields.length);
        for (Field field : fields) {
//            System.out.println(field.getName());
        }

        Constructor constructor = aClass.getDeclaredConstructor(double.class);
        constructor.setAccessible(true);
        Human human = (Human) constructor.newInstance(1.85);
//        System.out.println(human.getHeight());

        Method methods[] = aClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + " " + method.getReturnType() + " " + method.getParameterCount());
        }

        Method method = aClass.getMethod("grow", Double.TYPE);
        method.invoke(human,0.2);
        System.out.println(human.getHeight());

        Method methodWithReturn = aClass.getMethod("getHeight");
        Double result = (Double) methodWithReturn.invoke(human);
        System.out.println(result.getClass());
    }
}
