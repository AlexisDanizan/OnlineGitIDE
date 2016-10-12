package main.java.Controller;

import org.junit.Assert;
import org.junit.Test;

import org.springframework.web.servlet.ModelAndView;
import Controller.TestController;


/**
 * Created by Alexis on 12/10/2016.
 */
public class TestControllerTests {

    @Test
    public void testHandleRequestView() throws Exception{
        TestController controller = new TestController();
        ModelAndView modelAndView = controller.handleRequest(null, null);
        Assert.assertEquals("view/test.jsp", modelAndView.getViewName());
    }
}
