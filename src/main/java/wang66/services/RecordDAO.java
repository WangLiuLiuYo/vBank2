package wang66.services;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang66.POJO.CardBalance;
import wang66.POJO.Record;
import wang66.mappers.CardBalanceMapper;
import wang66.mappers.RecordMapper;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RecordDAO {

    @Autowired
    SqlSessionTemplate template;

    public BigDecimal queryBalance(String cardId){
        CardBalanceMapper cardBalanceMapper=template.getMapper(CardBalanceMapper.class);
        CardBalance cardBalance=cardBalanceMapper.queryCardByCardId(cardId);
        return cardBalance.getBalance();
    }
    public List<Record> queryByCondition(String cardId,String cond){
        RecordMapper recordMapper=template.getMapper(RecordMapper.class);
        return recordMapper.queryByCondition(cardId,cond);
    }
}
