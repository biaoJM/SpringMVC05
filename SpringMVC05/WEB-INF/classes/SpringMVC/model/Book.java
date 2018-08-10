package SpringMVC.model;
import java.io.Serializable;
public class Book implements Serializable
{
    private static final long serialVersionUID = 8250023367178013439L;
    
    private int id;
    private String name ;
    private Category category;
    private String author;
    private float  price;
    
    public Book(){}
    
    public Book(int id,String name,Category category,String author,float price)
    {
        this.id = id;
        this.name = name;
        this.category = category;
        this.author = author;
        this.price = price;
    }
    // get and set
    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getId()
    {
        return this.id;
    }
    public void setId(int id)
    {
        this.id = id;
    }    
    public Category getCategory()
    {
        return this.category;
    }
    public void setCategory(Category category)
    {
        this.category = category;
    }       
    public String getAuthor()
    {
        return this.author;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }
    public float getPrice()
    {
        return this.price;
    }
    public void setPrice(float price)
    {
        this.price = price;
    }    
}