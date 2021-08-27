package corp.sap.internal.exp.listener;

import corp.sap.internal.exp.service.DataBaseOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DatabasePrepareApplicationEventListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    DataBaseOperationService dataBaseOperationService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        dataBaseOperationService.setupDataBase("resources/java_reference_db.sql");
        dataBaseOperationService.setupData("resources/java_reference_data.sql");
    }
}
