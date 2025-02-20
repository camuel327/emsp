package com.example.emsp.mapper;

import com.example.emsp.model.po.AccountTokenRelPO;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTokenRelMapper {

    void insertAccountTokenRel(AccountTokenRelPO po);

}
