package xyz.ziang.mudisk.service;


public interface GitHubAuthService {

    /**
     * 获取github的授权地址
     * @return
     */
    String getGithubAuthPath();

    /**
     * 在github页面登录成功后，回调的处理方法
     * @param code
     * @return
     */
    String callback(String code);
}