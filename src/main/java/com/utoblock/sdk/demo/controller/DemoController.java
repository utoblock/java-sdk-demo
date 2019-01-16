package com.utoblock.sdk.demo.controller;

import com.alibaba.fastjson.JSON;
import com.utoblock.sdk.UtoBlock;
import com.utoblock.sdk.demo.controller.base.BaseController;
import com.utoblock.sdk.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * 描述：控制器
 * 创建人：yoan
 * 创建日期：2019/1/14
 */
@RestController
@Api(value = "DemoController", tags = "Demo")
public class DemoController extends BaseController {

    @PostMapping("/mining")
    @ApiOperation(value = "挖矿")
    public String mining(@ApiParam(value = "用户账号", required = true) @RequestParam String account,
                         @ApiParam(value = "事件key", required = true) @RequestParam String key,
                         @ApiParam(value = "描述") @RequestParam(required = false) String description,
                         @ApiParam(value = "额外参数") @RequestParam(required = false) String extras) {
        MiningResponse response = UtoBlock.mining()
                .account(account)
                .key(key)
                .description(description)
                .extras(extras)
                .start();
        return JSON.toJSONString(response);
    }

    @PostMapping("/consume")
    @ApiOperation(value = "消费")
    public String consume(@ApiParam(value = "用户账号", required = true) @RequestParam String account,
                          @ApiParam(value = "商品名称", required = true) @RequestParam String name,
                          @ApiParam(value = "商户流水号", required = true) @RequestParam String tradeNo,
                          @ApiParam(value = "消耗积分", required = true) @RequestParam BigDecimal value,
                          @ApiParam(value = "描述") @RequestParam(required = false) String description,
                          @ApiParam(value = "额外参数") @RequestParam(required = false) String extras) {
        ConsumeResponse response = UtoBlock.consume()
                .account(account)
                .name(name)
                .tradeNo(tradeNo)
                .value(value)
                .description(description)
                .extras(extras)
                .start();
        return JSON.toJSONString(response);
    }

    @GetMapping("/balance")
    @ApiOperation(value = "查询钱包")
    public String balance(@ApiParam(value = "用户账号", required = true) @RequestParam String account) {
        BalanceResponse response = UtoBlock.balance()
                .account(account)
                .start();
        return JSON.toJSONString(response);
    }

    @GetMapping("/transactions")
    @ApiOperation(value = "查询流水")
    public String transactions(@ApiParam(value = "用户账号", required = true) @RequestParam String account,
                               @ApiParam(value = "当前页", required = true, defaultValue = "1") @RequestParam Integer page,
                               @ApiParam(value = "页大小", required = true, defaultValue = "10") @RequestParam Integer size) {
        TransactionResponse response = UtoBlock.transaction()
                .account(account)
                .page(page)
                .size(size)
                .start();
        return JSON.toJSONString(response);
    }

    @PostMapping("/notify")
    @ApiOperation(value = "回调方法")
    public String notify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ConsumeResultResponse resultResponse = UtoBlock.consumeResult()
                .inputStream(request.getInputStream())
                .start();
        return JSON.toJSONString(resultResponse);
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public String login(@ApiParam(value = "用户账号", required = true) @RequestParam String account) {
        LoginResponse response = UtoBlock.login()
                .account(account)
                .start();
        return JSON.toJSONString(response);
    }

    @PostMapping("/consumeNoPass")
    @ApiOperation(value = "免密消费")
    public String consumeNoPass(@ApiParam(value = "用户账号", required = true) @RequestParam String account,
                                @ApiParam(value = "商品名称", required = true) @RequestParam String name,
                                @ApiParam(value = "商户流水号", required = true) @RequestParam String tradeNo,
                                @ApiParam(value = "消耗积分", required = true) @RequestParam BigDecimal value,
                                @ApiParam(value = "描述") @RequestParam(required = false) String description,
                                @ApiParam(value = "额外参数") @RequestParam(required = false) String extras) {
        ConsumeNoPassResponse response = UtoBlock.consumeWithoutPass()
                .account(account)
                .name(name)
                .tradeNo(tradeNo)
                .value(value)
                .description(description)
                .extras(extras)
                .start();
        return JSON.toJSONString(response);
    }
}
