package by.htp.jd2.command.impl.action;


import by.htp.jd2.service.CarService;
import by.htp.jd2.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.entity.Order;
import by.htp.jd2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * confirmation order by user
 *
 * @author alexey
 */
@Controller
public class ConfirmOrderCommand {
    private static final Logger LOG = LogManager.getLogger(ConfirmOrderCommand.class.getName());
    private static final String ERROR = "Confirm order ERROR";
    private static final String DEBUG = "Confirm order Command";
    private static final String ERROR_CONFIRM_ORDER = "Автомобиль уже заказал кто-то другой. Попробуйте еще раз";

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/user/confirmOrder", method = RequestMethod.POST)
    public ModelAndView confirmOrder(HttpServletRequest request) {

        HttpSession session = request.getSession();

        Order order = (Order) session.getAttribute("currentOrder");

        session.removeAttribute("currentOrder");

        System.out.println("ORDER!!!" + order.toString());
        try {
            order.setCanceled(false);
            order.setComplete(false);
            order.setRejectReason("0");
                orderService.addNewOrder(order);
        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        LOG.debug(DEBUG);
        return new ModelAndView("redirect:/user/orders");
    }

}
