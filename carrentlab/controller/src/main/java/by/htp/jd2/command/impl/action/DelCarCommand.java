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
 * deactivate car to exclude from list of availible cars
 *
 * @author alexey
 */
@RestController
public class DelCarCommand {
    private static final Logger LOG = LogManager.getLogger(DelCarCommand.class.getName());
    private static final String ERROR = "Del car ERROR";
    private static final String DEBUG = "Del car command";

    @Autowired
    private CarService carService;


    @RequestMapping(value = "/admin/deactivateCar", method = RequestMethod.POST)
    public ModelAndView deactivateCar(@RequestParam int id) {
        try {
            carService.delCar(id);
        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        LOG.debug(DEBUG);
        return new ModelAndView("redirect:/admin/control_car");
    }

}
