/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 91887
 */

public class Client {
    private String userId;
    public Daily D;
    private String feed_Type;
    private String class_Label;
    

    public Client(String userId, Daily d1,String feed_Type, String class_Label) {
        this.userId = userId;
        this.D = d1;
        this.feed_Type = feed_Type;
        this.class_Label = class_Label;
       
    }
    
    /**
     * @return the userId
     */
    
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }


    /**
     * @return the feed_Type
     */
    public String getFeed_Type() {
        return feed_Type;
    }

    /**
     * @param feed_Type the feed_Type to set
     */
    public void setFeed_Type(String feed_Type) {
        this.feed_Type = feed_Type;
    }

    /**
     * @return the class_Label
     */
    public String getClass_Label() {
        return class_Label;
    }

    /**
     * @param class_Label the class_Label to set
     */
    public void setClass_Label(String class_Label) {
        this.class_Label = class_Label;
    }


    @Override
    public String toString() {
        return  "User ID : " + userId + "\t " +  " Daily Name :  " + this.D.getName() + "\t " +  " Daily Category : " + this.D.getNews_Category() + "\t " + " Required  Feed Type : " + feed_Type +"\t " +  ", Class Label : " + class_Label ;
    }
       
}


