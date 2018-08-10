package SpringMVC.model;

public class Category
{
    private String name;
    private int  id;
    
    public Category(){}
    
    public Category(int id,String name)
    {
        this.id = id;
        this.name = name;
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
}