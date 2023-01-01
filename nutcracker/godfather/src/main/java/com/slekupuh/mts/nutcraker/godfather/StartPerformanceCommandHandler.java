package com.slekupuh.mts.nutcraker.godfather;


import com.slekupuh.mts.nutcraker.core.command.CommandHandler;
import com.slekupuh.mts.nutcraker.godfather.command.StartPerformanceCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StartPerformanceCommandHandler extends CommandHandler<StartPerformanceCommand> {
    @Override
    @JmsListener(destination = "godfatherCommand") //, containerFactory = "commandFactory"
    public void handle(StartPerformanceCommand command) {
        log.debug(" command {} processId {}",command.getClass().getSimpleName(), command.getProcessId());
        // todo:next
    }

    //JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
    //
    //    // Send a message with a POJO - the template reuse the message converter
    //    System.out.println("Sending an email message.");
    //    jmsTemplate.convertAndSend("mailbox", new Email("info@example.com", "Hello"));
    //  }
}
