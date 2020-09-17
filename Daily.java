/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 91887
 */
import java.util.*;
public class Daily {
    private String DailyName;
    private String News_Category;
    public String Headlines[];
  
    

    public Daily(String name,String category,String h1,String h2,String h3,String h4,String h5) {
        this.DailyName = name;
        this.News_Category = category;
        this.Headlines = new String[5];
        this.Headlines[0] = h1;
        this.Headlines[1] = h2;
        this.Headlines[2] = h3;
        this.Headlines[3] = h4;
        this.Headlines[4] = h5;
    }

    
    
    public String getName() {
        return this.DailyName;
    }

    public void setName(String name) {
        this.DailyName = name;
    }

    @Override
    public String toString() {
        return  "Daily Name  : " + this.DailyName + '\t' + " Category : " + this.getNews_Category() ;
    }

    public String  print_headlines(){
        return '\n' + "1." + this.Headlines[0] + '\n' + "2." + this.Headlines[1] + '\n' + "3." + this.Headlines[2]
                + '\n' + "4." + this.Headlines[3] + '\n' + "5." + this.Headlines[4];
    }
    
    /**
     * @return the News_Category
     */
    public String getNews_Category() {
        return News_Category;
    }

    /**
     * @param News_Category the News_Category to set
     */
    public void setNews_Category(String News_Category) {
        this.News_Category = News_Category;
    }
    
    
    
}
