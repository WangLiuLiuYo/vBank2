package wang66.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import wang66.POJO.KV;

import java.util.List;

public interface testMapper {
    @Select("select * from mb_test")
    List<KV> query();

    @Insert("insert into mb_test values(#{k},#{v})")
    int insert(KV kv);
    @Delete("delete from mb_test where k=${k}")
    int delete(@Param("k")int k);
}