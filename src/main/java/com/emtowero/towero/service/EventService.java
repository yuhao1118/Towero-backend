package com.emtowero.towero.service;

import com.alibaba.fastjson.JSONObject;
import com.emtowero.towero.dto.ResultModel;
import com.emtowero.towero.mapper.EventMapper;
import com.emtowero.towero.model.EventModel;
import com.emtowero.towero.provider.HttpProvider;
import okhttp3.Headers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EventService {
    @Resource
    private EventMapper eventMapper;

    @Resource
    private HttpProvider httpProvider;

    @Scheduled(cron = "0 0 * * * *")
    public ResultModel insertAllandUpdate() {
        String url = "events/all/simple";

        try {
            String response = httpProvider.getTbaRequest(url);

            List<EventModel> eventModelList = JSONObject.parseArray(response, EventModel.class);

            for (EventModel eventModel : eventModelList) {
                Integer month = 0;

                if (eventModel.getStart_date() != null) {
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(eventModel.getStart_date());
                    String monthStr = new SimpleDateFormat("MM").format(date);
                    month = Integer.valueOf(monthStr);
                }

                eventModel.setMonth(month);
                eventModel.setGmt_create(System.currentTimeMillis());
                eventModel.setGmt_mod(eventModel.getGmt_create());
                eventMapper.insertOne(eventModel);
            }

            return new ResultModel(HttpStatus.OK, new Date().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResultModel(HttpStatus.INTERNAL_SERVER_ERROR, null);
    }


    public ResultModel getEvents(Integer year, Integer month) {
        List<EventModel> eventModels = eventMapper.selectByDate(year, month);

        if (eventModels != null) {
            return new ResultModel(HttpStatus.OK, eventModels);
        }

        return new ResultModel(HttpStatus.NO_CONTENT, null);
    }

    public ResultModel searchEvents(String search, Integer year) {
        List<EventModel> eventModels = eventMapper.search(search, year);

        if (eventModels != null) {
            return new ResultModel(HttpStatus.OK, eventModels);
        }

        return new ResultModel(HttpStatus.NO_CONTENT, null);
    }
}
