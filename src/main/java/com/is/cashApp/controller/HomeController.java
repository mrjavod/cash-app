package com.is.cashApp.controller;

import com.is.cashApp.entity.Cash;
import com.is.cashApp.service.CashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class HomeController {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private CashService service;

    @Autowired
    public HomeController(CashService service) {
        this.service = service;
    }

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
                                 @RequestParam(name = "date") String date) throws ParseException {

        String typeName = (type == 1) ? "Incomes" : "Expenses";

        service.save(new Cash(desc, cost, dateFormat.parse(date), type, typeName));

        model.put("title", "Add new Entry");
        return "createEntry";
    }

    @GetMapping("/incomes")
    public String incomes(Map<String, Object> model) {

        String currentDate = dateFormat.format(new Date());

        model.put("title", "Incomes");
        model.put("currentDate", currentDate);
        model.put("entriesList", service.getIncomesByDate(new Date()));
        return "incomes";
    }

    @GetMapping("/expenses")
    public String expenses(Map<String, Object> model) {

        String currentDate = dateFormat.format(new Date());

        model.put("title", "Expenses");
        model.put("currentDate", currentDate);
        model.put("entriesList", service.getExpensesByDate(new Date()));
        return "expenses";
    }

    @PostMapping("/searchExpenses")
    public String searchExpenses(Map<String, Object> model,
                                 @RequestParam(name = "searchDate") String searchDate) throws ParseException {

        model.put("title", "Expenses");
        model.put("currentDate", searchDate);
        model.put("entriesList", service.getExpensesByDate(dateFormat.parse(searchDate)));
        return "expenses";
    }

    @PostMapping("/searchIncomes")
    public String searchIncomes(Map<String, Object> model,
                                @RequestParam(name = "searchDate") String searchDate) throws ParseException {

        model.put("title", "Incomes");
        model.put("currentDate", searchDate);
        model.put("entriesList", service.getIncomesByDate(dateFormat.parse(searchDate)));
        return "incomes";
    }

    @GetMapping("/all")
    public String allData(Map<String, Object> model) {
        model.put("title", "All Data");
        model.put("entriesList", service.getAll(new Date()));
        return "all";
    }

    @PostMapping("/search")
    public String search(Map<String, Object> model,
                         @RequestParam(name = "dateFrom") String dateFrom,
                         @RequestParam(name = "dateTo") String dateTo) throws ParseException {

        model.put("title", "All data");
        model.put("entriesList", service.getAllBetweenDates(dateFormat.parse(dateFrom), dateFormat.parse(dateTo)));
        return "all";
    }

    @GetMapping("/deleteIncomes/{id}")
    public String deleteIncomes(Map<String, Object> model,
                          @PathVariable(value = "id") Integer id) {

        Cash cash = service.getCashById(id);

        if (cash != null) {
            service.remove(cash);
        }

        String currentDate = dateFormat.format(new Date());

        model.put("title", "Incomes");
        model.put("currentDate", currentDate);
        model.put("entriesList", service.getIncomesByDate(new Date()));
        return "incomes";
    }

    @GetMapping("/deleteExpenses/{id}")
    public String deleteExpenses(Map<String, Object> model,
                          @PathVariable(value = "id") Integer id) {

        Cash cash = service.getCashById(id);

        if (cash != null) {
            service.remove(cash);
        }

        String currentDate = dateFormat.format(new Date());

        model.put("title", "Expenses");
        model.put("currentDate", currentDate);
        model.put("entriesList", service.getIncomesByDate(new Date()));
        return "expenses";
    }

}
