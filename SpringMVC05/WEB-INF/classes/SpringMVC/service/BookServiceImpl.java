package SpringMVC.service;
import SpringMVC.model.*;

import java.util.*;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
@Service
public class BookServiceImpl implements BookService
{
    private Connection connectionOfOracle;
    private Statement statementOfOracle;
    private static final Log logger = LogFactory.getLog(BookServiceImpl.class);
    
    // constructor
    public BookServiceImpl()
    {
        init();
    }
    
    // initial function
    // connect database
    private void init()
    {
        // database
        try 
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";// localhost是本机地址，可更改为IP地址
            String user = "biaoJM";
            String password = "216077";
            connectionOfOracle = DriverManager.getConnection(url, user, password);// 获取连接
            statementOfOracle = connectionOfOracle.createStatement();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    // insert a book into database
    public boolean insert(Book book)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into book values(");
        sql.append(String.valueOf(book.getId())+",");
        sql.append(book.getName()+",");
        sql.append(String.valueOf(book.getCategory().getId())+",");
        sql.append(book.getCategory().getName()+",");
        sql.append(book.getAuthor()+",");
        sql.append(String.valueOf(book.getPrice()));
        sql.append(")");
        logger.info(sql.toString());
        try
        {
            statementOfOracle.execute(sql.toString());        
        }catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    // update the book represented by param id with param book
    public boolean update(int id,Book book)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("update book set id=NVL("+String.valueOf(book.getId())+",id),"
                    +"name=NVL("+book.getName()+",name),"
                    +"category_id=NVL("+String.valueOf(book.getCategory().getId())+",category_id),"
                    +"category_name=NVL("+book.getCategory().getName()+",category_name),"
                    +"author=NVL("+book.getAuthor()+",author),"
                    +"price=NVL("+String.valueOf(book.getPrice())+",price)"
                    +" where id="+String.valueOf(id)
                    );
        logger.info(sql.toString());
        try
        {
            statementOfOracle.execute(sql.toString());        
        }catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    // delete the book represented by param id
    public boolean delete(int id)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("delete book where ");
        sql.append("id="+String.valueOf(id));
        logger.info(sql.toString());
        try
        {
            statementOfOracle.execute(sql.toString());        
        }catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    // return all books;if there is no books,return null.
    public ArrayList<Book> get()
    {
        ArrayList<Book> bookList = new ArrayList<Book>();
        try
        {
            ResultSet selectResult = statementOfOracle.executeQuery("select * from book");
            while(selectResult.next())
            {
                Book book = new Book(
                            selectResult.getInt("id"),selectResult.getString("name")
                            ,new Category(selectResult.getInt("category_id")
                                ,selectResult.getString("category_name"))
                            ,selectResult.getString("author"),selectResult.getFloat("price")
                            );
                bookList.add(book);
            }
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        bookList.add(new Book(4,"this is not",new Category(2," in")," database.",(float)2.1));
        return bookList;
    }
    
    public Book get(int id)
    {
        Book book=null;
        try
        {
            ResultSet selectResult = statementOfOracle.executeQuery("select * from book where id="+id);
            if(selectResult.next())
            {
                book = new Book(
                            selectResult.getInt("id"),selectResult.getString("name")
                            ,new Category(selectResult.getInt("category_id")
                                ,selectResult.getString("category_name"))
                            ,selectResult.getString("author"),selectResult.getFloat("price")
                            );
            }
        }catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
        return book;
    }
}