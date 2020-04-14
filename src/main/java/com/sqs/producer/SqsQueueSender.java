package com.sqs.producer;

import com.sqs.bean.CustomerCase;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;

import java.util.Map;

public class SqsQueueSender {

    private final QueueMessagingTemplate queueMessagingTemplate;

    public SqsQueueSender(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    public void send(String destinationName, CustomerCase customerCase, Map<String,Object> headers) {
        this.queueMessagingTemplate.convertAndSend(destinationName, customerCase, headers);
    }
}
