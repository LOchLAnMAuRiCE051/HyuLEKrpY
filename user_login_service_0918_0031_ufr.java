// 代码生成时间: 2025-09-18 00:31:44
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

// 用户登录验证服务类
@Path("/login")
public class UserLoginService {

    // 模拟数据库中的用户信息，用于验证
    private Map<String, String> userDatabase = new HashMap<>();

    // 构造函数，初始化用户信息
    public UserLoginService() {
        // 假设有两个用户，用户名和密码都是明文存储
        userDatabase.put("user1", "password1");
        userDatabase.put("user2", "password2");
    }

    // 登录接口，接收用户名和密码，返回登录结果
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String username, String password) {
        // 检查用户名和密码是否匹配
        if (username != null && password != null && userDatabase.containsKey(username) &&
                userDatabase.get(username).equals(password)) {
            // 登录成功
            return Response.ok().entity(toJson("Login successful")).build();
        } else {
            // 登录失败
            return Response.status(Response.Status.UNAUTHORIZED).entity(toJson("Login failed")).build();
        }
    }

    // 将结果转换为JSON格式的字符串
    private String toJson(String message) {
        return "{"message":"" + message + ""}";
    }
}
