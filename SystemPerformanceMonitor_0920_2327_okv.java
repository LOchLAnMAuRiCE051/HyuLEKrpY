// 代码生成时间: 2025-09-20 23:27:19
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;

/**
 * A RESTful service that provides system performance monitoring.
 */
@Path("/monitor")
public class SystemPerformanceMonitor {

    private static final OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
    private static final RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
    private static final ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();

    /**
     * Returns system performance information in JSON format.
     *
     * @return JSON String containing system performance metrics.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSystemPerformance() {
        try {
            StringBuilder performanceData = new StringBuilder();
            performanceData.append("{"cpuLoad":"").append(osBean.getSystemCpuLoad()).append("","systemLoad":"").append(osBean.getSystemLoadAverage()).append("","
            + "memoryUsage":"").append(runtimeBean.getMemoryMXBean().getHeapMemoryUsage().getUsed()).append("","threadCount":"").append(threadBean.getThreadCount()).append(""}
");
            return performanceData.toString();
        } catch (Exception e) {
            // Log the exception and return an error message
            // Logger.log(e);
            return "{"error":"System performance data could not be retrieved."}";
        }
    }
}
