package by.htp.jd2.command.impl.action;


import by.htp.jd2.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.entity.Car;
import by.htp.jd2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * add new car to database
 *
 * @author alexey
 */
@RestController
public class AddCarCommand {
    private static final Logger LOG = LogManager.getLogger(AddCarCommand.class.getName());
    private static final String ERROR = "Add car ERROR";
    private static final String DEBUG = "Add car command";

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/admin/addCarCommand", method = RequestMethod.POST)
    public ModelAndView addCarCommand(@ModelAttribute("car") Car car) {
        try {
            car.setActive(true);
            carService.addNewCar(car);
        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        LOG.debug(DEBUG);
        return new ModelAndView("redirect:/admin/control_car");
    }
}
