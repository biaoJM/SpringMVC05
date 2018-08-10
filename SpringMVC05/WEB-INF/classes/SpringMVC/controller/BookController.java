package SpringMVC.controller;
import SpringMVC.model.*;
import SpringMVC.service.*;

import java.io.IOException;
import java.io.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// import org.apache.commons.logging.Log;
// import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController
{
    // 这是由框架进行自动注入的服务类，用于操作database的数据
    @Autowired(required=true)
    private BookService bookService;
    
    // 调用BookService的方法获取所有的book，并将这个数组对象交给list_book页面
    // 返回 list_book 页面
    @RequestMapping(value = "/list_book", method = {RequestMethod.POST,RequestMethod.GET})
    public String listBook(Model model)
    {
        ArrayList<Book> bookList = bookService.get();
        model.addAttribute("bookList",bookList);
        return "list_book";
    }
    
    // 返回输入想要插入的数据的界面input_insert_book
    @RequestMapping(value = "/input_insert_book", method = {RequestMethod.POST,RequestMethod.GET})
    public String inputInsertBook(Model model)
    {
        model.addAttribute("book",new Book());
        return "input_insert_book";
    }    
    
    // 将客户端提交的Book对象插入到BookService中
    // 返回到list_book页面
    @RequestMapping(value = "/insert_book", method = {RequestMethod.POST,RequestMethod.GET})
    public String insertBook(Model model,@ModelAttribute Book book)
    {
        if(bookService.insert(book))
        {
            model.addAttribute("message",(book.getId()+" was successfully inserted!"));
        }
        else
        {
            model.addAttribute("message",(book.getId()+" cannot be inserted!"));
        }
        return "forward:/list_book";
    }
    
    // // 返回选择想要删除的数据的界面choose_delete_book
    // @RequestMapping(value = "/choose_delete_book", method = {RequestMethod.POST,RequestMethod.GET})
    // public String chooseInputInsertBook(Model model)
    // {
        // ArrayList<Book> bookList = bookService.get();
        // model.addAttribute("bookList",bookList);
        // return "redirect:/choose_delete_book"
    // }    
    
    // 根据客户端提交想要删除的Book的id，调用BookService的方法进行删除
    // 无论是否删除成功都将返回list_book，但是会显示响应的提示信息
    @RequestMapping(value = "/delete_book/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String deleteBook(Model model,@PathVariable(value="id") int id)
    {
        if(bookService.delete(id))
        {
            model.addAttribute("message",(id+" was successfully deleted!"));
        }
        else
        {
            model.addAttribute("message",(id+" failed to be deleted!"));
        }
        return "forward:/list_book";
    }

    // 返回输入想要更新的数据的界面input_update_book
    @RequestMapping(value = "/input_update_book/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String inputUpdateBook(Model model,@PathVariable(value="id") int id)
    {
        model.addAttribute("oldBook",bookService.get(id));
        model.addAttribute("id",id);
        model.addAttribute("book",new Book());
        return ("input_update_book");
    }    
    
    // 根据客户端提交想要更新的旧Book的id和新的Book对象，调用BookService的方法进行更新
    // 无论是否删除成功都将返回list_book，但是会显示响应的提示信息
    @RequestMapping(value = "/update_book/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String updateBook(Model model,@ModelAttribute Book book,@PathVariable(value="id") int id)
    {
        if(bookService.update(id,book))
        {
            model.addAttribute("message",(id+" was successfully updated!"));
        }
        else
        {
            model.addAttribute("message",(id+" failed to be updated!"));
        }
        return "forward:/list_book";
    }
}// class