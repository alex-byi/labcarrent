package by.htp.jd2.command.impl.action;

import by.htp.jd2.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderCompleteCommandTest {

    @Mock
    OrderService orderService;

    @InjectMocks
    OrderCompleteCommand orderCompleteCommand;

    @Test
    public void orderComplete() {
        ModelAndView modelAndView = orderCompleteCommand.orderComplete(1);
        Assert.assertEquals(modelAndView.getViewName(), "redirect:/admin/order_page");
    }
}