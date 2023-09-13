package com.aqua.fall23g1.service.impl;

import com.aqua.fall23g1.mapper.TestTableMapper;
import com.aqua.fall23g1.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestTableMapper testTableMapper;

    @Override
    public int testInsertData(String data) {
        return testTableMapper.testInsertData(data);
    }
}
