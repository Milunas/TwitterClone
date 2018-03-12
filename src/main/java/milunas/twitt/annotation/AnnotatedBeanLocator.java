package milunas.twitt.annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
  class AnnotatedBeanLocator {

    private final ConfigurableApplicationContext applicationContext;

    public AnnotatedBeanLocator( ApplicationContext applicationContext){
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }

    public List<String> getAllBeans() {
        List<String> strings = new ArrayList<>();
        ConfigurableListableBeanFactory factory = applicationContext.getBeanFactory();
        for( String name : factory.getBeanDefinitionNames() ) {
            BeanDefinition bd = factory.getBeanDefinition( name );
            strings.add(bd.getBeanClassName());
        }
        return strings;
    }

    public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> type ){
        ConfigurableListableBeanFactory factory = applicationContext.getBeanFactory();

        return factory.getBeansWithAnnotation(type);
    }

    public String getBeansWithAnnotation(){
        ConfigurableListableBeanFactory factory = applicationContext.getBeanFactory();
        Map<String, Object> map = factory.getBeansWithAnnotation(RestController.class);
        return map.entrySet().stream().findFirst().get().getKey();
    }

    public List<String> getMethodsFromRestControllerClass(){
        ConfigurableListableBeanFactory factory = applicationContext.getBeanFactory();
        Map<String, Object> map = factory.getBeansWithAnnotation(RestController.class);
        List<String> list = new ArrayList<>();
        Class classWithRestAnnotation = map.entrySet().stream().findFirst().get().getValue().getClass();
        Method[] methods = classWithRestAnnotation.getDeclaredMethods();

        for (Method method: methods){
            list.add(method.getName());
        }
        return list;
    }

    public List<String> getUrlFromRestControllerClass(){
        ConfigurableListableBeanFactory factory = applicationContext.getBeanFactory();
        Map<String, Object> map = factory.getBeansWithAnnotation(RestController.class);
        List<String> list = new ArrayList<>();
        Class classWithRestAnnotation = map.entrySet().stream().findFirst().get().getValue().getClass();
        Method[] methods = classWithRestAnnotation.getDeclaredMethods();

        for (Method method: methods){
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            list.add(requestMapping.value()[0]);
        }
        return list;
    }

    public List<String> getUrlFromAuthAnnotation(){
        ConfigurableListableBeanFactory factory = applicationContext.getBeanFactory();
        Map<String, Object> map = factory.getBeansWithAnnotation(RestController.class);
        List<String> list = new ArrayList<>();
        Class classWithRestAnnotation = map.entrySet().stream().findFirst().get().getValue().getClass();
        Method[] methods = classWithRestAnnotation.getDeclaredMethods();

        for (Method method: methods){
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            if(method.isAnnotationPresent(Auth.class))
            list.add(requestMapping.value()[0]);
        }
        return list;
    }

}
