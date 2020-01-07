package by.htp.jd2.command.impl.link;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * add new additional bill(crash bill) to order
 *
 * @author alexey
 */
@RestController
public class NewCrashBillPageCommand {
    private static final Logger LOG = LogManager.getLogger(NewCrashBillPageCommand.class.getName());
    private static final String DEBUG = "Go to new crash bill page";
    private static final String ERROR = "Go to new crash bill page ERROR";


    @RequestMapping(value = "/admin/add_crash")
    public ModelAndView addCrashPage(@RequestParam int orderId,  int carId, int userId  ) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/add_crash");
        modelAndView.addObject("orderId", orderId);
        modelAndView.addObject("carId", carId);
        modelAndView.addObject("userId", userId);
        LOG.debug(DEBUG);
        return modelAndView;
    }

}
