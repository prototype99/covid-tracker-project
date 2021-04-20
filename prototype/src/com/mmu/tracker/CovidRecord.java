//this class stores api data. data should not be stored long term as it could lead to misinformation
package com.mmu.tracker;

import kong.unirest.json.JSONObject;

public class CovidRecord {
    String countryCode, countryExternalName, countryInternalName, date;
    int caseAll, caseNew, deathAll, deathNew, recoveryAll, recoveryNew;
    //case = confirmed - (death + recovery )
    CovidRecord(JSONObject j){
        countryCode = j.getString("CountryCode");
        countryExternalName = j.getString("Country");
        countryInternalName = j.getString("Slug");
        date = j.getString("Date");
        deathAll = j.getInt("TotalDeaths");
        deathNew = j.getInt("NewDeaths");
        recoveryAll = j.getInt("TotalRecovered");
        recoveryNew = j.getInt("NewRecovered");
        //we have to calculate these because it is not provided by default
        caseAll = j.getInt("TotalConfirmed") - (deathAll + recoveryAll);
        caseNew = j.getInt("NewConfirmed") - (deathNew + recoveryNew);
    }
}
