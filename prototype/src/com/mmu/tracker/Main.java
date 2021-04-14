package com.mmu.tracker;
//import required libraries
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import javax.swing.*;

public class Main {
    //add form items, warnings are suppressed because they are actually needed for successful build
    @SuppressWarnings("unused")
    private JButton goButton;
    private JPanel mainPanel;
    @SuppressWarnings("unused")
    private JTextField searchBar;
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
        //use if documentation is missing
        //print("");
        print("countries");
        /*HttpResponse<JsonNode> response = Unirest.get("https://api.covid19api.com/countries").asJson();
        System.out.println(response.getBody().getArray().getJSONObject(0));*/
    }
    //function to review output. do not start apirequest with a slash!
    static void print(String apiRequest) {
        HttpResponse<JsonNode> response = Unirest.get("https://api.covid19api.com/" + apiRequest).asJson();
        //we must use getbody to get the part we care about
        System.out.println(response.getBody());
    }
}
