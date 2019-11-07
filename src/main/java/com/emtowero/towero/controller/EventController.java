package com.emtowero.towero.controller;

import com.emtowero.towero.dto.ResultModel;
import com.emtowero.towero.service.EventService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/events", method = RequestMethod.GET)
public class EventController {

    @Resource
    private EventService eventService;

    @RequestMapping(value = "/year/{year}/month/{month}")
    public ResultModel get(@PathVariable("year") Integer year,
                           @PathVariable("month") Integer month) {

        return eventService.getEvents(year, month);
    }

    @RequestMapping(value = "/update")
    public ResultModel update() {
        return eventService.insertAllandUpdate();
    }

    @RequestMapping(value = "/search/{search}/year/{year}")
    public ResultModel search(@PathVariable("search") String search,
                              @PathVariable("year") Integer year) {
        return eventService.searchEvents(search, year);
    }
}
