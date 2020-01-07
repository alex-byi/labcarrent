package by.htp.jd2.command.impl.action;

import by.htp.jd2.service.CarService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DelCarCommandTest {

    @Mock
    CarService carService;

    @InjectMocks
    DelCarCommand delCarCommand;

    @Test
    public void deactivateCar() {
        ModelAndView modelAndView = delCarCommand.deactivateCar(1);
        Assert.assertEquals(modelAndView.getViewName(), "redirect:/admin/control_car");
    }
}