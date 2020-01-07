package by.htp.jd2.command.impl.action;

import java.util.List;

import by.htp.jd2.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.entity.Car;
import by.htp.jd2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * get list of cars with some fuel type
 *
 * @author alexey
 */
@RestController
public class GetFuelCarsCommand {
    private static final Logger LOG = LogManager.getLogger(GetFuelCarsCommand.class.getName());
    private static final String ERROR = "Get fuel cars USER ERROR";
    private static final String DEBUG = "Get fuel cars command";

    @Autowired
    private CarService carService;


    @RequestMapping(value = "/admin/getFuelCars", method = RequestMethod.POST)
    public ModelAndView getFuelCars(@RequestParam String searchFuel) {
        List<Car> list = null;
        ModelAndView modelAndView = new ModelAndView();
        try {
            list = carService.getFuelCars(searchFuel);
        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        modelAndView.addObject("fuelCar", list);
        modelAndView.addObject("fuelStr", searchFuel);
        modelAndView.setViewName("/admin/control_car");
        LOG.debug(DEBUG);
        return modelAndView;
    }
}
