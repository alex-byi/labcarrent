package by.htp.jd2.command.impl.link;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ErrorPage {

    @RequestMapping(value = {"/common/error_page"}, method = RequestMethod.GET)
    public ModelAndView errorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/common/error_page");
        return modelAndView;
    }

}


