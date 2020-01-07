package by.htp.jd2.command.impl.action;

import by.htp.jd2.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.entity.Crash;
import by.htp.jd2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * add additional bill to order
 *
 * @author alexey
 */
@RestController
public class AddCrashCommand {
    private static final Logger LOG = LogManager.getLogger(AddCrashCommand.class.getName());
    private static final String ERROR = "ADD crash bill ERROR";
    private static final String DEBUG = "Add crash command";

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/admin/addCrashCommand", method = RequestMethod.POST)
    public ModelAndView addCrashCommand(@RequestParam String description, int amount, int userId, int orderId,
                                     int carId) {
        Crash crash = new Crash();
        try {
           crash.setDamage(description);
           crash.setAmount(amount);
           crash.setIdCar(carId);
           crash.setIdUser(userId);
           crash.setComplete(false);
            orderService.setCrash(orderId, crash);
        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        LOG.debug(DEBUG);
        return new ModelAndView("redirect:/admin/crash_page");
    }
}
