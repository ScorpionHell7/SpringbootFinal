package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    PersonRepository personRepository;

    @Value("${eazyschool.pageSize}")
    private int defaultPageSize;

    @Value("${eazyschool.contact.successMsg}")
    private String message;

    @Autowired
    Environment environment;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model,Authentication authentication, HttpSession session) {
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        if(null!=person.getEazyClass() && null!=person.getEazyClass().getName()){
            model.addAttribute("enrolledClass", person.getEazyClass().getName());
        }
        session.setAttribute("loggedInPerson", person);
        logMessage();
        return "dashboard.html";
    }

    private void logMessage() {
        log.error("Error message form dashboard page");
        log.warn("Warning message form dashboard page");
        log.info("Info message form dashboard page");
        log.debug("Debug message form dashboard page");
        log.trace("Trace message form dashboard page");

        log.error("Default page size form dashboard page is : "+defaultPageSize);
        log.error("Message form dashboard page is : "+message);

        log.error("Environment page size form dashboard page is : "+environment.getProperty("eazyschool.pageSize"));
        log.error("Environment Message form dashboard page is : "+environment.getProperty("eazyschool.contact.successMsg"));
        log.error("Java home : "+environment.getProperty("JAVA_HOME"));

    }



}
