package com.emtowero.towero.mapper;

import com.emtowero.towero.model.TeamModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeamMapper {
    @Insert("replace into teams (`key`, team_number, name, nickname, city, state_prov, country, gmt_create, gmt_mod) values (#{key}, #{team_number}, #{name}, #{nickname}, #{city}, #{state_prov}, #{country}, #{gmt_create}, #{gmt_mod});")
    void insertOne(TeamModel teamModel);

    @Select("select * from teams limit #{skip},#{limit};")
    List<TeamModel> selectAll(@Param("skip") Integer skip, @Param("limit") Integer limit);

    @Select("select * from teams where team_number REGEXP #{search} or nickname REGEXP #{search} or country REGEXP #{search};")
    List<TeamModel> search(@Param("search") String search);

    @Select("select * from teams limit 1;")
    TeamModel selectOne();
}
