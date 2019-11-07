package com.emtowero.towero.controller;

import com.emtowero.towero.dto.ResultModel;
import com.emtowero.towero.service.GlobalService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = {"/", "/index", "/status"}, method = RequestMethod.GET)
public class IndexController {
    @Resource
    private GlobalService globalService;

    @RequestMapping
    public ResultModel index() {
        return globalService.getStatus();
    }
}
