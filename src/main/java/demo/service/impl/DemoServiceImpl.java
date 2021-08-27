package demo.service.impl;

import demo.repository.mapper.DemoMapper;
import demo.repository.po.Demo;
import demo.service.DemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DemoServiceImpl implements DemoService {
    @Resource
    private DemoMapper demoMapper;

    @Override
    public Long add(Demo demo) {
        demoMapper.insertSelective(demo);
        return demo.getId();
    }

    @Override
    public Demo find(Long id) {
        return demoMapper.selectByPrimaryKey(id);
    }
}
