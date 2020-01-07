package by.htp.jd2.command.impl.action;

import by.htp.jd2.service.CarService;
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
public class ConfirmOrderCommandTest {
    @Mock
    CarService carService;

    @Mock
    OrderService orderService;

    @InjectMocks
    ConfirmOrderCommand confirmOrderCommand;

    @Test
    public void addMoneyAdmin() {
        ModelAndView modelAndView = confirmOrderCommand.confirmOrder(1,
                "", "", "", 1, 100, 1);
        Assert.assertEquals(modelAndView.getViewName(), "redirect:/user/orders");
    }
}