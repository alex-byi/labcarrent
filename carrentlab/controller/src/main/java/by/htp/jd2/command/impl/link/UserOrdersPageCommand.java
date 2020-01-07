package by.htp.jd2.command.impl.link;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.htp.jd2.service.CarService;
import by.htp.jd2.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.Order;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * go to page where user can see all his orders
 *
 * @author alexey
 */
@RestController
public class UserOrdersPageCommand {
    private static final Logger LOG = LogManager.getLogger(UserOrdersPageCommand.class.getName());
    private static final String DEBUG = "User orders page command";
    private static final String ERROR = "User orders page command ERROR";

    @Autowired
    private OrderService orderService;

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/orders")
    public ModelAndView userOrdersPage(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User user = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView();
        Set<Car> cars = new HashSet<>();
        List<Order> orders;

        try {
            user = userService.getUserByLogin(authentication.getName());

            orders = orderService.userOrders(user.getId());
            modelAndView.addObject("userOrders", orders);

            Car car;
            for (Order order : orders) {
                car = carService.getCarById(order.getIdCar());
                cars.add(car);
            }
            modelAndView.addObject("carsO", cars);

        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        session.setAttribute("user", user);
        modelAndView.setViewName("/user/orders");
        LOG.debug(DEBUG);
        return modelAndView;
    }
}
