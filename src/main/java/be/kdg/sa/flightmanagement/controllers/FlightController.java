package be.kdg.sa.flightmanagement.controllers;

import be.kdg.sa.flightmanagement.services.DelayService;
import be.kdg.sa.flightmanagement.services.DelayTypeService;
import be.kdg.sa.flightmanagement.services.FlightService;
import be.kdg.sa.flightmanagement.model.Delay;
import be.kdg.sa.flightmanagement.model.Flight;
import be.kdg.sa.flightmanagement.utils.Utils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author LukasVandenBossche on 29/09/2021
 * @project flightmanagement
 */
@Controller
public class FlightController {
    private static final Logger log = LoggerFactory.getLogger(FlightController.class);
    private final FlightService flightService;
    private final DelayService delayService;
    private final DelayTypeService delayTypeService;
    @Value("${fm.web.date-time-format}")
    private String dateTimeFormat;

    @Autowired
    public FlightController(FlightService flightService, DelayService delayService, DelayTypeService delayTypeService) {
        this.flightService = flightService;
        this.delayService = delayService;
        this.delayTypeService = delayTypeService;
    }

    @GetMapping("/home")
    public ModelAndView showFlightsOfToday() {
        Date date = new Date();
        var flights = flightService.getAllFlightsOnDate(date);
        var mav = new ModelAndView();
        mav.setViewName("flightsOfDate");
        mav.addObject("inputDate", new InputDate());
        mav.addObject("selectedDate", date);
        mav.addObject("retrievedFlights", flights);
        mav.addObject("format", dateTimeFormat);
        return mav;
    }

    @PostMapping(value = "/home", params = "action=table")
    public ModelAndView showFlightsOfDateAsTable(@ModelAttribute InputDate inputDate) {
        if (inputDate.getDate() == null) inputDate.setDate(new Date());
        var flights = flightService.getAllFlightsOnDate(inputDate.getDate());
        var mav = new ModelAndView();
        mav.setViewName("flightsOfDate");
        mav.addObject("selectedDate", inputDate.getDate());
        mav.addObject("retrievedFlights", flights);
        mav.addObject("format", dateTimeFormat);
        return mav;
    }

    @PostMapping(value = "/home", params = "action=ganttchart")
    public void showFlightsOfDateAsGanttChart(HttpServletResponse response, @ModelAttribute InputDate inputDate) {
        if (inputDate.getDate() == null) inputDate.setDate(new Date());
        var flights = flightService.getAllFlightsOnDate(inputDate.getDate());
        TaskSeries scheduled = new TaskSeries("scheduled");
        TaskSeries estimated = new TaskSeries("estimated");
        TaskSeries actual = new TaskSeries("actual");
        for (Flight flight : flights) {
            scheduled.add(new Task(flight.getName(), flight.getSTD(), flight.getSTA()));
            estimated.add(new Task(flight.getName(), flight.getETD(), flight.getETA()));
            actual.add(new Task(flight.getName(), flight.getATD(), flight.getATA()));
        }
        TaskSeriesCollection dataSet = new TaskSeriesCollection();
        dataSet.add(scheduled);
        dataSet.add(estimated);
        dataSet.add(actual);
        SimpleDateFormat format = new SimpleDateFormat(dateTimeFormat);
        JFreeChart ganttChart = ChartFactory.createGanttChart("Flight Management", "Flights",
                "Timeline of " + format.format(inputDate.getDate()), dataSet);
        try {
            ChartUtilities.writeChartAsPNG(response.getOutputStream(), ganttChart, 1600, 900);
            response.getOutputStream().close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/flight/{uuid}")
    public ModelAndView showFlight(@PathVariable String uuid) {
        var flight = flightService.getFlight(UUID.fromString(uuid));
        var delayTypes = delayTypeService.getAllDelayTypes();
        var mav = new ModelAndView();
        mav.setViewName("flight");
        mav.addObject("retrievedFlight", flight);
        mav.addObject("delayTypes", delayTypes);
        mav.addObject("format", dateTimeFormat);
        Delay newDelay = new Delay();
        mav.addObject("newDelay", newDelay);
        Delay updatedDelay = new Delay();
        mav.addObject("updatedDelay", updatedDelay);
        return mav;
    }

    @Transactional
    @PostMapping("/flight/{uuid}/createDelay")
    public String createDelay(@PathVariable String uuid, @ModelAttribute Delay newDelay) {
        Flight flight = flightService.getFlight(UUID.fromString(uuid));
        newDelay.setFlight(flight);
        delayService.addDelay(newDelay);

        if (newDelay.isDepartureDelay()) {
            flight.setETD(Utils.addMinutesToDate(flight.getETD(), newDelay.getMinutes()));
        }
        flight.setETA(Utils.addMinutesToDate(flight.getETA(), newDelay.getMinutes()));
        flightService.changeFlight(flight);
        return "redirect:/flight/" + uuid;
    }
}


