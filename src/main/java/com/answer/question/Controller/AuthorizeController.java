package com.answer.question.Controller;

import com.answer.question.dto.AccessTokenDTO;
import com.answer.question.provider.GithubProvider;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

public class AuthorizeController {


    @Autowired
    private GithubProvider githubProvider;
    
    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.client.url}")
    private String clientUrl;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setRedirect_url(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
//        GithubUser user = githubProvider.getUser(accesToken);
//        System.out.println(user.getName());
        if(user!=null){
            //登录成功，写cookie和session
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        }else{
            //登录失败，
        }
        return "/";
    }

}
