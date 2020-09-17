/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 91887
 */
// Header files


import java.io.*;
import java.util.*;
import java.util.HashSet;
import java.util.Set;
import java.lang.Math;
import java.util.LinkedHashSet;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.io.FileReader;
import com.opencsv.CSVReader;

        

public class samplemain {
    @SuppressWarnings("resource")  
    public static void RecommendHeadlines(String final_class_label,ArrayList<Client> user_instances,String daily){
        System.out.println("\n Add the following Dailies  to your feed");
        for(int i=0;i<user_instances.size();i++){
            if(user_instances.get(i).getClass_Label().equals(final_class_label) & !(user_instances.get(i).D.getName().trim().equals(daily))){
                System.out.println("\n " + user_instances.get(i).D.getName());
                System.out.println(user_instances.get(i).D.print_headlines());
            }
        }
    }
    public static double findDistance(String s1,String s2){
        
        double distance;
        if(s1.trim().equals(s2))
            distance = 0;
        else
            distance=1;
        return distance;
        
    }
    public static void KNNeighbors(ArrayList<Client> user_instances,String userid,String daily,String category,String feedType,String[] class_labels){
        
        double temp_distance;
        int min_index_user;
        int temp;
        String final_class_label;
        Double[] distance_temp = new Double[user_instances.size()];
        ArrayList<Double> Eu_distances = new ArrayList<Double>();
        ArrayList<Double> Eu_distances_clone = new ArrayList<Double>();
        
        
        for(int i=0;i<user_instances.size();i++){
            temp_distance =Math.sqrt(Math.pow(findDistance(user_instances.get(i).D.getName(),daily),2) + 
                    Math.pow(findDistance(user_instances.get(i).D.getNews_Category(),category),2) + 
                    Math.pow(findDistance(user_instances.get(i).getFeed_Type(),feedType),2) );
            System.out.println(temp_distance);
            Eu_distances.add(temp_distance);
            Eu_distances_clone.add(temp_distance);
        }
        
         Collections.sort(Eu_distances);
         System.out.println(Eu_distances);
         
         // Value of K is chosen to be 3
         List<Double> shortestDistances  = Eu_distances.subList(0,3);
         
         HashMap<String,Integer> class_count = new HashMap<String,Integer>();
         // Initializing counts in the HashMap of class labels
         for(String label : class_labels){
             class_count.put(label,0);
         }
         System.out.println(shortestDistances);
         for(double distance : shortestDistances){
             int index = Eu_distances_clone.indexOf(distance);
             String cLabel = user_instances.get(index).getClass_Label();
             // Storing count of class label 
             int count = class_count.containsKey(cLabel) ? class_count.get(cLabel) : 0;
             class_count.put(cLabel, count+1);
         }
         
        
        String maxKey=null;
        int maxValue = Integer.MIN_VALUE; 
        for(Map.Entry<String,Integer> entry : class_count.entrySet()) {
            if(entry.getValue() > maxValue) {
             maxValue = entry.getValue();
             maxKey = entry.getKey();
        }
    }
        final_class_label = maxKey;
        
         System.out.println("\n The predicted class label is : " + final_class_label);
         
         RecommendHeadlines(final_class_label,user_instances,daily);
        
    }
    public static void main(String[] args) throws Exception{
        
        boolean c;
        int len_dailies,len_users;
        int index=0;
        
      
        String[]  Dailies_list,Categories_list,Feed_list,Class_labels ;
        ArrayList<Daily> daily_instances = new ArrayList<Daily>();
        ArrayList<Client> user_instances = new ArrayList<Client>();
        CSVReader reader_daily = new CSVReader(new FileReader("D:/IV Semester/Java/Package/Daily File.csv"));
        CSVReader reader_user = new CSVReader(new FileReader("D:/IV Semester/Java/Package/User File.csv"));
       
        
        
       
        // Verifying the reader
        c = reader_daily.verifyReader();
        String[] record;
        record = reader_daily.readNext();
        while((record = reader_daily.readNext())!=null){
            Daily d_temp = new Daily(record[0],record[1],record[2],record[3],record[4],record[5],record[6]);
            daily_instances.add(d_temp);
        }
        
        
       
        c = reader_user.verifyReader();
        String[] next_record;
        next_record = reader_user.readNext();
        while((next_record = reader_user.readNext())!=null){
            Daily temp = daily_instances.get(index);
            Client c_temp = new Client(next_record[0],temp,next_record[3],next_record[4]);
             user_instances.add(c_temp);
             index++;
        }
       
      //Store the number of users and Dailies in the Database  
      len_dailies = daily_instances.size();
      len_users = user_instances.size();
      
      // Storing Dailies in the Dailies List 
      Dailies_list = new String[len_dailies];
      Categories_list = new String[len_dailies];
      Feed_list = new String[len_dailies];
      Class_labels = new String[len_users];
      int flag=0,flag2=0,flag3=0;
      for(int i=0;i<len_dailies;i++){
            flag =0;
            for(int j=0;j<len_dailies;j++)
            {
                
                if(daily_instances.get(i).getName().trim().equals(Dailies_list[j])){
                    flag=1;
                    break;
                }
                
            }
            if(flag==0)
              Dailies_list[i] = daily_instances.get(i).getName();  
      }
     
        
        List<String> TempList = new ArrayList<String>();
        for(String s : Dailies_list){
            if(s!=null)
                TempList.add(s);
        }
        Dailies_list = TempList.toArray(new String[TempList.size()]);
        
        // Storing the list of news categories and Feed Types
        for(int i=0;i<len_dailies;i++){
            Categories_list[i] = daily_instances.get(i).getNews_Category();
        }        

        for(int i=0;i<len_users;i++){
            Feed_list[i] = user_instances.get(i).getFeed_Type();
        }
        for(int i=0;i<len_users;i++){
            Class_labels[i] = user_instances.get(i).getClass_Label();
        }
        
        // Removing Duplicate Feed types and Categories
        Feed_list = Arrays.stream(Feed_list).distinct().toArray(String[]::new);
        Categories_list = Arrays.stream(Categories_list).distinct().toArray(String[]::new);
        Class_labels = Arrays.stream(Class_labels).distinct().toArray(String[]::new);
        //
        Scanner myscanner = new Scanner(System.in);
        String daily,category,feedType,userid;
        int temp1,temp2,temp3;
        System.out.println("\n Choose the Daily : (Enter only code numbers )");
        for(int i=0;i<Dailies_list.length;i++){
            System.out.println(i + " " + Dailies_list[i]);
        }
        temp1 = myscanner.nextInt();
        System.out.println("\n Choose the Category : ");
        for(int i=0;i<Categories_list.length;i++){
            System.out.println(i + " " + Categories_list[i]);
        }
        temp2 = myscanner.nextInt();
        System.out.println("\n Choose your required feed type : ");
        for(int i=0;i<Feed_list.length;i++){
            System.out.println(i + " " + Feed_list[i]);
        }
        temp3 = myscanner.nextInt();
        
        daily = Dailies_list[temp1];
        category = Categories_list[temp2];
        feedType = Feed_list[temp3];
        temp3 = ThreadLocalRandom.current().nextInt(10,30);
        userid = Integer.toString(temp3);
        System.out.println(userid);
        System.out.println(daily + category + feedType);
        
        // K Nearest Neighbour function call to recommend headlines to the current user
        KNNeighbors(user_instances,userid,daily,category,feedType,Class_labels);
        
 
        
        
       
       
       
       
       
       
       
      
    
      
      
    }
}
