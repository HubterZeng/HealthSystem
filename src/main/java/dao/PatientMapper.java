package dao;

import entity.Patient;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientMapper {
    @Delete({
        "delete from patient",
        "where userId = #{userid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userid);

    @Insert({
        "insert into patient (userId, userName, ",
        "sex, accountUser, ",
        "passwordUser, address, ",
        "user_phone, age, ",
        "questionForReset)",
        "values (#{userid,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=VARCHAR}, #{accountuser,jdbcType=VARCHAR}, ",
        "#{passworduser,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{userPhone,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER}, ",
        "#{questionforreset,jdbcType=VARCHAR})"
    })
    int insert(Patient record);

    int insertSelective(Patient record);

    @Select({
        "select",
        "userId, userName, sex, accountUser, passwordUser, address, user_phone, age, ",
        "questionForReset",
        "from patient",
        "where accountUser = #{accountUser,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    Patient selectByAccount(String accountUser);

    @Select({
            "select",
            "userId, userName, sex, accountUser, passwordUser, address, user_phone, age, ",
            "questionForReset",
            "from patient",
            "where userId = #{userId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")

    Patient selectByPrimaryKey(Integer userId);
    int updateByPrimaryKeySelective(Patient record);

    @Update({
        "update patient",
        "set userName = #{username,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=VARCHAR},",
          "accountUser = #{accountuser,jdbcType=VARCHAR},",
          "passwordUser = #{passworduser,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "user_phone = #{userPhone,jdbcType=VARCHAR},",
          "age = #{age,jdbcType=INTEGER},",
          "questionForReset = #{questionforreset,jdbcType=VARCHAR}",
        "where userId = #{userid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Patient record);
}