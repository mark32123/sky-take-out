package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {


    @Autowired
    private OrderMapper orderMapper;

    /**
     * 处理支付超时订单
     */
    @Scheduled(cron = "0 * * * * ?")
    public void processTimeoutOrder() {

        log.info("处理支付超时订单");

        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);

        List<Orders> list = orderMapper.getByStatusAndOrderTimeOut(Orders.PENDING_PAYMENT, time);

        if (list != null && list.size() > 0) {
            for (Orders orders : list) {
                orderMapper.update(Orders.builder().id(orders.getId()).status(Orders.CANCELLED).cancelReason("订单超时，自动取消").cancelTime(LocalDateTime.now()).build());
            }
        }
    }

    /**
     * 处理打烊后依旧派送中订单
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void processDeliveryTimeOutOrder() {

        log.info("处理派送订单");

        LocalDateTime time = LocalDateTime.now().plusMinutes(-60);

        List<Orders> list = orderMapper.getByStatusAndOrderTimeOut(Orders.DELIVERY_IN_PROGRESS, time);

        if (list != null && list.size() > 0) {
            for (Orders orders : list){
                orders.setStatus(Orders.COMPLETED);
                orders.setDeliveryTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }
    }

}
