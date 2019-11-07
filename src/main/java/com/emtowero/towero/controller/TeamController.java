package com.emtowero.towero.controller;

import com.emtowero.towero.dto.ResultModel;
import com.emtowero.towero.service.TeamService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/teams", method = RequestMethod.GET)
public class TeamController {
    @Resource
    private TeamService teamService;

    @RequestMapping(value = "/skip/{skip}/limit/{limit}")
    public ResultModel get(@PathVariable("skip") Integer skip,
                           @PathVariable("limit") Integer limit) {

        return teamService.getTeams(skip, limit);
    }

    @RequestMapping(value = "/search/{search}")
    public ResultModel search(@PathVariable("search") String search) {
        return teamService.searchTeams(search);
    }

    @RequestMapping(value = "/update")
    public ResultModel update() {
        return teamService.insertAndUpdateAll();
    }
}
