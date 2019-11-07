package com.emtowero.towero.controller;

import com.emtowero.towero.dto.ResultModel;
import com.emtowero.towero.service.GlobalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/error", method = RequestMethod.GET)
public class ErrorController {

    @Resource
    private GlobalService globalService;

    @RequestMapping(value = "/404")
    public ResultModel error404() {
        return globalService.getError(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/500")
    public ResultModel error500() {
        return globalService.getError(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
