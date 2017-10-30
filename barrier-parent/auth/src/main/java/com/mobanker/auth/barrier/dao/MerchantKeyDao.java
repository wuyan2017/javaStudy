package com.mobanker.auth.barrier.dao;

import com.mobanker.auth.barrier.entity.MerchantKey;
import org.springframework.stereotype.Repository;

/**
 * @packageName: com.mobanker.auth.barrier.dao
 * @description:
 * @author: zhuosh
 * @DATE: 2016/5/11
 */
@Repository
public interface MerchantKeyDao {

    /**
     * 根据指定的商户ID查询
     * @param merchantID
     * @return
     */
    public MerchantKey queryConfirmMerchant(String merchantID);

}
