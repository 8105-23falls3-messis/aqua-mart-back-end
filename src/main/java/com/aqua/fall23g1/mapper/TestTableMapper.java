package com.aqua.fall23g1.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface TestTableMapper {

    int testInsertData(String data);

}