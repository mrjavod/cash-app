package com.is.cashApp.service;

import com.is.cashApp.entity.Cash;
import com.is.cashApp.repository.CashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashService {

    private CashRepository cashRepository;

    @Autowired
    public CashService(CashRepository cashRepository) {
        this.cashRepository = cashRepository;
    }

    public void save(Cash cash) {
        cashRepository.save(cash);
    }

    public List<Cash> getIncomes() {
        return cashRepository.findByTypeId(1);
    }

    public List<Cash> getExpenses() {
        return cashRepository.findByTypeId(2);
    }

    public List<Cash> getExpensesByDate(String date) {
        return cashRepository.findByEntryDate(date);
    }

}
