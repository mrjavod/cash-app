package com.is.cashApp.controller;

import com.is.cashApp.models.Entries;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private List<Entries> entriesList = new ArrayList<>();

    @GetMapping
    public String index(Map<String, Object> model) {
        model.put("title", "Home page");
        return "index";
    }

    @GetMapping("/home")
    public String homepage(Map<String, Object> model) {
        model.put("title", "Home page");
        return "index";
    }

    @GetMapping("/create")
    public String create(Map<String, Object> model) {
        model.put("title", "Add new Entry");
        return "createEntry";
    }

    @PostMapping("/createNewEntry")
    public String createNewEntry(Map<String, Object> model,
                                 @RequestParam(name = "desc") String desc,
                                 @RequestParam(name = "cost") Double cost,
                                 @RequestParam(name = "type") Integer type) {

        String typeName = (type == 1) ? "Incomes" : "Expenses";
        entriesList.add(new Entries(desc, cost, typeName));

        model.put("title", "Incomes");
        model.put("entriesList", entriesList);
        return "incomes";
    }

    @GetMapping("/incomes")
    public String incomes(Map<String, Object> model) {
        model.put("title", "Incomes");
        model.put("entriesList", entriesList);
        return "incomes";
    }
}
