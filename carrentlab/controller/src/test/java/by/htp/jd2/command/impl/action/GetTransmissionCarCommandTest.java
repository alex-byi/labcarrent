package by.htp.jd2.command.impl.action;

import by.htp.jd2.entity.Car;
import by.htp.jd2.service.CarService;
import by.htp.jd2.service.ServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetTransmissionCarCommandTest {
    @Mock
    CarService carService;

    @InjectMocks
    GetTransmissionCarCommand getTransmissionCarCommand;

    @Test
    public void getTransmissionCars() throws ServiceException {
        List<Car> list = carService.getTransmissionCar("MANUAL");
        ModelAndView modelAndView = getTransmissionCarCommand.getTransmissionCars("MANUAL");
        Assert.assertEquals(modelAndView.getViewName(), "/admin/control_car");
        assertTrue(modelAndView.getModel().containsKey("transmissionCar"));
        assertTrue(modelAndView.getModel().containsValue(list));
    }
}