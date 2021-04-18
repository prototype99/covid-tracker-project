//this class stores api data. data should not be stored long term as it could lead to misinformation
package com.mmu.tracker;

public class Record {
    String countryCode, countryExternalName, countryInternalName;
    int caseAll, caseNew, deathAll, deathNew, recoveryAll, recoveryNew;
    //case = confirmed - (death + recovery )
}
