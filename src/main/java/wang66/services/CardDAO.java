package wang66.services;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang66.mappers.FrozenCardMapper;

@Service
public class CardDAO {
    @Autowired
    SqlSessionTemplate template;

    public void frozeCard(String cardId){
        FrozenCardMapper mapper=template.getMapper(FrozenCardMapper.class);
        mapper.insert(cardId);
    }
    public void unfrozeCard(String cardId){
        FrozenCardMapper mapper=template.getMapper(FrozenCardMapper.class);
        mapper.delete(cardId);
    }
}
