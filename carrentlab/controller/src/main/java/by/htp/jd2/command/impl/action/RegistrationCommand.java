package by.htp.jd2.command.impl.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.htp.jd2.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RegistrationCommand {
    private static final Logger LOG = LogManager.getLogger(RegistrationCommand.class.getName());
    private static final String DEBUG = "Registration command";
    private static final String ERROR = "Registration ERROR";
    private static final String DUPLICATE = "Пользователь с таким логином уже существует. Выберите другой";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "common/registration", method = RequestMethod.POST)
        public ModelAndView registration(@ModelAttribute("user") User user, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        try {
            if (!userService.checkUser(user.getLogin())) {
                session.setAttribute("duplicateLogin", DUPLICATE);
                return new ModelAndView("redirect:/common/registration");
            } else {
                userService.registration(user);
                session.setAttribute("user", user);
                LOG.debug(DEBUG);
                return new ModelAndView("redirect:/");
            }
        } catch (ServiceException e) {
            LOG.error(ERROR, e);
            return null;
        }
    }
}
