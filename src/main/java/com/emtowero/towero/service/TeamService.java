package com.emtowero.towero.service;


import com.alibaba.fastjson.JSONObject;
import com.emtowero.towero.dto.ResultModel;
import com.emtowero.towero.mapper.TeamMapper;
import com.emtowero.towero.model.TeamModel;
import com.emtowero.towero.provider.HttpProvider;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class TeamService {
    @Resource
    private TeamMapper teamMapper;

    @Resource
    private HttpProvider httpProvider;

    @Scheduled(cron = "0 0 * * * *")
    public ResultModel insertAndUpdateAll() {

        String url = "teams/all/simple";

        try {
            String response = httpProvider.getTbaRequest(url);

            List<TeamModel> teamModelList = JSONObject.parseArray(response, TeamModel.class);

            for (TeamModel teamModel : teamModelList) {
                teamModel.setGmt_create(System.currentTimeMillis());
                teamModel.setGmt_mod(teamModel.getGmt_create());
                teamMapper.insertOne(teamModel);
            }

            return new ResultModel(HttpStatus.OK, new Date().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResultModel(HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    public ResultModel getTeams(Integer skip, Integer limit) {
        List<TeamModel> teamModels = teamMapper.selectAll(skip, limit);

        if (teamModels != null) {
            return new ResultModel(HttpStatus.OK, teamModels);
        }

        return new ResultModel(HttpStatus.NO_CONTENT, null);
    }

    public ResultModel searchTeams(String search) {
        List<TeamModel> teamModels = teamMapper.search(search);

        if (teamModels != null) {
            return new ResultModel(HttpStatus.OK, teamModels);
        }

        return new ResultModel(HttpStatus.NO_CONTENT, null);
    }
}
