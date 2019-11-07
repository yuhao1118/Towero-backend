package com.emtowero.towero.model;

import lombok.Data;

@Data
public class TeamModel {
    private String key;
    private Integer team_number;
    private String name;
    private String nickname;
    private String city;
    private String state_prov;
    private String country;
    private Long gmt_create;
    private Long gmt_mod;
}
