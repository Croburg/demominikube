package de.mlinac.usertask.delegate;

import de.mlinac.usertask.message.MessageSender;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DelegateTaskCompleted implements ExecutionListener {

    private Logger log = LoggerFactory.getLogger(DelegateTaskCompleted.class);

    @Autowired
    private MessageSender messageSender;

    @Override
    public void notify(DelegateExecution execution) throws Exception {

        Map<String, Object> variables = execution.getVariables();
        messageSender.sendUsertask(variables.get("topic").toString(), variables);
    }
}
