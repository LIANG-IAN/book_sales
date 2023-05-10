package com.example.bool_sales.controller;

import com.example.bool_sales.service.ifs.BookSalesService;
import com.example.bool_sales.vo.BookSalesRequest;
import com.example.bool_sales.vo.BookSalesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class BookSalesController {


  @Autowired
  BookSalesService bookSalesService;

  @PostMapping("add_or_update_book")
  public BookSalesResponse addOrUpdateBook(@RequestBody BookSalesRequest bookSalesRequest) {
    return bookSalesService.addOrUpdateBook(bookSalesRequest.getBookSales());
  }

  @GetMapping("find_books_by_category")
  public BookSalesResponse findBooksByCategory(@RequestBody BookSalesRequest bookSalesRequest) {
    return bookSalesService.findBooksByCategory(bookSalesRequest.getCategory());
  }

  @GetMapping("find_books_by_title_or_isbn_or_author")
  public BookSalesResponse findBooksByTitleOrIsbnOrAuthor(@RequestBody BookSalesRequest bookSalesRequest) {
    return bookSalesService.findBooksByTitleOrIsbnOrAuthor(bookSalesRequest.getKeyWord(), bookSalesRequest.isIdentity());
  }

  @PostMapping("update_inventory_by_title")
  public BookSalesResponse updateInventoryByTitle(@RequestBody BookSalesRequest bookSalesRequest) {
    return bookSalesService.updateInventoryByTitle(bookSalesRequest.getTitle(), bookSalesRequest.getNewInventory());
  }

  @PostMapping("update_price_by_title")
  public BookSalesResponse updatePriceByTitle(@RequestBody BookSalesRequest bookSalesRequest) {
    return bookSalesService.updatePriceByTitle(bookSalesRequest.getTitle(), bookSalesRequest.getPrice());
  }

  @PostMapping("update_category_by_title")
  public BookSalesResponse updateCategoryByTitle(@RequestBody BookSalesRequest bookSalesRequest) {
    return bookSalesService.updateCategoryByTitle(bookSalesRequest.getTitle(), bookSalesRequest.getCategory());
  }

  @PostMapping("buy_book")
  public BookSalesResponse buyBook(@RequestBody BookSalesRequest bookSalesRequest) {
    return bookSalesService.buyBook(bookSalesRequest.getTitle(), bookSalesRequest.getCount());
  }

  @GetMapping("show_top_5_sales")
  public BookSalesResponse showTop5Sales() {
    return bookSalesService.showTop5Sales();
  }

}
