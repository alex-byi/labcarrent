package by.htp.jd2.command.impl.link;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import by.htp.jd2.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.entity.Car;
import by.htp.jd2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * go to page where user can choose car
 *
 * @author alexey
 */
@RestController

public class OrderPageStep2UserCommand {
    private static final Logger LOG = LogManager.getLogger(OrderPageStep2UserCommand.class.getName());
    private static final String DEBUG = "Order step 2 command";
    private static final String ERROR = "Order step 2 command ERROR";

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/user/order_page_step2")
    public ModelAndView orderPageStep2(@RequestParam String dates) {

        String dateStart = dates.substring(0, 10);
        String dateEnd = dates.substring(13, 23);
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<Car> availableCars = null;
        int days = 0;
        String startDate = null;
        String endDate = null;

        try {
            Date dateS = dateFormat1.parse(dateStart);
            Date dateE = dateFormat1.parse(dateEnd);

            startDate = dateFormat.format(dateS);
            endDate = dateFormat.format(dateE);
            availableCars = carService.getAllAvailableCars(startDate,
                    endDate);

            long milliseconds = dateE.getTime() - dateS.getTime();
            days = (int) (milliseconds / (24 * 60 * 60 * 1000)) + 1;

        } catch (ServiceException | ParseException e) {
            LOG.error(ERROR, e);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/order_page");
        modelAndView.addObject("availableCars", availableCars);
        modelAndView.addObject("dayCol", days);
        modelAndView.addObject("startDate1", startDate);
        modelAndView.addObject("endDate1", endDate);
        LOG.debug(DEBUG);
        return modelAndView;
    }
}
