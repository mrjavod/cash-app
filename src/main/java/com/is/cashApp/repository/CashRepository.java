package com.is.cashApp.repository;

import com.is.cashApp.entity.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CashRepository extends JpaRepository<Cash, Integer> {

    List<Cash> findByTypeId(Integer typeId);

    List<Cash> findByEntryDateAndAndTypeId(Date date, Integer typeId);

    List<Cash> findAllByEntryDate(Date date);

    List<Cash> findAllByEntryDateBetweenOrderByEntryDate(Date fromDate, Date toDate);

    @Query(value = "UPDATE Cash SET description=?1, cost=?2, entryDate=?3 WHERE id=?4", nativeQuery = true)
    void update(String desc, Double cost, Date date, Integer id);

}
