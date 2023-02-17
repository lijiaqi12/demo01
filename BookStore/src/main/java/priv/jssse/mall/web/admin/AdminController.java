package priv.jssse.mall.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import priv.jssse.mall.entity.AdminUser;
import priv.jssse.mall.service.AdminUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminUserService adminUserService;

    /*private AdminService adminService;*/

    /**
     * 访问首页
     *
     * @return
     */
    @RequestMapping("/toIndex.html")
    public String toIndex() {
        return "admin/index";
    }

    /**
     * 访问登录页面
     *
     * @return
     */
    @RequestMapping("/toLogin.html")
    public String toLogin() {
        return "admin/login";
    }

    /**
     * 登录请求
     *
     * @param username
     * @param password
     */
    //@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/login.do")
    public void login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*AdminUser adminUser = adminUserService.checkLogin(request, username, password);*/
        AdminUser adminUser = adminUserService.checkLogin(request, username, password);
       /* if (adminUser != null) {
            //登录成功 重定向到首页
            request.getSession().setAttribute("user", adminUser);
            response.sendRedirect("/mall/index.html");
        } else {
            throw new LoginException("登录失败！ 用户名或者密码错误");
        }*/
        response.sendRedirect("/priv/jssse/mall/admin/toIndex.html");
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/logout.do")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("login_user");
        response.sendRedirect("toLogin.html");
    }
}
