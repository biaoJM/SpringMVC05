package SpringMVC.service;
import SpringMVC.model.*;
import java.util.*;

public interface BookService
{
    public boolean insert(Book book);
    public boolean update(int id,Book book);
    public boolean delete(int id);
    public ArrayList<Book> get();
    public Book get(int id);
}