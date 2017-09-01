/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cust.persistance.managers;

import com.cust.common.FilterCriteria;
import com.cust.common.QueryCriteria;
import com.cust.common.ServiceControl;
import com.cust.common.SessionInfo;
import com.cust.common.SortCriteria;
import com.cust.domain.vo.ElegantBuySell;
import com.cust.domain.vo.ElegantBuySellConsolidation;
import com.cust.domain.vo.ElegantMailList;
import com.cust.domain.vo.ElegantMarketMail;
import com.cust.domain.vo.ElegantUser;
import com.cust.domain.webservice.BuySellWebService;
import com.cust.persistance.PersistanceManager;
import com.cust.persistance.util.CustUtil;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Inderjit
 */
public class BuySellManager {

    BuySellWebService buySellWebService;
    ServiceControl serviceControl = null;
    SessionInfo sessionInfo = null;

    public BuySellManager() {
        serviceControl = PersistanceManager.getInstance().getServiceControl();
        sessionInfo = PersistanceManager.getInstance().getSessionInfo();
    }

    public boolean deleteBuySell(ElegantBuySell sup) throws Exception {
        boolean deleteDone = false;
        ArrayList<ElegantBuySell> buySellList = new ArrayList<>();
        buySellList.add(sup);
        try {
            if (PersistanceManager.getInstance().connectToService("BuySellService")) {
                buySellWebService = PersistanceManager.getInstance().getBuysellWebService();
                deleteDone = (Boolean) buySellWebService.deleteBuySellList(serviceControl, buySellList).getResponseValue().get(0);
                serviceControl.setQueryCriteria(null);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return deleteDone;
    }

    public boolean saveOrUpdateBuySell(ArrayList<ElegantBuySell> buySellList, boolean updateStock) throws Exception {
        boolean updateDone = true;
        try {
            if (PersistanceManager.getInstance().connectToService("BuySellService")) {
                buySellWebService = PersistanceManager.getInstance().getBuysellWebService();
                buySellWebService.saveBuySellList(serviceControl, buySellList, updateStock);
                updateDone = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//            System.out.println(buySellList.get(0).getBuySellDetailsList().size() + " saved " + updateDone );
        return updateDone;
    }

    public ElegantBuySell getBuySellById(ElegantUser user, int buySellId) throws Exception {
        ElegantBuySell buySell = null;
        try {
            if (PersistanceManager.getInstance().connectToService("BuySellService")) {
                buySellWebService = PersistanceManager.getInstance().getBuysellWebService();
                buySell = (ElegantBuySell) buySellWebService.getBuySellById(serviceControl, user.getCompID(), buySellId).getResponseValue().get(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return buySell;
    }

    public ArrayList<ElegantBuySell> getAllBuySell(ElegantUser user, int billType, boolean forReport) throws Exception {
        ArrayList<ElegantBuySell> buySellList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();
//        queryCriteria.setFilterCriteria(filterCriteriaList);
//        serviceControl.setQueryCriteria(queryCriteria);
        try {
            if (PersistanceManager.getInstance().connectToService("BuySellService")) {
                if (serviceControl.getQueryCriteria() == null) {
                    serviceControl.setQueryCriteria(queryCriteria);
                }
                if (serviceControl.getQueryCriteria().getFilterCriteria() == null) {
                    serviceControl.getQueryCriteria().setFilterCriteria(filterCriteriaList);
                }

                buySellWebService = PersistanceManager.getInstance().getBuysellWebService();
                FilterCriteria filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("billType");
                filterCriteria.setFilterCondition("=");
                filterCriteria.setFilterFieldValue(Integer.toString(billType));
                serviceControl.getQueryCriteria().getFilterCriteria().add(filterCriteria);

                filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("billStatus");
                filterCriteria.setFilterCondition("=");
                filterCriteria.setFilterFieldValue("0");

                serviceControl.getQueryCriteria().getFilterCriteria().add(filterCriteria);
//              
//                filterCriteria = new FilterCriteria();
//                filterCriteria.setFilterFieldName("billStatus");
//                filterCriteria.setFilterCondition("=");
//                filterCriteria.setFilterFieldValue("0");
//                serviceControl.getQueryCriteria().getFilterCriteria().add(filterCriteria);
                buySellList = (ArrayList<ElegantBuySell>) buySellWebService.getAllBuySell(serviceControl, user.getCompID(), forReport).getResponseValue().get(0);
                serviceControl.setQueryCriteria(null);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return buySellList;
    }

    public ArrayList<ElegantBuySell> getAllBuySellForRep(ElegantUser user) throws Exception {
        ArrayList<ElegantBuySell> buySellList = null;
        try {
            if (PersistanceManager.getInstance().connectToService("BuySellService")) {
                buySellWebService = PersistanceManager.getInstance().getBuysellWebService();
                buySellList = (ArrayList<ElegantBuySell>) buySellWebService.getAllBuySell(serviceControl, user.getCompID(), true).getResponseValue().get(0);
                serviceControl.setQueryCriteria(null);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return buySellList;
    }

    public ArrayList<ElegantMailList> getGlobalMailList() throws Exception {
        ArrayList<ElegantMailList> mailList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<SortCriteria> sortCriteriaList = new ArrayList<SortCriteria>();
        try {
            SortCriteria sortCriteria = new SortCriteria();
            sortCriteria.setSortFieldName("emailAddress");
            sortCriteria.setSortDirection("ASC");
            sortCriteriaList.add(sortCriteria);
            queryCriteria.setSortCriteria(sortCriteriaList);
            serviceControl.setQueryCriteria(queryCriteria);
            if (PersistanceManager.getInstance().connectToService("BuySellService")) {
                buySellWebService = PersistanceManager.getInstance().getBuysellWebService();
                mailList = (ArrayList<ElegantMailList>) buySellWebService.getGlobalMailingList(serviceControl).getResponseValue().get(0);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mailList;
    }

    public ArrayList<ElegantMarketMail> saveGlobalMailList(ArrayList<ElegantMarketMail> mailList) throws Exception {
        try {
            if (PersistanceManager.getInstance().connectToService("BuySellService")) {
                buySellWebService = PersistanceManager.getInstance().getBuysellWebService();
                mailList = (ArrayList<ElegantMarketMail>) buySellWebService.saveGlobalMailingList(serviceControl, mailList).getResponseValue().get(0);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mailList;
    }

    public ArrayList<ElegantBuySellConsolidation> getAllPendingOrders(ElegantUser user, String supCode, String salesMan, String prodCode, Date fromDate, Date toDate, String billType) throws Exception {
        ArrayList<ElegantBuySellConsolidation> pendingOrderList = null;
        int salesManId = 0;
        long prodId = 0;
        try {
            billType = billType.equals(CustUtil.PENDINGPURCHASEORDERS) ? "Order" : billType.equals(CustUtil.PENDINGSALESORDERS) ? "Invoice" : billType.equals(CustUtil.ORDERVSSALES) ? "OrderVsSale" : "";
            if (PersistanceManager.getInstance().connectToService("BuySellService")) {
                buySellWebService = PersistanceManager.getInstance().getBuysellWebService();
                if (!salesMan.isEmpty()) {
                    salesManId = new Integer(salesMan);
                }
                if (!prodCode.isEmpty()) {
                    prodId = new Long(prodCode);
                }
                pendingOrderList = (ArrayList<ElegantBuySellConsolidation>) buySellWebService.getAllBuySellForReport(serviceControl, user.getCompID(), salesManId, prodId, supCode, fromDate, toDate, billType).getResponseValue().get(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pendingOrderList;
    }
}
