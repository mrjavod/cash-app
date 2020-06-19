package com.is.cashApp.controller;

import com.is.cashApp.models.Entries;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                                 @RequestParam(name = "type") Integer type,
                                 @RequestParam(name = "date") String date) {

        String typeName = (type == 1) ? "Incomes" : "Expenses";
        entriesList.add(new Entries(desc, cost, typeName, date));

        model.put("title", "Add new Entry");
        return "createEntry";
    }

    @GetMapping("/incomes")
    public String incomes(Map<String, Object> model) {
        model.put("title", "Incomes");
        model.put("entriesList", entriesList.stream().filter(e -> e.getType().equals("Incomes"))
                                                    .collect(Collectors.toList()));
        return "incomes";
    }

    @GetMapping("/expenses")
    public String expenses(Map<String, Object> model) {
        model.put("title", "Expenses");
        model.put("entriesList", entriesList.stream().filter(e -> e.getType().equals("Expenses"))
                                                    .collect(Collectors.toList()));
        return "expenses";
    }

    @PostMapping("/searchExpenses")
    public String searchExpenses(Map<String, Object> model,
                                 @RequestParam(name = "searchDate") String searchDate){

        model.put("title", "Expenses");
        model.put("entriesList", entriesList.stream().filter(e -> e.getType().equals("Expenses")
                && e.getDate().equals(searchDate)).collect(Collectors.toList()));
        return "expenses";
    }

    @GetMapping("/all")
    public String allData(Map<String, Object> model) {
        model.put("title", "All Data");
        model.put("entriesList", entriesList);
        return "all";
    }

    @PostMapping("/search")
    public String search(Map<String, Object> model,
                         @RequestParam(name = "dateFrom") Date dateFrom,
                         @RequestParam(name = "dateTo") Date dateTo){

        model.put("title", "All data");
        model.put("entriesList", entriesList);
        return "expenses";
    }
}
