/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 91887
 */
public class HeadlinesList {
    private String headline;

    /**
     * @return the headline
     */
    
    
    public HeadlinesList(String headline) {
        this.headline = headline;
    }

    @Override
    public String toString() {
        return "HeadlinesList{" + "headline=" + headline + '}';
    }
    

    public String getHeadline() {
        return headline;
    }

    /**
     * @param headline the headline to set
     */
    public void setHeadline(String headline) {
        this.headline = headline;
    }
    
    
}
