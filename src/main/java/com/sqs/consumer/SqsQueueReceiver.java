package com.sqs.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class SqsQueueReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(SqsQueueReceiver.class);

    @SqsListener(value = {"${cloud.aws.end-point.uri}"}, deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void listen(Acknowledgment acknowledgment, String message, @Headers MessageHeaders headers) throws ExecutionException, InterruptedException {
        String messageId = (String) headers.get("MessageId");
        LOG.info("message from queue = messageId : "+messageId+" contents: "+message+" Headers: "+headers.containsKey("user_name"));
        acknowledgment.acknowledge().get();
    }
}
