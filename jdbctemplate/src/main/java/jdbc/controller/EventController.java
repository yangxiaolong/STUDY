package jdbc.controller;

import jdbc.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dragon
 */
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/publishEvent")
    public String publishEvent() {
        return eventService.publishEvent();
    }
}