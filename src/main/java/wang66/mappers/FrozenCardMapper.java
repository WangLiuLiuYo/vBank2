package wang66.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface FrozenCardMapper {
    @Select("select cardId from frozen where cardId=#{cardId}")
    String query(@Param("cardId")String cardId);
    @Insert("insert into frozen values(#{cardId})")
    int insert(@Param("cardId")String cardId);
    @Delete("delete from frozen where cardId=#{cardId};")
    int delete(@Param("cardId")String cardId);
}
