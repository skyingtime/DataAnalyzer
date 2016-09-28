package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yangliu on 28/09/2016.
 */
@Controller
public class MainController {

        @RequestMapping("/")
        @ResponseBody
        public String index() {
            return "Created by KevinLiu, DataAnalyzer";
        }

}
