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
    // �����ɿ�ܽ����Զ�ע��ķ����࣬���ڲ���database������
    @Autowired(required=true)
    private BookService bookService;
    
    // ����BookService�ķ�����ȡ���е�book���������������󽻸�list_bookҳ��
    // ���� list_book ҳ��
    @RequestMapping(value = "/list_book", method = {RequestMethod.POST,RequestMethod.GET})
    public String listBook(Model model)
    {
        ArrayList<Book> bookList = bookService.get();
        model.addAttribute("bookList",bookList);
        return "list_book";
    }
    
    // ����������Ҫ��������ݵĽ���input_insert_book
    @RequestMapping(value = "/input_insert_book", method = {RequestMethod.POST,RequestMethod.GET})
    public String inputInsertBook(Model model)
    {
        model.addAttribute("book",new Book());
        return "input_insert_book";
    }    
    
    // ���ͻ����ύ��Book������뵽BookService��
    // ���ص�list_bookҳ��
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
    
    // // ����ѡ����Ҫɾ�������ݵĽ���choose_delete_book
    // @RequestMapping(value = "/choose_delete_book", method = {RequestMethod.POST,RequestMethod.GET})
    // public String chooseInputInsertBook(Model model)
    // {
        // ArrayList<Book> bookList = bookService.get();
        // model.addAttribute("bookList",bookList);
        // return "redirect:/choose_delete_book"
    // }    
    
    // ���ݿͻ����ύ��Ҫɾ����Book��id������BookService�ķ�������ɾ��
    // �����Ƿ�ɾ���ɹ���������list_book�����ǻ���ʾ��Ӧ����ʾ��Ϣ
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

    // ����������Ҫ���µ����ݵĽ���input_update_book
    @RequestMapping(value = "/input_update_book/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String inputUpdateBook(Model model,@PathVariable(value="id") int id)
    {
        model.addAttribute("oldBook",bookService.get(id));
        model.addAttribute("id",id);
        model.addAttribute("book",new Book());
        return ("input_update_book");
    }    
    
    // ���ݿͻ����ύ��Ҫ���µľ�Book��id���µ�Book���󣬵���BookService�ķ������и���
    // �����Ƿ�ɾ���ɹ���������list_book�����ǻ���ʾ��Ӧ����ʾ��Ϣ
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