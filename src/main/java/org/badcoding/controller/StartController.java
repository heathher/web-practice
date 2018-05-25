package org.badcoding.controller;

import org.badcoding.dao.SalesOrderEntity;
import org.badcoding.dao.implementation.SalesOrderImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class StartController {

    @Autowired
    SalesOrderImplementation dao = new SalesOrderImplementation();

    @Value("admin")
    private String admin;

    @Value("admin")
    private String admin_password;

    @RequestMapping(value="/start", method= RequestMethod.GET)
    public String greetingForm(Map<String, Object> model) {
        List<SalesOrderEntity> sales = dao.getList();
        model.put("login", sales);
        return "start";
    }

}