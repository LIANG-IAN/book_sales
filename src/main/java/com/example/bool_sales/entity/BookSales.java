package com.example.bool_sales.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book_sales")
public class BookSales {
  @Id
  @Column(name = "title")
  private String title;

  @Column(name = "isbn")
  private String isbn;

  @Column(name = "author")
  private String author;

  @Column(name = "price")
  private int price;

  @Column(name = "inventory")
  private String inventory;

  @Column(name = "sales")
  private int sales;

  @Column(name = "category")
  private String category;


  public BookSales() {
  }

  public BookSales(String title, String isbn, String author, int price, String inventory, int sales, String category) {
    this.title = title;
    this.isbn = isbn;
    this.author = author;
    this.price = price;
    this.inventory = inventory;
    this.sales = sales;
    this.category = category;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getInventory() {
    return inventory;
  }

  public void setInventory(String inventory) {
    this.inventory = inventory;
  }

  public int getSales() {
    return sales;
  }

  public void setSales(int sales) {
    this.sales = sales;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String toString() {
    return "BookSales{title = " + title + ", isbn = " + isbn + ", author = " + author + ", price = " + price + ", inventory = " + inventory + ", sales = " + sales + ", category = " + category + "}";
  }
}
