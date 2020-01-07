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
public class AddCarCommandTest {

    @Mock
    CarService carService;

    @InjectMocks
    AddCarCommand addCarCommand;


    @Test
    public void addCarCommand() {
        ModelAndView modelAndView = addCarCommand.addCarCommand("Test",
                100, "test", "test", "test", "MANUAL");
        Assert.assertEquals(modelAndView.getViewName(), "redirect:/admin/control_car");
    }
}