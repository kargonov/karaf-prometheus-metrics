package com.test.bundle;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MetricsHttpExporterServiceTracker extends ServiceTracker<HttpService, HttpService> {

    private final static Logger LOGGER = LoggerFactory.getLogger(MetricsHttpExporterServiceTracker.class);

    public MetricsHttpExporterServiceTracker(final BundleContext context) {
        super(context, HttpService.class.getName(), null);
    }

    @Override
    public HttpService addingService(final ServiceReference<HttpService> ref) {
        LOGGER.info("register MetricsServlet");
        final HttpService httpService = context.getService(ref);
        MetricsServletRegisterUtil.registerServlet(httpService);
        return httpService;
    }

    @Override
    public void removedService(ServiceReference<HttpService> reference, HttpService httpService) {
        MetricsServletRegisterUtil.unregisterServlet(httpService);
        context.ungetService(reference);
    }
}
