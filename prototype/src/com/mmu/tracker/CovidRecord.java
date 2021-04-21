/*this class stores api data. data should not be stored long term as it could lead to
misinformation*/
package com.mmu.tracker;
public class CovidRecord {
    String countryCode, countryExternalName, countryInternalName, date;
    int caseAll, caseNew, deathAll, deathNew, recoveryAll, recoveryNew;
    //case = confirmed - (death + recovery )
    CovidRecord(String countryCode, String countryExternalName, String countryInternalName,
                String date, int caseAll, int caseNew, int deathAll, int deathNew,
                int recoveryAll, int recoveryNew){
        this.countryCode = countryCode;
        this.countryExternalName = countryExternalName;
        this.countryInternalName = countryInternalName;
        this.date = date;
        this.deathAll = deathAll;
        this.deathNew = deathNew;
        this.recoveryAll = recoveryAll;
        this.recoveryNew = recoveryNew;
        this.caseAll = caseAll;
        this.caseNew = caseNew;
    }
}
