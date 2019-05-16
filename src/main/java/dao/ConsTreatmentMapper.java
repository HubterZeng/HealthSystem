package dao;

import entity.ConsTreatment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsTreatmentMapper {
    @Delete({
        "delete from cons_treatment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cons_treatment (id, docId, ",
        "cons_Id, treatments)",
        "values (#{id,jdbcType=INTEGER}, #{docid,jdbcType=INTEGER}, ",
        "#{consId,jdbcType=INTEGER}, #{treatments,jdbcType=LONGVARCHAR})"
    })
    int insert(ConsTreatment record);

    int insertSelective(ConsTreatment record);

    @Select({
        "select",
        "id, docId, cons_Id, treatments",
        "from cons_treatment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    ConsTreatment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConsTreatment record);

    @Update({
        "update cons_treatment",
        "set docId = #{docid,jdbcType=INTEGER},",
          "cons_Id = #{consId,jdbcType=INTEGER},",
          "treatments = #{treatments,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(ConsTreatment record);

    @Update({
        "update cons_treatment",
        "set docId = #{docid,jdbcType=INTEGER},",
          "cons_Id = #{consId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ConsTreatment record);
}