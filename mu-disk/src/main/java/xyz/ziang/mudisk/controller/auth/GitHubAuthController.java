package xyz.ziang.mudisk.controller.auth;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.ziang.mudisk.service.GitHubAuthService;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/github")
@AllArgsConstructor
public class GitHubAuthController {
    /**
     * github认证的业务处理类
     */
    @Autowired
    private GitHubAuthService githubAuthService;

    /**
     * 跳转到github的授权登录页面
     * @param response
     * @throws Exception
     */
    @GetMapping("/login")
    public void showLoginPage(HttpServletResponse response) throws Exception {
        String path = githubAuthService.getGithubAuthPath();
        response.sendRedirect(path);
    }

    /**
     * 在github页面登录成功后，github会回调这个
     * @param code
     * @return
     */
    @GetMapping(value = "/callback")
    @ResponseBody
    public String callback(@RequestParam(value = "code") String code) {
        return githubAuthService.callback(code);
    }
}