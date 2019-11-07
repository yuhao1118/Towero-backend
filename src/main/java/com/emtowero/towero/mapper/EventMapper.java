package com.emtowero.towero.mapper;

import com.emtowero.towero.model.EventModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EventMapper {
    @Insert("replace into events (`key`, `name`, event_code, event_type, city, state_prov, country, start_date, end_date, year, month, gmt_create, gmt_mod) values (#{key}, #{name}, #{event_code}, #{event_type}, #{city}, #{state_prov}, #{country}, #{start_date}, #{end_date}, #{year}, #{month}, #{gmt_create}, #{gmt_mod});")
    void insertOne(EventModel eventModel);

    @Select("select * from events where year = #{year} and month = #{month} order by start_date")
    List<EventModel> selectByDate(@Param("year") Integer year, @Param("month") Integer month);

    @Select("select * from events where name REGEXP #{search} and year = #{year} order by start_date")
    List<EventModel> search(@Param("search") String search, @Param("year") Integer year);

    @Select("select * from events limit 1")
    EventModel selectOne();

}
