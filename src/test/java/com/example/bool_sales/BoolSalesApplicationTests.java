package com.example.bool_sales;

import com.example.bool_sales.constants.RtnCode;
import com.example.bool_sales.entity.BookSales;
import com.example.bool_sales.service.ifs.BookSalesService;
import com.example.bool_sales.service.impl.BookSalesImpl;
import com.example.bool_sales.vo.BookSalesResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class BoolSalesApplicationTests {

  @Autowired
  BookSalesImpl bookSalesService;

  // 新增或更新書本：成功
  @Test
  public void addOrUpdateBook1() {
    BookSales bookSales = new BookSales("神鵰俠侶", "JQ586354", "金庸", 500, 150, 480, "武俠");
    BookSalesResponse bookSalesResponse = bookSalesService.addOrUpdateBook(bookSales);
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.ADD_BOOK_SUCCESS.getMessage()), "");
  }

  // 新增或更新書本：輸入數值不符合規定
  @Test
  public void addOrUpdateBook() {
    BookSales bookSales = new BookSales("西遊記", "JQ586354", "羅貫中", 500, 150, -480, "武俠");
    BookSalesResponse bookSalesResponse = bookSalesService.addOrUpdateBook(bookSales);
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.INCORRECT_BOOK_INFO_ERROR.getMessage()), "");
  }

  // 分類搜尋書本：成功
  @Test
  public void findBooksByCategory1() {
    BookSalesResponse bookSalesResponse = bookSalesService.findBooksByCategory("武俠");
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.FIND_SUCCESS.getMessage()), "");
  }

  // 分類搜尋書本：輸入值為空
  @Test
  public void findBooksByCategory2() {
    BookSalesResponse bookSalesResponse = bookSalesService.findBooksByCategory(null);
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.INCORRECT_BOOK_INFO_ERROR.getMessage()), "");
  }

  // 分類搜尋書本：該分類無書本
  @Test
  public void findBooksByCategory3() {
    BookSalesResponse bookSalesResponse = bookSalesService.findBooksByCategory("懸疑");
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.NO_SAME_CATEGORY_ERROR.getMessage()), "");
  }

  // 書名、ISBN、作者搜尋書本，並依身分別返回兩種結果：成功
  @Test
  public void findBooksByTitleOrIsbnOrAuthor1() {
    BookSalesResponse bookSalesResponse = bookSalesService.findBooksByTitleOrIsbnOrAuthor("金庸", true);
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.INCORRECT_BOOK_INFO_ERROR.getMessage()), "");
  }

  // 書名、ISBN、作者搜尋書本，並依身分別返回兩種結果：輸入書本不存在
  @Test
  public void findBooksByTitleOrIsbnOrAuthor2() {
    BookSalesResponse bookSalesResponse = bookSalesService.findBooksByTitleOrIsbnOrAuthor("", true);
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.NO_BOOK_FOUND_ERROR.getMessage()), "");
  }

  // 書名、ISBN、作者搜尋書本，並依身分別返回兩種結果：成功
  @Test
  public void findBooksByTitleOrIsbnOrAuthor3() {
    BookSalesResponse bookSalesResponse = bookSalesService.findBooksByTitleOrIsbnOrAuthor("老殘遊記", true);
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.FIND_SUCCESS.getMessage()), "");
  }


  // 更新書本分類：成功
  @Test
  public void updateInventoryByTitle1() {
    BookSalesResponse bookSalesResponse = bookSalesService.updateInventoryByTitle("天龍八部", 666);
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.FIND_SUCCESS.getMessage()), "");
  }

  // 更新書本分類：輸入值為空
  @Test
  public void updateInventoryByTitle2() {
    BookSalesResponse bookSalesResponse = bookSalesService.updateInventoryByTitle(null, 666);
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.INCORRECT_BOOK_INFO_ERROR.getMessage()), "");
  }

  // 更新書本分類：無該書本
  @Test
  public void updateInventoryByTitle3() {
    BookSalesResponse bookSalesResponse = bookSalesService.updateInventoryByTitle("老殘遊記", 666);
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.NO_BOOK_FOUND_ERROR.getMessage()), "");
  }

  // 更新書本分類：成功
  @Test
  public void updatePriceByTitle1() {
    BookSalesResponse bookSalesResponse = bookSalesService.updatePriceByTitle("天龍八部", 399);
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.FIND_SUCCESS.getMessage()), "");
  }

  // 更新書本分類：輸入值為空
  @Test
  public void updatePriceByTitle2() {
    BookSalesResponse bookSalesResponse = bookSalesService.updatePriceByTitle(null, 666);
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.INCORRECT_BOOK_INFO_ERROR.getMessage()), "");
  }

  // 更新書本分類：無該書本
  @Test
  public void updatePriceByTitle3() {
    BookSalesResponse bookSalesResponse = bookSalesService.updatePriceByTitle("老殘遊記", 666);
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.NO_BOOK_FOUND_ERROR.getMessage()), "");
  }

  // 更新書本分類：成功
  @Test
  public void updateCategoryByTitle1() {
    BookSalesResponse bookSalesResponse = bookSalesService.updateCategoryByTitle("天龍八部", "科幻、穿越、戀愛");
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.FIND_SUCCESS.getMessage()), "");
  }

  // 更新書本分類：輸入值為空
  @Test
  public void updateCategoryByTitle2() {
    BookSalesResponse bookSalesResponse = bookSalesService.updateCategoryByTitle(null, "科幻、穿越、戀愛");
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.INCORRECT_BOOK_INFO_ERROR.getMessage()), "");
  }

  // 更新書本分類：無該書本
  @Test
  public void updateCategoryByTitle3() {
    BookSalesResponse bookSalesResponse = bookSalesService.updateCategoryByTitle("老殘遊記", "科幻、穿越、戀愛");
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.NO_BOOK_FOUND_ERROR.getMessage()), "");
  }

  //購買書本：成功
  @Test
  public void buyBook1() {
    BookSalesResponse bookSalesResponse = bookSalesService.buyBook("天龍八部", 30);
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.FIND_SUCCESS.getMessage()), "");
  }

  //購買書本：輸入數值不符合規定
  @Test
  public void buyBook2() {
    BookSalesResponse bookSalesResponse = bookSalesService.buyBook("天龍八部", -30);
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.INCORRECT_BOOK_INFO_ERROR.getMessage()), "");
  }

  //購買書本：庫存不夠
  @Test
  public void buyBook3() {
    BookSalesResponse bookSalesResponse = bookSalesService.buyBook("天龍八部", 30000);
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.INVENTORY_OVER_LIMIT_ERROR.getMessage()), "");
  }

  //購買書本：無此書本
  @Test
  public void buyBook4() {
    BookSalesResponse bookSalesResponse = bookSalesService.buyBook("射鵰英雄傳", 15);
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.NO_BOOK_FOUND_ERROR.getMessage()), "");
  }

  // 顯示銷售前五名書本：成功
  @Test
  public void showTop5Sales1() {
    BookSalesResponse bookSalesResponse = bookSalesService.showTop5Sales();
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.FIND_SUCCESS.getMessage()), "");
  }


  // 顯示銷售前五名書本：資料庫中無任何書
  @Test
  public void showTop5Sales2() {
    BookSalesResponse bookSalesResponse = bookSalesService.showTop5Sales();
    Assert.isTrue(bookSalesResponse.getMessage().equals(RtnCode.NO_BOOK_FOUND_ERROR.getMessage()), "");
  }
}


