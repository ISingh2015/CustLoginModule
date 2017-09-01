package com.cust.persistance.managers;

import com.cust.common.ServiceControl;
import com.cust.common.SessionInfo;
import com.cust.domain.vo.ElegantCountry;
import com.cust.domain.webservice.BuySellWebService;
import com.cust.persistance.PersistanceManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Inderjit
 */
public class EmailManager {

    BuySellWebService buySellWebService;
    ServiceControl serviceControl = null;
    SessionInfo sessionInfo = null;


    public EmailManager() {
        serviceControl = PersistanceManager.getInstance().getServiceControl();
        sessionInfo = PersistanceManager.getInstance().getSessionInfo();

    }

    public ArrayList<ElegantCountry> getGlobalEmailList() throws Exception {
        ArrayList<ElegantCountry> countryList = null;
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.countryservice")[0])) {
                buySellWebService = PersistanceManager.getInstance().getBuysellWebService();
                List<Object> tempGlobalEmailList = buySellWebService.getGlobalMailingList(serviceControl).getResponseValue();
                countryList = (ArrayList<ElegantCountry>) tempGlobalEmailList.get(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return countryList;
    }


}
