package com.test.bundle;

import io.prometheus.metrics.expositionformats.ExpositionFormats;
import io.prometheus.metrics.expositionformats.PrometheusTextFormatWriter;
import io.prometheus.metrics.instrumentation.jvm.JvmMetrics;
import io.prometheus.metrics.model.registry.PrometheusRegistry;
import io.prometheus.metrics.model.snapshots.MetricSnapshots;
import org.osgi.service.component.annotations.Component;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;


@Component(immediate=true)
public class MetricsExporterServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        JvmMetrics.builder().register();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        final MetricSnapshots scrape = PrometheusRegistry.defaultRegistry.scrape();
        final ExpositionFormats init = ExpositionFormats.init();
        final PrometheusTextFormatWriter writer = init.getPrometheusTextFormatWriter();

        writer.write(outputStream, scrape);

        response.setStatus(200);
        response.setHeader("Content-Type", "text/plain; charset=utf-8");
        response.setContentLength(outputStream.size());
        response.getOutputStream().write(outputStream.toByteArray());
    }

    private boolean shouldUseCompression(HttpServletRequest request) {
        final Enumeration<String> encodingHeaders = request.getHeaders("Accept-Encoding");
        if (encodingHeaders == null) {
            return false;
        }
        while (encodingHeaders.hasMoreElements()) {
            final String encodingHeader = encodingHeaders.nextElement();
            final String[] encodings = encodingHeader.split(",");
            for (String encoding : encodings) {
                if (encoding.trim().equalsIgnoreCase("gzip")) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }
    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
    }
}
