package com.mmu.tracker;
//import required libraries
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import javax.swing.*;
import java.util.ArrayList;
public class Main {
    static ArrayList<CovidRecord> data;
    private JPanel mainPanel;
    @SuppressWarnings("unused")
    private SearchBar<String> searchBar;
    @SuppressWarnings("unused")
    private JButton refreshButton;
    @SuppressWarnings("unused")
    private JLabel lblRecoveryAll;
    @SuppressWarnings("unused")
    private JLabel lblRecoveryNew;
    @SuppressWarnings("unused")
    private JLabel lblCaseAll;
    @SuppressWarnings("unused")
    private JLabel lblCaseNew;
    @SuppressWarnings("unused")
    private JLabel lblDeathAll;
    @SuppressWarnings("unused")
    private JLabel lblDeathNew;

    /*combobox code inspired by:
    https://kodejava.org/how-do-i-set-and-get-the-selected-item-in-jcombobox/*/
    public Main() {
        //equivalent to Unirest.setTimeouts(0, 0); in older unirest-java
        Unirest.config().socketTimeout(0).connectTimeout(0);
        //we load the data here to avoid hell
        loadData(searchBar);
        //query the data
        searchBar.addActionListener(actionEvent -> loadCountry(
                searchBar.getSelectedItem().toString(),
                lblRecoveryAll,
                lblRecoveryNew,
                lblCaseAll,
                lblCaseNew, lblDeathAll,
                lblDeathNew
        ));
        //refresh data in case it's too old
        refreshButton.addActionListener(actionEvent -> loadData(searchBar));
    }
    //main code block
    public static void main(String[] args) {
        //load and display the form
        JFrame frame = new JFrame("COVIDASH");
        frame.setContentPane(new Main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    //future function to validate user input
    static boolean isValid(){
        //convert case
        //convert spaces to hyphens
        //delete brackets
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
    static void loadData(JComboBox<String> searchBar){
        //make sure variable is accessible
        JSONArray top;
        try {
            //get data
            top = download("summary").getObject().getJSONArray("Countries");
        } catch (JSONException | NullPointerException e) {
            System.out.println("json parsing failed, is data correct?\ntry clicking refresh");
            top = new JSONArray();
        }
        //initialise array
        data  = new ArrayList<>();
        //iterate through the countries
        for (int i = 0;i<top.length();i++) {
            //retrieve an individual country
            JSONObject j = top.getJSONObject(i);
            //we need these stored in memory because they're used twice
            String countryCode = j.getString("CountryCode");
            String countryExternalName = j.getString("Country");
            String countryInternalName = j.getString("Slug");
            //we have to store these for calculating the case variables
            int deathAll = j.getInt("TotalDeaths");
            int deathNew = j.getInt("NewDeaths");
            int recoveryAll = j.getInt("TotalRecovered");
            int recoveryNew = j.getInt("NewRecovered");
            //add currently loaded country to list
            data.add(new CovidRecord(countryCode, countryExternalName, countryInternalName,
                    j.getString("Date"), deathAll, deathNew, recoveryAll, recoveryNew,
                    (j.getInt("TotalConfirmed") - (deathAll + recoveryAll)), (j.getInt(
                            "NewConfirmed") - (deathNew + recoveryNew))));
            searchBar.addItem(countryCode);
            searchBar.addItem(countryExternalName);
            searchBar.addItem(countryInternalName);
        }
    }
    static void loadCountry(String s, JLabel lblRecoveryAll, JLabel lblRecoveryNew, JLabel lblCaseAll, JLabel lblCaseNew, JLabel lblDeathAll, JLabel lblDeathNew){
        if(s.length() < 2 || s.length() == 3){
            lblRecoveryAll.setText("input");
            lblCaseAll.setText("is");
            lblDeathAll.setText("invalid");
            lblRecoveryNew.setText("please");
            lblCaseNew.setText("try");
            lblDeathNew.setText("again");
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
