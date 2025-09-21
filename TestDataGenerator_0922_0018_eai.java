// 代码生成时间: 2025-09-22 00:18:35
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;
import java.util.UUID;

// 测试数据生成器类，用于生成随机测试数据
@Path("/testData")
public class TestDataGenerator {

    // 构造函数
    public TestDataGenerator() {
        // 初始化代码可以放在这里
    }

    // GET请求处理方法，返回随机测试数据
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String generateTestData() {
        try {
            // 生成随机字符串
            String randomString = UUID.randomUUID().toString();
            // 生成随机数字
            int randomInt = new Random().nextInt(100);
            // 构建测试数据JSON字符串
            String testData = String.format({"{"id": "%s", "value": %d}"}, randomString, randomInt);
            // 返回测试数据
            return testData;
        } catch (Exception e) {
            // 错误处理
            return String.format({"{"error": "%s"}"}, e.getMessage());
        }
    }
}
