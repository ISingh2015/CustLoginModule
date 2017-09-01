package com.cust.persistance.managers;

import com.cust.common.ServiceControl;
import com.cust.common.SessionInfo;
import com.cust.domain.vo.ElegantCountry;
import com.cust.domain.webservice.CountryWebService;
import com.cust.persistance.PersistanceManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Inderjit
 */
public class CountryManager {

    CountryWebService countryWebService;
    ServiceControl serviceControl = null;
    SessionInfo sessionInfo = null;


    public CountryManager() {
        serviceControl = PersistanceManager.getInstance().getServiceControl();
        sessionInfo = PersistanceManager.getInstance().getSessionInfo();

    }

    public ArrayList<ElegantCountry> getCountryList() throws Exception {
        ArrayList<ElegantCountry> countryList = null;
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.countryservice")[0])) {
                countryWebService = PersistanceManager.getInstance().getCountryWebService();
                List<Object> tempCountryList = countryWebService.getAllCountries(serviceControl).getResponseValue();
                countryList = (ArrayList<ElegantCountry>) tempCountryList.get(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return countryList;
    }

    public boolean saveOrUpdateCountry(ArrayList<ElegantCountry> countryList) throws Exception {
        boolean updateDone = true;
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.countryservice")[0])) {
                countryWebService = PersistanceManager.getInstance().getCountryWebService();
                countryWebService.saveCountryList(serviceControl, countryList);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return updateDone;
    }

    public boolean deleteCountry(ElegantCountry country) throws Exception {
        boolean deleteDone = true;
        ArrayList<ElegantCountry> countryList = new ArrayList<ElegantCountry>();
        countryList.add(country);
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.countryservice")[0])) {
                countryWebService = PersistanceManager.getInstance().getCountryWebService();
                deleteDone = (Boolean) countryWebService.deleteCountryList(serviceControl, countryList).getResponseValue().get(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return deleteDone;
    }


}
