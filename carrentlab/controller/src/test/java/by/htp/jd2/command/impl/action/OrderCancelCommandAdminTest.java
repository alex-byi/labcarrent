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
public class OrderCancelCommandAdminTest {

    @Mock
    OrderService orderService;

    @InjectMocks
    OrderCancelCommandAdmin orderCancelCommandAdmin;

    @Test
    public void orderCancelCommand() {
        ModelAndView modelAndView = orderCancelCommandAdmin.orderCancelCommand(1, "test");
        Assert.assertEquals(modelAndView.getViewName(), "redirect:/admin/order_page");
    }
}