package com.is.cashApp.controller;

import com.is.cashApp.entity.Cash;
import com.is.cashApp.models.AppResponse;
import com.is.cashApp.models.Entries;
import com.is.cashApp.repository.CashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("api")
public class Rest {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private CashRepository repository;

    @Autowired
    public Rest(CashRepository repository) {
        this.repository = repository;
    }

    @PostMapping("create")
    public ResponseEntity create(@RequestBody Entries cash) throws ParseException {

        String typeName = (cash.getType() == 1) ? "Incomes" : "Expenses";

        Cash c = repository.save(new Cash(cash.getDesc(), cash.getCost(), dateFormat.parse(cash.getDate()), cash.getType(), typeName));

        return new ResponseEntity<>(new AppResponse(1, "Successfully created", c), HttpStatus.CREATED);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Integer id) {

        Cash cash = repository.findById(id).orElse(null);

        if (cash == null) {
            return new ResponseEntity<>(new AppResponse(0, "Cash not found", null),
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(new AppResponse(1, "Successfully find", cash), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity getAll() {

        List<Cash> cashList = repository.findAll();

        if (cashList.size() == 0) {
            return new ResponseEntity<>(new AppResponse(0, "Cash not found", null),
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(new AppResponse(1, "Successfully find", cashList), HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity update(@RequestBody Entries entries) throws ParseException {

        Cash cash = repository.findById(entries.getId()).orElse(null);

        if (cash == null) {
            return new ResponseEntity<>(new AppResponse(0, "Cash not found", null),
                    HttpStatus.OK);
        }

        cash.setCost(entries.getCost());
        cash.setEntryDate(dateFormat.parse(entries.getDate()));
        cash.setDescription(entries.getDesc());
        cash.setTypeId(entries.getType());

        Cash updatedCash = repository.save(cash);

        return new ResponseEntity<>(new AppResponse(1, "Successfully updated", updatedCash), HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity delete(@RequestBody Entries entries) {

        Cash cash = repository.findById(entries.getId()).orElse(null);

        if (cash == null) {
            return new ResponseEntity<>(new AppResponse(0, "Cash not found", null), HttpStatus.OK);
        }

        repository.delete(cash);
        return new ResponseEntity<>(new AppResponse(1, "Successfully deleted", null), HttpStatus.OK);
    }

    @GetMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Integer id) {

        Cash cash = repository.findById(id).orElse(null);

        if (cash == null) {
            return new ResponseEntity<>(new AppResponse(0, "Cash not found", null), HttpStatus.OK);
        }

        repository.delete(cash);
        return new ResponseEntity<>(new AppResponse(1, "Successfully deleted", null), HttpStatus.OK);
    }
}
