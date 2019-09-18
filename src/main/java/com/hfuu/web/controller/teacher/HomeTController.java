package com.hfuu.web.controller.teacher;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class HomeTController {
    static private Logger log = Logger.getLogger(HomeTController.class);

    @RequestMapping(value = {"/homet"}, method = RequestMethod.GET)
    public String toHomeT() {
        log.debug("跳转到:teacher/homet.jsp");
        return "teacher/homet";
    }
}
