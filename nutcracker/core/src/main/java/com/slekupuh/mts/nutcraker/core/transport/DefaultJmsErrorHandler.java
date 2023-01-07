package com.slekupuh.mts.nutcraker.core.transport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

@Slf4j
@Service
public class DefaultJmsErrorHandler implements ErrorHandler {
    @Override
    public void handleError(Throwable t) {
        log.warn("In default jms error handler...");
        log.error("Error Message : {}", t.getMessage());
    }

}
