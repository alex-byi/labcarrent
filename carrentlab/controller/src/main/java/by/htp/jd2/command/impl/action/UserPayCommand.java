package by.htp.jd2.command.impl.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.htp.jd2.service.OrderService;
import by.htp.jd2.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.entity.Order;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * user pay order
 *
 * @author alexey
 */
@RestController
public class UserPayCommand {
    private static final Logger LOG = LogManager.getLogger(UserPayCommand.class.getName());
    private static final String DEBUG = "User pay command";
    private static final String ERROR = "Pay ERROR";

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/user/userOrderPay", method = RequestMethod.POST)
    public ModelAndView userOrderPay(@RequestParam int userId, int orderId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        Order order;
        try {
            int orderAmount = orderService.setPayment(orderId, userId);
            user.setCash(user.getCash() - orderAmount);
            session.setAttribute("user", user);
        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        LOG.debug(DEBUG);
        return new ModelAndView("redirect:/user/orders");
    }
}
