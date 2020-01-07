package by.htp.jd2.command.impl.link;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import by.htp.jd2.service.CarService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * go to page where user can confirm order
 *
 * @author alexey
 */
@RestController
public class OrderPageStep3UserCommand {
    private static final Logger LOG = LogManager.getLogger(OrderPageStep3UserCommand.class.getName());
    private static final String DEBUG = "Order step 3 command";
    private static final String ERROR = "Order step 3 command ERROR";

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/final_order_page")
    public ModelAndView finalOrderPageStep2(@RequestParam int idCar, String startDate, String endDate, int amount,
                                            int dayCol, HttpServletRequest request) {
        HttpSession session = request.getSession();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Car car = null;
        Order order = new Order();

        try {
            User user = userService.getUserByLogin(authentication.getName());
            int idUser = user.getId();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            car = carService.getCarById(idCar);

            order.setDateOrder(date);
            order.setStartDate(dateFormat.parse(startDate));
            order.setEndDate(dateFormat.parse(endDate));
            order.setIdCar(idCar);
            order.setIdUser(idUser);
            order.setAmount(amount);
            order.setDayCol(dayCol);

        } catch (ServiceException | ParseException e) {
            LOG.error(ERROR, e);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/final_order_page");
        modelAndView.addObject("selectCar", car);
        modelAndView.addObject("currentOrder", order);
        session.setAttribute("currentOrder", order);
        LOG.debug(DEBUG);
        return modelAndView;
    }
}
