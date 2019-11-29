package com.hfuu.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 16688
 * @PackageName:com.hfuu.web.interceptor
 * @ClassName:Interceptor
 * @Description:
 * @autor:Starry the Night
 * @Date:2019/10/26 13:17
 */
public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {


//        //获取父url  如果不是直接输入的话就是先前的访问过来的页面，要是用户输入了，这个父url是不存在的
//        String conString = request.getHeader("REFERER");
//        /*System.err.println(request.getContextPath());*/
//        //判断如果上一个目录为空的话，说明是用户直接输入url访问的
//        if("".equals(conString) || null==conString){
//            response.sendRedirect(request.getContextPath());
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
    }
}
