package com.example.bool_sales.repository;

import com.example.bool_sales.entity.BookSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookSalesDao extends JpaRepository<BookSales,String> {
}
