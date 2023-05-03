package com.example.bool_sales.service.ifs;

import com.example.bool_sales.entity.BookSales;
import com.example.bool_sales.vo.BookSalesResponse;

public interface BookSalesService {

  public BookSalesResponse addOrUpdateBook(BookSales bookSales);
  public BookSalesResponse findBooksByCategory(String category);
  public BookSalesResponse findBooksByTitleOrIsbnOrAuthor(String keyWord,boolean identity);
  public BookSalesResponse updateInventoryByTitle(String title,int newInventory);
  public BookSalesResponse updatePriceByTitle(String title,int price);
  public BookSalesResponse updateCategoryByTitle(String title,String category);
  public BookSalesResponse buyBook(String title ,int count);
  public BookSalesResponse showTop5Sales();
}
