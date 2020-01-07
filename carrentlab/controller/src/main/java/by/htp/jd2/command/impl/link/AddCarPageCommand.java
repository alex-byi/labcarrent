package by.htp.jd2.command.impl.link;

import by.htp.jd2.entity.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * go to "Add car" page
 *
 * @author alexey
 */

@RestController
public class AddCarPageCommand{
    private static final Logger LOG = LogManager.getLogger(AddCarPageCommand.class.getName());
    private static final String DEBUG = "Go to add car page";

    @RequestMapping(value = "/admin/add_car")

    public ModelAndView addCarPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("car", new Car());
        modelAndView.setViewName("admin/add_car");
        LOG.debug(DEBUG);
        return modelAndView;
    }
}
