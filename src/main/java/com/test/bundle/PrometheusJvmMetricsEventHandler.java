package com.test.bundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
        immediate = true,
        property = EventConstants.EVENT_TOPIC + "=*"
)
public class PrometheusJvmMetricsEventHandler implements EventHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(PrometheusJvmMetricsEventHandler.class);

    @Deactivate
    public void deactivate() throws Exception {
        LOGGER.info("PrometheusJvmMetricsEventHandler deactivated....");
    }

    @Override
    public void handleEvent(Event event) {
        for (String property : event.getPropertyNames()) {

        }
    }
}
