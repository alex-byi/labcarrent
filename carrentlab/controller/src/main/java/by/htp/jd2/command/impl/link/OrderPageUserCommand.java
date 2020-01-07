package by.htp.jd2.command.impl.link;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * go to page whera user can start new order(choose date)
 *
 * @author alexey
 */
@RestController
public class OrderPageUserCommand {
    private static final Logger LOG = LogManager.getLogger(OrderPageUserCommand.class.getName());
    private static final String DEBUG = "Go to order page USER command";

    @RequestMapping(value = "/user/order_page")
    public ModelAndView userOrderPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/order_page");
        LOG.debug(DEBUG);
        return modelAndView;
    }
}
