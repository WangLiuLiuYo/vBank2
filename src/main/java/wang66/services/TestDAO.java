package wang66.services;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wang66.POJO.KV;
import wang66.mappers.testMapper;

@Service
public class TestDAO {
    @Autowired
    SqlSessionTemplate template;
    @Transactional(rollbackFor = Exception.class)
    public void transTest(){
        testMapper mapper=template.getMapper(testMapper.class);
        KV kv=new KV();
        kv.setK(8);
        kv.setV("Eight");
        mapper.insert(kv);
        int k=100/0;
        kv.setK(6);
        kv.setV("Six");
        mapper.insert(kv);
    }
    public int delete(int key){
        testMapper mapper=template.getMapper(testMapper.class);
        return mapper.delete(key);
    }
}
