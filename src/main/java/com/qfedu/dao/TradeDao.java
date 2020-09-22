package com.qfedu.dao;

import com.qfedu.entity.Trade;
import com.qfedu.vo.VTradeInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

public interface TradeDao {
    public List<VTradeInfo> findAll(@Param("uid")Integer uid,@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
    public void add(Trade trade);
}
