package com.qjp.user.designPattern.strategyPattern.demo.handler;


import com.qjp.user.designPattern.strategyPattern.demo.PayChannelEnum;
import com.qjp.user.designPattern.strategyPattern.demo.PayHandler;
import org.springframework.stereotype.Component;

@Component
public class WxPayHandler implements PayHandler {

    @Override
    public PayChannelEnum getChannel() {
        return PayChannelEnum.WX_PAY;
    }

    @Override
    public void dealPay() {
        System.out.println("微信支付");
    }
}
