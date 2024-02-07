package com.test.bundle;

import org.osgi.annotation.bundle.Header;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;

@Header(name = Constants.BUNDLE_ACTIVATOR, value = "${@class}")
public class Activator implements BundleActivator {
    private MetricsHttpExporterServiceTracker httpServiceTracker;

    @Override
    public void start(final BundleContext bundleContext) throws Exception {
        httpServiceTracker = new MetricsHttpExporterServiceTracker(bundleContext);
        httpServiceTracker.open();
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        httpServiceTracker.close();
    }

}
