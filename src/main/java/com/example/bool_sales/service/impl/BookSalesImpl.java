package com.example.bool_sales.service.impl;

import com.example.bool_sales.constants.RtnCode;
import com.example.bool_sales.entity.BookSales;
import com.example.bool_sales.repository.BookSalesDao;
import com.example.bool_sales.service.ifs.BookSalesService;
import com.example.bool_sales.vo.BookSalesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class BookSalesImpl implements BookSalesService {

  private Logger logger = LoggerFactory.getLogger(getClass()); //slf4j

  @Autowired
  BookSalesDao bookSalesDao;

  @Override
  public BookSalesResponse addOrUpdateBook(BookSales bookSales) {
    // 檢查輸入數值是否符合規定
    if (bookSales == null
            || !StringUtils.hasText(bookSales.getTitle())
            || !StringUtils.hasText(bookSales.getIsbn())
            || !StringUtils.hasText(bookSales.getAuthor())
            || bookSales.getPrice() <= 0
            || bookSales.getInventory() < 0
            || bookSales.getSales() < 0
            || !StringUtils.hasText(bookSales.getCategory())
    ) {
      return new BookSalesResponse(RtnCode.INCORRECT_BOOK_INFO_ERROR.getMessage());
    }
    // 透過SQL語法，PK存在則更新，不存在則新增
    bookSalesDao.createOrUpdateA(bookSales.getTitle(), bookSales.getIsbn(), bookSales.getAuthor(), bookSales.getPrice(), bookSales.getInventory(), bookSales.getSales(), bookSales.getCategory());
    return new BookSalesResponse(RtnCode.ADD_BOOK_SUCCESS.getMessage());

  }

  @Override
  public BookSalesResponse findBooksByCategory(String category) {
    // 檢查輸入數值是否符合規定
    if (!StringUtils.hasText(category)) {
      return new BookSalesResponse(RtnCode.INCORRECT_BOOK_INFO_ERROR.getMessage());
    }
    // 檢查該分類是否有書籍存在
    List<Map<String,Object>> bookSalesList = bookSalesDao.findBooksByCategory(category);
    if (CollectionUtils.isEmpty(bookSalesList)) {
      return new BookSalesResponse(RtnCode.NO_SAME_CATEGORY_ERROR.getMessage());
    }
    // 將相符的書的資訊取出並合成字串
    // 放入集合批次回傳
    List<String> bookList = mapArrayToString(bookSalesList);
    return new BookSalesResponse(bookList, RtnCode.FIND_SUCCESS.getMessage());
  }

  @Override
  public BookSalesResponse findBooksByTitleOrIsbnOrAuthor(String keyWord, boolean identity) {
    // 檢查輸入數值是否符合規定
    if (!StringUtils.hasText(keyWord)) {
      return new BookSalesResponse(RtnCode.INCORRECT_BOOK_INFO_ERROR.getMessage());
    }
    // 創建回傳用集合
    List<String> bookList;
    // 判斷使用者身分，true為書商、false為消費者
    if (identity) {
      List<Map<String,Object>> temp = bookSalesDao.booksellerFindByTitleOrIsbnOrAuthor(keyWord);
      // 判斷是否有相關書籍
      if (CollectionUtils.isEmpty(temp)) {
        return new BookSalesResponse(RtnCode.NO_BOOK_FOUND_ERROR.getMessage());
      }
      // 將相符的書的資訊取出並合成字串
      bookList = mapArrayToString(temp);

    }
    else {
      List<Map<String,Object>> temp = bookSalesDao.consumerFindByTitleOrIsbnOrAuthor(keyWord);
      // 判斷是否有相關書籍
      if (CollectionUtils.isEmpty(temp)) {
        return new BookSalesResponse(RtnCode.NO_BOOK_FOUND_ERROR.getMessage());
      }
      // 將相符的書的資訊取出並合成字串
      bookList = mapArrayToString(temp);
    }
    return new BookSalesResponse(bookList, RtnCode.FIND_SUCCESS.getMessage());
  }

  @Override
  public BookSalesResponse updateInventoryByTitle(String title, int newInventory) {
    // 檢查輸入數值是否符合規定
    if (!StringUtils.hasText(title) || newInventory < 0) {
      return new BookSalesResponse(RtnCode.INCORRECT_BOOK_INFO_ERROR.getMessage());
    }
    bookSalesDao.updateInventoryByTitle(title, newInventory);
    List<Map<String,Object>> temp = bookSalesDao.findBookInfoByTitle(title);
    // 判斷是否有相關書籍
    if (CollectionUtils.isEmpty(temp)) {
      return new BookSalesResponse(RtnCode.NO_BOOK_FOUND_ERROR.getMessage());
    }
    // 將相符的書的資訊取出並合成字串
    List<String> bookList = mapArrayToString(temp);
    return new BookSalesResponse(bookList, RtnCode.FIND_SUCCESS.getMessage());
  }

  @Override
  public BookSalesResponse updatePriceByTitle(String title, int price) {
    // 檢查輸入數值是否符合規定
    if (!StringUtils.hasText(title) || price < 0) {
      return new BookSalesResponse(RtnCode.INCORRECT_BOOK_INFO_ERROR.getMessage());
    }
    bookSalesDao.updatePriceByTitle(title, price);
    List<Map<String,Object>> temp = bookSalesDao.findBookInfoByTitle(title);
    // 判斷是否有相關書籍
    if (CollectionUtils.isEmpty(temp)) {
      return new BookSalesResponse(RtnCode.NO_BOOK_FOUND_ERROR.getMessage());
    }
    // 將相符的書的資訊取出並合成字串
    List<String> bookList = mapArrayToString(temp);
    return new BookSalesResponse(bookList, RtnCode.FIND_SUCCESS.getMessage());
  }

  @Override
  public BookSalesResponse updateCategoryByTitle(String title, String category) {
    // 檢查輸入數值是否符合規定
    if (!StringUtils.hasText(title) || !StringUtils.hasText(category)) {
      return new BookSalesResponse(RtnCode.INCORRECT_BOOK_INFO_ERROR.getMessage());
    }
    bookSalesDao.updateCategoryByTitle(title, category);
    List<Map<String,Object>> temp = bookSalesDao.findBookInfoByTitleReturnCategory(title);
    // 判斷是否有相關書籍
    if (CollectionUtils.isEmpty(temp)) {
      return new BookSalesResponse(RtnCode.NO_BOOK_FOUND_ERROR.getMessage());
    }
    // 將相符的書的資訊取出並合成字串
    List<String> bookList = mapArrayToString(temp);
    return new BookSalesResponse(bookList, RtnCode.FIND_SUCCESS.getMessage());
  }

  @Override
  public BookSalesResponse buyBook(String title ,int count){
    // 檢查輸入數值是否符合規定
    if (!StringUtils.hasText(title) || count < 0) {
      return new BookSalesResponse(RtnCode.INCORRECT_BOOK_INFO_ERROR.getMessage());
    }
    // 更改庫存銷售量
    int flag = bookSalesDao.updateSalesAndInventory(title,count);
    // 判斷庫存是否足夠
    if (flag == 0){
      return new BookSalesResponse(RtnCode.INVENTORY_OVER_LIMIT_ERROR.getMessage());
    }
    // 購買書籍並計算總價
    List<Map<String,Object>> temp = bookSalesDao.buyBook(title,count);
    // 判斷是否有相關書籍
    if (CollectionUtils.isEmpty(temp)) {
      return new BookSalesResponse(RtnCode.NO_BOOK_FOUND_ERROR.getMessage());
    }
    // 將相符的書的資訊取出並合成字串
    List<String> bookList = mapArrayToString(temp);
    return new BookSalesResponse(bookList, RtnCode.FIND_SUCCESS.getMessage());
  }
  @Override
  public BookSalesResponse showTop5Sales(){
    // 取得銷售額前五名的書
    List<Map<String,Object>> temp = bookSalesDao.showTop5Sales();
    // 判斷資料庫中是否有書
    if (CollectionUtils.isEmpty(temp)) {
      return new BookSalesResponse(RtnCode.NO_BOOK_FOUND_ERROR.getMessage());
    }
    // 將相符的書的資訊取出並合成字串
    List<String> bookList = mapArrayToString(temp);
    return new BookSalesResponse(bookList, RtnCode.FIND_SUCCESS.getMessage());
  }

  private List<String> mapArrayToString(List<Map<String,Object>> temp) {
    List<String> bookSalesList = new ArrayList<>();
    for (Map<String, Object> map : temp) {
      Set<String> keySet =map.keySet();
      for (String s : keySet) {
        bookSalesList.add(s+": ");
        bookSalesList.add(map.get(s).toString());
      }
    }
    return bookSalesList;
  }
}
