package com.is.cashApp.service;

import com.is.cashApp.entity.Cash;
import com.is.cashApp.repository.CashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public List<Cash> getIncomesByDate(Date date) {
        return cashRepository.findByEntryDateAndAndTypeId(date, 1);
    }

    public List<Cash> getExpensesByDate(Date date) {
        return cashRepository.findByEntryDateAndAndTypeId(date, 2);
    }

    public List<Cash> getAll(Date date) {
        return cashRepository.findAllByEntryDate(date);
    }

    public List<Cash> getAllBetweenDates(Date dateFrom, Date dateTo) {
        return cashRepository.findAllByEntryDateBetweenOrderByEntryDate(dateFrom, dateTo);
    }

    public Cash getCashById(Integer id) {
        return cashRepository.getOne(id);
    }

    public void remove(Cash cash) {
        cashRepository.delete(cash);
        System.out.println("Cash is deleted");
    }
}
