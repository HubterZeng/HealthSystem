package dao;

import entity.Doctor;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


import entity.Doctor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Repository
public interface DoctorMapper {
    @Delete({
        "delete from doctor",
        "where docId = #{docid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer docid);

    @Insert({
        "insert into doctor (docId, userName, ",
        "sex, age, workYears, ",
        "workPlace, address, ",
        "accountUser, passwordUser, ",
        "questionForReset)",
        "values (#{docid,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER}, #{workyears,jdbcType=INTEGER}, ",
        "#{workplace,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{accountuser,jdbcType=CHAR}, #{passworduser,jdbcType=VARCHAR}, ",
        "#{questionforreset,jdbcType=VARCHAR})"
    })
    int insert(Doctor record);

    int insertSelective(Doctor record);

    @Select({
        "select",
        "docId, userName, sex, age, workYears, workPlace, address, accountUser, passwordUser, ",
        "questionForReset",
        "from doctor",
        "where docId = #{docid,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Doctor selectByPrimaryKey(Integer docid);

    @Select({
            "select",
            "docId, userName, sex, age, workYears, workPlace, address, accountUser, passwordUser, ",
            "questionForReset",
            "from doctor",
            "where accountUser = #{userAccount,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    Doctor selectByUserAccount(String userAccount);

    int updateByPrimaryKeySelective(Doctor record);

    @Update({
        "update doctor",
        "set userName = #{username,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=VARCHAR},",
          "age = #{age,jdbcType=INTEGER},",
          "workYears = #{workyears,jdbcType=INTEGER},",
          "workPlace = #{workplace,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "accountUser = #{accountuser,jdbcType=CHAR},",
          "passwordUser = #{passworduser,jdbcType=VARCHAR},",
          "questionForReset = #{questionforreset,jdbcType=VARCHAR}",
        "where docId = #{docid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Doctor record);
}