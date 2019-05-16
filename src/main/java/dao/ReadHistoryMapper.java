package dao;

import entity.ReadHistory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadHistoryMapper {
    @Delete({
        "delete from readhistory",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into readhistory (id, cons_id, ",
        "docId)",
        "values (#{id,jdbcType=INTEGER}, #{consId,jdbcType=INTEGER}, ",
        "#{docid,jdbcType=INTEGER})"
    })
    int insert(ReadHistory record);

    int insertSelective(ReadHistory record);

    @Select({
        "select",
        "id, cons_id, docId",
        "from readhistory",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    ReadHistory selectByPrimaryKey(Integer id);
    @Select({
            "select",
            "id, cons_id, docId",
            "from readhistory",
            "where docId = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    List<ReadHistory> selectByDocId(Integer id);
    int updateByPrimaryKeySelective(ReadHistory record);

    @Update({
        "update readhistory",
        "set cons_id = #{consId,jdbcType=INTEGER},",
          "docId = #{docid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ReadHistory record);
}