package by.htp.jd2.command.impl.link;

import java.util.List;

import by.htp.jd2.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.entity.Car;
import by.htp.jd2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * go to "Control car" page
 *
 * @author alexey
 */
@RestController
public class ControlCarPageCommand {
    private static final Logger LOG = LogManager.getLogger(ControlCarPageCommand.class.getName());
    private static final String ERROR = "go to Control car page error";
    private static final String DEBUG = "Go to Car control page command";

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/admin/control_car")
    public ModelAndView controlCar() throws Exception {
        List<Car> cars = null;
        try {
            cars = carService.getAllCars();
        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/control_car");
        modelAndView.addObject("cars", cars);
        LOG.debug(DEBUG);
        return modelAndView;
    }
}