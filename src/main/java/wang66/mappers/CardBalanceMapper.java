package wang66.mappers;

import org.apache.ibatis.annotations.*;
import wang66.POJO.Card;
import wang66.POJO.CardBalance;

import java.util.List;

public interface CardBalanceMapper {
    @Insert("insert into card_balance(cardId,balance) values(#{cardId},#{balance})")
    int insertBalance(CardBalance cardBalance);
    @Select("select * from card_balance where cardId=#{cardId}")
    CardBalance queryCardByCardId(@Param("cardId")String cardId);
    @Update("update card_balance set balance=#{balance} where cardId=#{cardId}")
    int resetBalance(CardBalance cardBalance);
    @Select("select cb.cardId,cb.balance,isnull(f.cardId) as ok from card_balance cb left join frozen f on cb.cardId=f.cardId where cb.cardId like #{prefix}")
    List<Card> getCards(@Param("prefix")String prefix);
    @Delete("delete from card_blance where cardId=#{cardId}")
    int deleteCardBalanceByCardId(@Param("cardId")String cardId);
}
