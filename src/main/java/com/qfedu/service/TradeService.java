package com.qfedu.service;

import com.qfedu.vo.VTradeInfo;

import java.util.Date;
import java.util.List;

public interface TradeService {
    public List<VTradeInfo> findAllTrades(Integer id, Date beginTime, Date endTime, Integer page, Integer limit);
    public void transfer(String code, String otherCode, Double money);
}
