//this class stores api data. data should not be stored long term as it could lead to misinformation
package com.mmu.tracker;

import kong.unirest.json.JSONObject;

public class CovidRecord {
    String countryCode, countryExternalName, countryInternalName;
    int caseAll, caseNew, deathAll, deathNew, recoveryAll, recoveryNew;
    //case = confirmed - (death + recovery )
    CovidRecord(JSONObject j){
        
    }
}
