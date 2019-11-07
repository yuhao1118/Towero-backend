package com.emtowero.towero.service;

import com.emtowero.towero.dto.IndexModel;
import com.emtowero.towero.dto.ResultModel;
import com.emtowero.towero.mapper.EventMapper;
import com.emtowero.towero.mapper.TeamMapper;
import com.emtowero.towero.model.EventModel;
import com.emtowero.towero.model.TeamModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GlobalService {
    @Resource
    private EventMapper eventMapper;

    @Resource
    private TeamMapper teamMapper;

    public ResultModel getStatus() {
        EventModel eventModel = eventMapper.selectOne();
        TeamModel teamModel = teamMapper.selectOne();
        IndexModel indexModel = new IndexModel();

        indexModel.setServer_time(System.currentTimeMillis());
        indexModel.setTeam_db_update(teamModel.getGmt_mod());
        indexModel.setEvent_db_update(eventModel.getGmt_mod());

        return new ResultModel(HttpStatus.OK, indexModel);
    }

    public ResultModel getError(HttpStatus status) {
        return new ResultModel(status, null);
    }
}
