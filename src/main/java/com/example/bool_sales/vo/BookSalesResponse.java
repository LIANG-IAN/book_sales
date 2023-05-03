package com.example.bool_sales.vo;

import java.util.List;

public class BookSalesResponse {

  private String message;

  private List<String> list;

  public BookSalesResponse() {
  }

  public BookSalesResponse(String message) {
    this.message = message;
  }

  public BookSalesResponse(List<String> list, String message) {
    this.message = message;
    this.list = list;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<String> getList() {
    return list;
  }

  public void setList(List<String> list) {
    this.list = list;
  }
}
