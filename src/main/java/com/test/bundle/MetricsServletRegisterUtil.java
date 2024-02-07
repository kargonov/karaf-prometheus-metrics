package com.test.bundle;

import org.osgi.service.http.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetricsServletRegisterUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(MetricsServletRegisterUtil.class);
    private static final String ALIAS = "/metrics";

    public static void registerServlet(final HttpService httpService) {
        LOGGER.info("registerServlet");
        try {
            httpService.registerServlet(ALIAS, new MetricsExporterServlet(), null, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void unregisterServlet(final HttpService httpService) {
        LOGGER.info("unregisterServlet");
        httpService.unregister(ALIAS);
    }

}
