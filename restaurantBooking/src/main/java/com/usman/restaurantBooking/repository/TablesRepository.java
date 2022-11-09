package com.usman.restaurantBooking.repository;

import com.usman.restaurantBooking.entities.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TablesRepository extends JpaRepository<Tables, Integer> {
    @Query("select table from Tables table where table.tableCapacity >= ?1 and table.availableTable > ?2 order by table.tableCapacity DESC")
    List<Tables> findAvailableTables(int tableCapacity, int availableTable);

//    @Query("select table from Tables table where table.tableCapacity >= ?1 and table.availableTable > ?2 order by table.tableCapacity DESC")
//    List<Tables> findAvailableTables(int tableCapacity, int availableTable);
}