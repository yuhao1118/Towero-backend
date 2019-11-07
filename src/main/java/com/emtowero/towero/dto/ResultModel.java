package com.emtowero.towero.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResultModel {
    private int code;
    private String msg;
    private Object data;

    public ResultModel(HttpStatus status, Object data) {
        this.code = status.value();
        this.msg = status.getReasonPhrase();
        this.data = data;
    }
}
