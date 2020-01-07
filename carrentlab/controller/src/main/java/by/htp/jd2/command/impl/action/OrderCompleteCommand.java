package by.htp.jd2.command.impl.action;

import by.htp.jd2.service.OrderService;
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
 * complete order
 *
 * @author alexey
 */
@RestController
public class OrderCompleteCommand {
    private static final Logger LOG = LogManager.getLogger(OrderCompleteCommand.class.getName());
    private static final String DEBUG = "Order complete command";
    private static final String ERROR = "Complete order ERROR";

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/admin/orderComplete", method = RequestMethod.POST)
    public ModelAndView orderComplete(@RequestParam int orderId) {
        try {
            orderService.setComplete(orderId);
        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        LOG.debug(DEBUG);
        return new ModelAndView("redirect:/admin/order_page");
    }
}
