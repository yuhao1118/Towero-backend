package com.emtowero.towero.model;

import lombok.Data;

@Data
public class EventModel {
    private String key;
    private String name;
    private String event_code;
    private String event_type;
    private String city;
    private String state_prov;
    private String country;
    private String start_date;
    private String end_date;
    private Integer year;
    private Integer month;
    private Long gmt_create;
    private Long gmt_mod;
}
