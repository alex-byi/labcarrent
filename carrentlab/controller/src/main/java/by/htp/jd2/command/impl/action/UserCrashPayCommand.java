package by.htp.jd2.command.impl.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.htp.jd2.service.CrashService;
import by.htp.jd2.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.entity.Crash;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * pay additional bill by user
 *
 * @author alexey
 */
@RestController
public class UserCrashPayCommand {
    private static final Logger LOG = LogManager.getLogger(UserCrashPayCommand.class.getName());
    private static final String DEBUG = "User crash pay command";
    private static final String ERROR = "Pay ERROR";

    @Autowired
    private CrashService crashService;

    @RequestMapping(value = "/user/crashPay", method = RequestMethod.POST)
    public ModelAndView activateUser(@RequestParam int crashId, int amount, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        try {
            int crashAmount = crashService.setCrashPayment(crashId);
            user.setCash(user.getCash() - crashAmount);
            session.setAttribute("user", user);
        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        LOG.debug(DEBUG);
        return new ModelAndView("redirect:/user/crash_page");
    }

}
