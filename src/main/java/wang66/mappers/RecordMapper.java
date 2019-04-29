package wang66.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import wang66.POJO.Record;

import java.util.List;

public interface RecordMapper {
    @Insert("insert into record_${selfCardId}(cardId,money,comment)values(#{cardId},#{money},#{comment})")
    int insertRecord(Record record);

    @Select("select * from record_${selfCardId} where ${condition}")
    List<Record> queryByCondition(@Param("selfCardId")String selfCardId,@Param("condition")String condition);
}

