package com.emtowero.towero.service;

import com.alibaba.fastjson.JSON;
import com.emtowero.towero.dto.ResultModel;
import com.emtowero.towero.provider.HttpProvider;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProxyService {
    @Resource
    private HttpProvider httpProvider;

    public ResultModel getTbaResult(String api) {
        String string = httpProvider.getTbaRequest(api);
        if (string != null) {
            return new ResultModel(HttpStatus.OK, JSON.parse(string));
        }

        return new ResultModel(HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
}
