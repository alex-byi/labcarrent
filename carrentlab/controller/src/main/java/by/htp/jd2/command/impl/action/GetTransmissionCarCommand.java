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
 * get list of cars with some transmission type
 *
 * @author alexey
 */
@RestController
public class GetTransmissionCarCommand {
    private static final Logger LOG = LogManager.getLogger(GetTransmissionCarCommand.class.getName());
    private static final String DEBUG = "Get transmission cars command";
    private static final String ERROR = "Get transmission cars USER ERROR";

    @Autowired
    private CarService carService;


    @RequestMapping(value = "/admin/getTransmissionCars", method = RequestMethod.POST)
    public ModelAndView getTransmissionCars(@RequestParam String searchtransmission) {
        List<Car> list = null;
        ModelAndView modelAndView = new ModelAndView();
        try {
            list = carService.getTransmissionCar(searchtransmission);
        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        modelAndView.addObject("transmissionCar", list);
        modelAndView.addObject("transmissionStr", searchtransmission);
        modelAndView.setViewName("/admin/control_car");
        LOG.debug(DEBUG);
        return modelAndView;
    }
}
