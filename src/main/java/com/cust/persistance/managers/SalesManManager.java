/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cust.persistance.managers;

import com.cust.common.ServiceControl;
import com.cust.common.ServicePayload;
import com.cust.common.SessionInfo;
import com.cust.domain.vo.ElegantSalesMan;
import com.cust.domain.vo.ElegantUser;
import com.cust.domain.webservice.SalesManWebService;
import com.cust.domain.webservice.UserWebService;
import com.cust.persistance.PersistanceManager;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Inderjit
 */
public class SalesManManager {

    SalesManWebService salesManWebService = null;
    UserWebService userWebService = null;
    ServiceControl serviceControl = null;
    SessionInfo sessionInfo = null;

    public SalesManManager() {
        serviceControl = PersistanceManager.getInstance().getServiceControl();
        sessionInfo = PersistanceManager.getInstance().getSessionInfo();

    }

    public boolean deleteSalesMan(ElegantSalesMan salesMan) throws Exception {
        boolean deleteDone = true;
        ArrayList<ElegantSalesMan> elegantSalesManList = new ArrayList<ElegantSalesMan>();
        elegantSalesManList.add(salesMan);
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.salesmanservice")[0])) {
                salesManWebService = PersistanceManager.getInstance().getSalesManWebService();
                deleteDone = (Boolean) salesManWebService.deleteSalesManList(serviceControl, elegantSalesManList).getResponseValue().get(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return deleteDone;
    }

    public ArrayList<ElegantSalesMan> saveOrUpdateSalesMan(ArrayList<ElegantSalesMan> salesManList) throws Exception {
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.salesmanservice")[0])) {
                salesManWebService = PersistanceManager.getInstance().getSalesManWebService();
                ServicePayload servicePayload = salesManWebService.saveSalesManList(serviceControl, salesManList);
                salesManList = (ArrayList<ElegantSalesMan>) servicePayload.getResponseValue().get(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return salesManList;
    }

    public ArrayList<ElegantSalesMan> getAllSalesMans(ElegantUser user) throws Exception {
        ArrayList<ElegantSalesMan> salesManList = null;
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.salesmanservice")[0])) {
                salesManWebService = PersistanceManager.getInstance().getSalesManWebService();
                salesManList = (ArrayList<ElegantSalesMan>) salesManWebService.getAllSalesMan(serviceControl, user.getCompID()).getResponseValue().get(0);
            }
//            if (salesManList != null) {
//                Collections.sort(salesManList);
//            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return salesManList;
    }

    /**
     * @return the serviceControl
     */
    public ServiceControl getServiceControl() {
        return serviceControl;
    }

    /**
     * @param serviceControl the serviceControl to set
     */
    public void setServiceControl(ServiceControl serviceControl) {
        this.serviceControl = serviceControl;
    }

    public ArrayList<ElegantUser> getAllUsers() throws Exception {
        ArrayList<ElegantUser> managersList = null;
        userWebService = PersistanceManager.getInstance().getUserWebService();
        try {
            managersList = (ArrayList<ElegantUser>) userWebService.getAllUsers(serviceControl, PersistanceManager.getInstance().getElegantUser().getCompID()).getResponseValue().get(0);;
            if (managersList != null) {
                Collections.sort(managersList);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return managersList;
    }

}
