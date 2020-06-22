package com.is.cashApp.repository;

import com.is.cashApp.entity.Cash;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashRepository extends JpaRepository<Cash, Long> {

    List<Cash> findByTypeId(Integer typeId);

    List<Cash> findByEntryDate(String date);

}
