package by.htp.jd2.command.impl.action;

import by.htp.jd2.service.CarService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

@RunWith(MockitoJUnitRunner.class)
public class ActivateCarCommandTest {

    @Mock
    CarService carService;

    @InjectMocks
    ActivateCarCommand activateCarCommand;

    @Test
    public void activateCar() {
        ModelAndView modelAndView = activateCarCommand.activateCar(1);
        Assert.assertEquals(modelAndView.getViewName(), "redirect:/admin/control_car");
    }
}