package by.htp.jd2.command.impl.link;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import by.htp.jd2.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.Crash;
import by.htp.jd2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * go to page with all crashs(additional bills) of all users
 *
 * @author alexey
 */
@RestController
public class CrashPageAdminCommand {
    private static final Logger LOG = LogManager.getLogger(CrashPageAdminCommand.class.getName());
    private static final String ERROR = "go to crash page admin page error";
    private static final String DEBUG = "Go to crash page admin command";

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private CrashService crashService;



    @RequestMapping(value = "/admin/crash_page")
    public ModelAndView crashPage() {
        Set<Car> cars = new HashSet<>();
        List<Crash> crashs = null;
        Set<User> users = new HashSet<>();
        Car car;
        User user;
        try {
            crashs = crashService.getAllCrashs();
            for (Crash crash : crashs) {
                car = carService.getCarById(crash.getIdCar());
                cars.add(car);
            }

            for (Crash crash : crashs) {
                user = userService.getUserById(crash.getIdUser());
                users.add(user);
            }

        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/crash_page");
        modelAndView.addObject("allCrashs", crashs);
        modelAndView.addObject("carsO", cars);
        modelAndView.addObject("usersO", users);
        LOG.debug(DEBUG);
        return modelAndView;
    }
}
