package by.htp.jd2.command.impl.action;

import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.Order;
import by.htp.jd2.service.CarService;
import by.htp.jd2.service.OrderService;
import by.htp.jd2.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Get list orders by user id
 *
 * @author alexey
 */
@RestController
public class UserOrdersSearchCommand {

    private static final Logger LOG = LogManager.getLogger(UserOrdersSearchCommand.class.getName());
    private static final String ERROR = "SEARCH USER ORDERS ERROR";
    private static final String DEBUG = "SEARCH USER ORDERS command";

    @Autowired
    private CarService carService;

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/admin/userOrderSearch", method = RequestMethod.POST)
    public ModelAndView userOrdersSearch(@RequestParam int idUser) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Order> list = orderService.userOrders(idUser);
            modelAndView.addObject("userOrders", list);

            Set<Car> cars = new HashSet<>();
            Car car;
            for (Order order : list) {
                car = carService.getCarById(order.getIdCar());
                cars.add(car);
            }
            modelAndView.addObject("carsO", cars);


        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        LOG.debug(DEBUG);
        modelAndView.setViewName("/admin/control_users");
        return modelAndView;
    }
}
