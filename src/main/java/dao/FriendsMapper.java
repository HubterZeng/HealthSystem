package dao;

import entity.Friends;
import entity.FriendsKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsMapper {
    @Delete({
        "delete from friends",
        "where userId = #{userid,jdbcType=INTEGER}",
          "and doctorId = #{doctorid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(FriendsKey key);

    @Insert({
        "insert into friends (userId, doctorId, ",
        "friendsNameUser, friendsNameDoc)",
        "values (#{userid,jdbcType=INTEGER}, #{doctorid,jdbcType=INTEGER}, ",
        "#{friendsnameuser,jdbcType=VARCHAR}, #{friendsnamedoc,jdbcType=VARCHAR})"
    })
    int insert(Friends record);

    int insertSelective(Friends record);

    @Select({
        "select",
        "userId, doctorId, friendsNameUser, friendsNameDoc",
        "from friends",
        "where doctorId = #{doctorId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    List<Friends> selectByDocId(Integer doctorId);


    @Select({
            "select",
            "userId, doctorId, friendsNameUser, friendsNameDoc",
            "from friends",
            "where userId = #{userId,jdbcType=INTEGER}",

    })
    @ResultMap("BaseResultMap")
    List<Friends> selectByUserId(Integer userId);



    int updateByPrimaryKeySelective(Friends record);

    @Update({
        "update friends",
        "set friendsNameUser = #{friendsnameuser,jdbcType=VARCHAR},",
          "friendsNameDoc = #{friendsnamedoc,jdbcType=VARCHAR}",
        "where userId = #{userid,jdbcType=INTEGER}",
          "and doctorId = #{doctorid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Friends record);
}