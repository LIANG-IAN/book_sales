package com.example.bool_sales.vo;

import com.example.bool_sales.entity.BookSales;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookSalesRequest {


  @JsonProperty("book_sales")
  private BookSales bookSales;

  @JsonProperty("category")
  private String category;

  @JsonProperty("key_word")
  private String keyWord;

  @JsonProperty("title")
  private String title;

  @JsonProperty("count")
  private int count;

  @JsonProperty("count")
  private int price;

  @JsonProperty("count")
  private int newInventory;

  @JsonProperty("identity")
  private boolean identity;

  public BookSalesRequest() {
  }

  public BookSales getBookSales() {
    return bookSales;
  }

  public String getCategory() {
    return category;
  }

  public String getKeyWord() {
    return keyWord;
  }

  public String getTitle() {
    return title;
  }

  public int getCount() {
    return count;
  }

  public int getPrice() {
    return price;
  }

  public int getNewInventory() {
    return newInventory;
  }

  public boolean isIdentity() {
    return identity;
  }
}
