package milunas.twitt.annotation;

import org.springframework.web.bind.annotation.RequestMapping;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 class AnnotationFun {

     public List<String> getUrlsFromMethods() throws NoSuchFieldException, IllegalAccessException {
        List<Method> methods = getAllLoadedMethods();
        List<String> urls = new ArrayList<>();
        for (Method method : methods) {
            RequestMapping mappingMethod = method.getAnnotation(RequestMapping.class);
            if (mappingMethod != null) {
                if(method.getAnnotation(Auth.class)!=null){
                    String[] strings = mappingMethod.value();
                    urls.addAll(Arrays.asList(strings));
                }
            }
        }
       return urls;
    }

    private List<Method> getAllLoadedMethods() throws NoSuchFieldException, IllegalAccessException {
        List<Class> allLoadedClassList = getAllLoadedClasses();
        List<Method> methodList = new ArrayList<>();
        for (Class loadedClass: allLoadedClassList){
            Method[] methods = loadedClass.getDeclaredMethods();
            methodList.addAll(Arrays.asList(methods));
        }
        return methodList;
    }

    private List<Class> getAllLoadedClasses() throws NoSuchFieldException, IllegalAccessException{
        Field classField = ClassLoader.class.getDeclaredField("classes");
        classField.setAccessible(true);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return  (List<Class>) classField.get(classLoader);
    }

}
