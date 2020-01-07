package by.htp.jd2.command.impl.link;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import by.htp.jd2.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.Order;
import by.htp.jd2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * go to page with all orders of all users
 *
 * @author alexey
 */
@RestController
@RequestMapping(value = "/admin/order_page")
public class OrderPageCommand {

    private static final Logger LOG = LogManager.getLogger(OrderPageCommand.class.getName());
    private static final String DEBUG = "Go to order page command";
    private static final String ERROR = "Go to order page command ERROR";

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView orderPage() {

        List<Order> orders = null;
        Set<Car> cars = new HashSet<>();
        Set<User> users = new HashSet<>();
        Car car;
        User user;
        try {
            orders = orderService.getAllOrders();

            for (Order order : orders) {
                car = carService.getCarById(order.getIdCar());
                cars.add(car);
            }

            for (Order order : orders) {
                user = userService.getUserById(order.getIdUser());
                users.add(user);
            }

        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/order_page");
        modelAndView.addObject("allOrders", orders);
        modelAndView.addObject("carsO", cars);
        modelAndView.addObject("usersO", users);
        LOG.debug(DEBUG);
        return modelAndView;
    }


}
