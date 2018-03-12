package milunas.twitt.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
 class ExampleController {

    @Autowired
    AnnotatedBeanLocator locator;


    @RequestMapping(value="/get", method = RequestMethod.GET)
      List<String> get(){
        return locator.getAllBeans();
    }

    @RequestMapping(value = "/getAnn", method = RequestMethod.GET)
     Map<String, Object> getAnn(){
        return locator.getBeansWithAnnotation(Auth.class);
    }

    @RequestMapping(value="/getRest", method = RequestMethod.GET)
     String getRest(){
        return locator.getBeansWithAnnotation();
    }

    @RequestMapping(value="/getRestMethods", method = RequestMethod.GET)
     List<String> getRestMethods(){
        return locator.getMethodsFromRestControllerClass();
    }

    @RequestMapping(value="/getUrls", method = RequestMethod.GET)
     List<String> getUrls(){
        return locator.getUrlFromRestControllerClass();
    }

    @RequestMapping(value = "/getAuthUrls", method = RequestMethod.GET)
     List<String> getAuthUrls(){
        return locator.getUrlFromAuthAnnotation();
    }

    @Auth
    @RequestMapping(value="/", method = RequestMethod.GET)
     String start(){
        return "Start!";
    }


}
