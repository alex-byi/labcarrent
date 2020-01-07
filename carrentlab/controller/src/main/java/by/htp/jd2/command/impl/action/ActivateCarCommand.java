package by.htp.jd2.command.impl.action;


import by.htp.jd2.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * activate one car by carID
 *
 * @author alexey
 */
@RestController
public class ActivateCarCommand {
    private static final Logger LOG = LogManager.getLogger(ActivateCarCommand.class.getName());
    private static final String ERROR = "Car activate ERROR";
    private static final String DEBUG = "Activate car command";

    @Autowired
    CarService carService;

    @RequestMapping(value = "/admin/activateCar", method = RequestMethod.POST)
    public ModelAndView activateCar(@RequestParam int id) {
        try {
            carService.activateCar(id);
        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        LOG.debug(DEBUG);
        return new ModelAndView("redirect:/admin/control_car");
    }
}
