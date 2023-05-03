package com.example.bool_sales.repository;

import com.example.bool_sales.entity.BookSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Repository
public interface BookSalesDao extends JpaRepository<BookSales, String> {

  @Transactional
  @Modifying
  @Query(value = "insert into book_sales  (title, isbn, author, price , inventory , sales, category ) select :title, :isbn, :author, :price , :inventory , :sales, :category ON DUPLICATE KEY UPDATE title= :title, isbn= :isbn, author=:author,price= :price ,inventory= :inventory ,sales= :sales,category= :category", nativeQuery = true)
  public int createOrUpdateA(
          @Param("title") String title,
          @Param("isbn") String isbn,
          @Param("author") String author,
          @Param("price") int price,
          @Param("inventory") int inventory,
          @Param("sales") int sales,
          @Param("category") String category
  );

  @Query(value = "select title,isbn,author,price,inventory from book_sales where category like %:category%", nativeQuery = true)
  public List<Object[]> findBooksByCategory(
          @Param("category") String category

  );
  @Query(value = "select title,isbn,author,price,inventory,sales from book_sales where title = :keyWord or isbn = :keyWord or author = :keyWord", nativeQuery = true)
  public List<Object[]> booksellerFindByTitleOrIsbnOrAuthor(
          @Param("keyWord") String keyWord
  );
  @Query(value = "select title,isbn,author,price from book_sales where title = :keyWord or isbn = :keyWord or author = :keyWord", nativeQuery = true)
  public List<Object[]> consumerFindByTitleOrIsbnOrAuthor(
          @Param("keyWord") String keyWord
  );

  @Transactional
  @Modifying
  @Query(value = "update book_sales set inventory = :newInventory where title = :title",
          nativeQuery = true)
  int updateInventoryByTitle(
          @Param("title") String title,
          @Param("newInventory") int newInventory
  );

  @Transactional
  @Modifying
  @Query(value = "update book_sales set price = :price where title = :title",
          nativeQuery = true)
  int updatePriceByTitle(
          @Param("title") String title,
          @Param("price") int price
  );

  @Transactional
  @Modifying
  @Query(value = "update book_sales set category = :category where title = :title",
          nativeQuery = true)
  int updateCategoryByTitle(
          @Param("title") String title,
          @Param("category") String newCategory
  );

  @Query(value = "select title, isbn, author, price, inventory from book_sales where title = :title",
          nativeQuery = true)
  List<Object[]> findBookInfoByTitle(
          @Param("title") String title
  );

  @Query(value = "select title, isbn, author, price, inventory, category from book_sales where title = :title",
          nativeQuery = true)
  List<Object[]> findBookInfoByTitleReturnCategory(
          @Param("title") String title
  );

  //  SELECT SUM(column_name) FROM table_name;
  @Transactional
  @Modifying
  @Query(value = "update book_sales set sales = sales + :count , inventory = inventory - :count where title = :title and inventory >= :count",nativeQuery = true)
  public int updateSalesAndInventory(
          @Param("title") String  title,
          @Param("count") int count
  );

  @Query(value = "select title, isbn, author, price, :count, (price * :count) as total_price from book_sales where title = :title",nativeQuery = true)
  public List<Object[]> buyBook(
          @Param("title") String title,
          @Param("count") int count
  );

  @Query(value = "select title,isbn,author,price from book_sales order by sales desc limit 5",nativeQuery = true)
  public List<Object[]> showTop5Sales();
}
