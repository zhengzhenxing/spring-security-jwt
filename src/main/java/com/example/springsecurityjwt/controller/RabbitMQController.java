package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.api.CommonResult;
import com.example.springsecurityjwt.service.MQDelaySenderService;
import com.example.springsecurityjwt.service.MQDirectSenderService;
import com.example.springsecurityjwt.service.MQFanoutSenderService;
import com.example.springsecurityjwt.service.MQTopicSenderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQController {

    @Resource
    private MQDirectSenderService mqDirectSenderService;

    @Resource
    private MQTopicSenderService mqTopicSenderService;

    @Resource
    private MQFanoutSenderService mqFanoutSenderService;

    @Resource
    private MQDelaySenderService mqDelaySenderService;

    @PostMapping("/direct-send")
    public CommonResult<String> directSend(@RequestBody String message) {
        mqDirectSenderService.send(message);
        return CommonResult.success("send " + message);
    }

    @PostMapping("/topic-send")
    public CommonResult<String> topicSend() {
        mqTopicSenderService.send();
        return CommonResult.success("send success");
    }

    @PostMapping("/topic-send-a")
    public CommonResult<String> topicSendA() {
        mqTopicSenderService.sendA();
        return CommonResult.success("send success");
    }

    @PostMapping("/topic-send-b")
    public CommonResult<String> topicSendB() {
        mqTopicSenderService.sendB();
        return CommonResult.success("send success");
    }

    @PostMapping("/fanout-send")
    public CommonResult<String> fanoutSend() {
        mqFanoutSenderService.send();
        return CommonResult.success("send success");
    }

    @PostMapping("/delay-send")
    public CommonResult<String> delaySend() {
        mqDelaySenderService.send();
        return CommonResult.success("send success");
    }

}
