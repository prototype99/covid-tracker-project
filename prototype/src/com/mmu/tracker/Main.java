package com.mmu.tracker;
//import required libraries
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import kong.unirest.json.JSONArray;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class Main {
    static ArrayList<CovidRecord> data;
    private JPanel mainPanel;
    @SuppressWarnings("unused")
    private JComboBox searchBar;
    @SuppressWarnings("unused")
    private JButton refreshButton;
    /*combobox code inspired by https://kodejava.org/how-do-i-set-and-get-the-selected-item-in-jcombobox/
    code for form interactions*/
    public Main() {
        //just a basic listener for now to prove it's possible
        searchBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(searchBar.getSelectedItem());
            }
        });
    }
    //main code block
    public static void main(String[] args) {
        //load and display the form
        JFrame frame = new JFrame("COVIDASH");
        frame.setContentPane(new Main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //equivalent to Unirest.setTimeouts(0, 0); in older unirest-java
        Unirest.config().socketTimeout(0).connectTimeout(0);
        loadData(download("summary").getObject().getJSONArray("Countries"));
    }
    //future function to validate user input
    static boolean isValid(){
        //convert case
        //convert spaces to hyphens
        //delete brackets
        //reject 1 letter and 3 letter input
        //remove ", Republic of China", taiwan is an independant country
        return false;
    }
    //do not start apirequest with a slash!
    static JsonNode download(String apiRequest){
        //retrieve data from online api
        HttpResponse<JsonNode> response = Unirest.get("https://api.covid19api.com/" + apiRequest).asJson();
        //we must use getbody to get the part we care about
        return response.getBody();
    }
    static void loadData(JSONArray j){
        data  = new ArrayList<>();
        //iterate through the countries
        for (int i = 0;i<j.length();i++) {
            //add currently loaded country to temporary list
            data.add(new CovidRecord(j.getJSONObject(i)));
        }
    }
    //test function to review output. do not start apirequest with a slash!
    static void print(String apiRequest) {
        System.out.println(download(apiRequest));
    }
    //if you need help with using the api
    static void help() {
        print("");
    }
}
