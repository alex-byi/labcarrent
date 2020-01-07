package by.htp.jd2.command.impl.link;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class UserAddCashCommand  {
    private static final Logger LOG = LogManager.getLogger(UserAddCashCommand.class.getName());
    private static final String ERROR = "Go to USER add cash page without session";
    private static final String DEBUG = "Go to USER add cash page";


    @RequestMapping(value = "/user/add_funds")
    public ModelAndView userAddFunds() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/add_funds");
        LOG.debug(DEBUG);
        return modelAndView;
    }

}
