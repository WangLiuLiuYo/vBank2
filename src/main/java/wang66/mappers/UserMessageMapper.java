package wang66.mappers;

import org.apache.ibatis.annotations.*;
import wang66.POJO.User;

public interface UserMessageMapper {
    String recordTableStruct="(\n" +
            "`cardId` char(20) not null,\n" +
            "`money` decimal(20,2) not null,\n" +
            "`date` datetime default current_timestamp,\n" +
            "`comment` varchar(100));";
    @Insert("insert into users(username,uid,email,cardId) values(#{username},#{uid},#{email},#{cardId});")
    int registerUser(User user);
    @Update("create table record_${cardId} "+recordTableStruct)
    int createRecordTable(User user);
    @Update("drop table record_${cardId}")
    int dropRecordTable(User user);
    @Select("select * from users where uid=#{uid}")
    User findUserByUid(@Param("uid")String uid);
    @Update("update users set ${col}=#{value} where uid=#{uid}")
    int resetSomeMessage(@Param("col")String col,@Param("value")String val,@Param("uid")String uid);
    @Delete("delete from users where uid=#{uid}")
    int deleteUserByUid(@Param("uid")String uid);
    @Select("select * from users where username=#{username} and cardId=#{cardId}")
    User findUserByCardIdAndName(@Param("username")String name,@Param("cardId")String cardId);
    @Select("select cardId from users where email=#{email}")
    String findCardIdByEmail(@Param("email")String email);
    @Select("select * from users where cardId=#{cardId}")
    User findUserByCardId(@Param("cardId")String cardId);
}
