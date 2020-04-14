package com.sqs.controller;

import com.sqs.bean.CustomerCase;
import com.sqs.producer.SqsQueueSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/sqs")
public class SQSController {

	private static final Logger LOG = LoggerFactory.getLogger(SQSController.class);

	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;

	@Value("${cloud.aws.end-point.uri}")
	private String sqsEndPoint;

	@GetMapping
	public void sendMessage() {
		Map<String,Object> headers = new HashMap<>();
        headers.put("user_name","USER_NAME");
        headers.put("case_screen_requestor","OGS");

        SqsQueueSender sqsQueueSender = new SqsQueueSender(queueMessagingTemplate);
        sqsQueueSender.send(sqsEndPoint, new CustomerCase("XRIUWDNNKIGOLSJDLW", "RUPESH"), headers);
	}
}
