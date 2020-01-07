package by.htp.jd2.command.impl.action;

import by.htp.jd2.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * cancel order
 *
 * @author alexey
 */
@RestController
public class OrderCancelCommandAdmin {
    private static final Logger LOG = LogManager.getLogger(OrderCancelCommandAdmin.class.getName());
    private static final String DEBUG = "Order cancel command";
    private static final String ERROR = "Cancel order ERROR";

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/admin/cancelOrderAdmin", method = RequestMethod.POST)
    public ModelAndView orderCancelCommand(@RequestParam int orderId, String reason) {
        try {
            orderService.setCanceled(orderId, reason);
        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        LOG.debug(DEBUG);
        return new ModelAndView("redirect:/admin/order_page");
    }
}
