package by.htp.jd2.command.impl.link;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ErrorPageAccessDeny {

    @RequestMapping(value = {"/common/access_deny"}, method = RequestMethod.GET)
    public ModelAndView errorPageAccessDeny() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/common/error_page_access_deny");
        return modelAndView;
    }

}
