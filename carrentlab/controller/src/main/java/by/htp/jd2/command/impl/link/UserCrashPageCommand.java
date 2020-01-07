package by.htp.jd2.command.impl.link;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.htp.jd2.service.CarService;
import by.htp.jd2.service.CrashService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.entity.Car;
import by.htp.jd2.entity.Crash;
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
 * go to page where user can see all his additional bills
 *
 * @author alexey
 */
@RestController
public class UserCrashPageCommand {
    private static final Logger LOG = LogManager.getLogger(UserCrashPageCommand.class.getName());
    private static final String DEBUG = "Go to USER crash page command";
    private static final String ERROR = "Go to USER crash page command ERROR";


    @Autowired
    private CarService carService;

    @Autowired
    private CrashService crashService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/crash_page")
    public ModelAndView userCrashPage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Crash> crashs;
        ModelAndView modelAndView = new ModelAndView();
        Set<Car> cars = new HashSet<>();
        

        try {
            User user = userService.getUserByLogin(authentication.getName());
            session.setAttribute("user", user);
            crashs = crashService.getUsersCrashs(user.getId());

            for (Crash crash : crashs) {
                cars.add(carService.getCarById(crash.getIdCar()));
            }
            modelAndView.addObject("cars", cars);

            if (!crashs.isEmpty()) {
                modelAndView.addObject("allCrashs", crashs);
            } else {
                modelAndView.addObject("allCrashs", null);
            }

        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        modelAndView.setViewName("/user/crash_page");
        LOG.debug(DEBUG);
        return modelAndView;
    }
}
