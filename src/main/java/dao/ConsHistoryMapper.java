package dao;

import entity.ConsHistory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ConsHistoryMapper {
    @Delete({
        "delete from cons_history",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cons_history (id, userId, ",
        "qusTitle, qusContent)",
        "values (#{id,jdbcType=INTEGER}, #{userid,jdbcType=INTEGER}, ",
        "#{qustitle,jdbcType=CHAR}, #{quscontent,jdbcType=LONGVARCHAR})"
    })
    int insert(ConsHistory record);

    int insertSelective(ConsHistory record);

    @Select({
        "select",
        "id, userId, qusTitle, qusContent",
        "from cons_history",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    ConsHistory selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, userId, qusTitle, qusContent",
            "from cons_history",
            "where userId = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<ConsHistory> selectByUserId(Integer id);

    @Select({
            "select",
            "id, userId, qusTitle, qusContent",
            "from cons_history"

    })
    @ResultMap("ResultMapWithBLOBs")
    List<ConsHistory> selectAll();



    int updateByPrimaryKeySelective(ConsHistory record);

    @Update({
        "update cons_history",
        "set userId = #{userid,jdbcType=INTEGER},",
          "qusTitle = #{qustitle,jdbcType=CHAR},",
          "qusContent = #{quscontent,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(ConsHistory record);

    @Update({
        "update cons_history",
        "set userId = #{userid,jdbcType=INTEGER},",
          "qusTitle = #{qustitle,jdbcType=CHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ConsHistory record);
}