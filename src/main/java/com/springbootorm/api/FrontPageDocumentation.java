package com.springbootorm.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Controller for index.html
@Controller
public class FrontPageDocumentation {

    @RequestMapping({"/"})
    public String index() {
        return "index";
    }
}
