/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cust.persistance;

import com.caucho.hessian.client.HessianProxyFactory;
import com.cust.common.DBInfo;
import com.cust.common.ServiceControl;
import com.cust.common.ServicePayload;
import com.cust.common.SessionInfo;
import com.cust.common.UserInfo;
import com.cust.domain.vo.ElegantUser;
import com.cust.domain.vo.ElegantUserAccess;
import com.cust.domain.vo.PreferenceVO;
import com.cust.domain.webservice.BuySellWebService;
import com.cust.domain.webservice.CountryWebService;
import com.cust.domain.webservice.CustomerWebService;
import com.cust.domain.webservice.PreferenceWebService;
import com.cust.domain.webservice.ProductWebService;
import com.cust.domain.webservice.SalesManWebService;
import com.cust.domain.webservice.SupplierWebService;
import com.cust.domain.webservice.UserWebService;
import com.cust.persistance.util.CustUtil;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author ISanhot
 */
public class PersistanceManager {

    private PreferenceWebService preferenceWebService = null;
    private UserWebService userWebService = null;
    private CountryWebService countryWebService = null;
    private CustomerWebService customerWebService = null;
    private SupplierWebService supplierWebService = null;
    private ProductWebService productWebService = null;
    private SalesManWebService salesManWebService = null;
    private BuySellWebService buysellWebService = null;

    private ElegantUser elegantUser;
    private PreferenceVO preferenceVO;
    private ArrayList<ElegantUserAccess> userAccessList;    
    private String loginServer;

    private ServicePayload servicePayload=null;
    private ServiceControl serviceControl=null;
    private DBInfo dbInfo=null;
    private SessionInfo sessionInfo=null;
    private UserInfo userInfo=null;
    
    private PersistanceManager() {
        serviceControl = new ServiceControl();
        sessionInfo = new SessionInfo();

        userInfo = new UserInfo();
        userInfo.setUserName("sa");
        sessionInfo.setSessionId(UUID.randomUUID().toString());
        sessionInfo.setUserInfo(userInfo);

        dbInfo = new DBInfo();
        dbInfo.setDbName("custService");
        serviceControl.setDbInfo(dbInfo);

        serviceControl.setSessionInfo(sessionInfo);
        
    }

    public static PersistanceManager getInstance() {
        return PersistanceManagerHolder.INSTANCE;
    }

    /**
     * @return the preferenceVO
     */
    public PreferenceVO getPreferenceVO() {
        return preferenceVO;
    }

    /**
     * @param preferenceVO the preferenceVO to set
     */
    public void setPreferenceVO(PreferenceVO preferenceVO) {
        this.preferenceVO = preferenceVO;
    }

    /**
     * @return the loginServer
     */
    public String getLoginServer() {
//        loginServer = (loginServer==null ? "localhost": loginServer);
        return loginServer;
    }

    /**
     * @param loginServer the loginServer to set
     */
    public void setLoginServer(String loginServer) {
        this.loginServer = loginServer;
    }

    /**
     * @return the elegantUser
     */
    public ElegantUser getElegantUser() {
        return elegantUser;
    }

    /**
     * @param elegantUser the elegantUser to set
     */
    public void setElegantUser(ElegantUser elegantUser) {
        this.elegantUser = elegantUser;
    }

    /**
     * @return the userAccessList
     */
    public ArrayList<ElegantUserAccess> getUserAccessList() {
        return userAccessList;
    }

    /**
     * @param userAccessList the userAccessList to set
     */
    public void setUserAccessList(ArrayList<ElegantUserAccess> userAccessList) {
        this.userAccessList = userAccessList;
    }

    private static class PersistanceManagerHolder {

        private static final PersistanceManager INSTANCE = new PersistanceManager();
    }

    /**
     * @return the userWebService
     */
    public UserWebService getUserWebService() {
        return userWebService;
    }

    /**
     * @param userWebService the userWebService to set
     */
    public void setUserWebService(UserWebService userWebService) {
        if (this.userWebService == null) {
            this.userWebService = userWebService;
        }
        return;
    }

    /**
     * @return the countryWebService
     */
    public CountryWebService getCountryWebService() {
        return countryWebService;
    }

    /**
     * @param countryWebService the countryWebService to set
     */
    public void setCountryWebService(CountryWebService countryWebService) {
        if (this.countryWebService == null) {
            this.countryWebService = countryWebService;
        }
    }

    /**
     * @return the customerWebService
     */
    public CustomerWebService getCustomerWebService() {
        return customerWebService;
    }

    /**
     * @param customerWebService the customerWebService to set
     */
    public void setCustomerWebService(CustomerWebService customerWebService) {
        if (this.customerWebService == null) {
            this.customerWebService = customerWebService;
        }
    }

    /**
     * @return the supplierWebService
     */
    public SupplierWebService getSupplierWebService() {
        return supplierWebService;
    }

    /**
     * @param supplierWebService the supplierWebService to set
     */
    public void setSupplierWebService(SupplierWebService supplierWebService) {
        if (this.supplierWebService == null) {
            this.supplierWebService = supplierWebService;
        }
    }

    /**
     * @return the buysellWebService
     */
    public BuySellWebService getBuysellWebService() {
        return buysellWebService;
    }

    /**
     * @param buysellWebService the buysellWebService to set
     */
    public void setBuysellWebService(BuySellWebService buysellWebService) {
        if (this.buysellWebService == null) {
            this.buysellWebService = buysellWebService;
        }
    }

    /**
     * @return the productWebService
     */
    public ProductWebService getProductWebService() {
        return productWebService;
    }

    /**
     * @param productWebService the productWebService to set
     */
    public void setProductWebService(ProductWebService productWebService) {
        if (this.productWebService == null) {
            this.productWebService = productWebService;
        }
    }

    /**
     * @return the salesManWebService
     */
    public SalesManWebService getSalesManWebService() {
        return salesManWebService;
    }

    /**
     * @param salesManWebService the salesManWebService to set
     */
    public void setSalesManWebService(SalesManWebService salesManWebService) {
        if (this.salesManWebService == null) {
            this.salesManWebService = salesManWebService;
        }

    }

    /**
     * @return the preferenceWebService
     */
    public PreferenceWebService getPreferenceWebService() {
        return preferenceWebService;
    }

    /**
     * @param preferenceWebService the preferenceWebService to set
     */
    public void setPreferenceWebService(PreferenceWebService preferenceWebService) {
        this.preferenceWebService = preferenceWebService;
    }
    /**
     * @return the serviceControl
     */
    public ServiceControl getServiceControl() {
        return serviceControl;
    }

    /**
     * @return the dbInfo
     */
    public DBInfo getDbInfo() {
        return dbInfo;
    }

    /**
     * @return the sessionInfo
     */
    public SessionInfo getSessionInfo() {
        return sessionInfo;
    }

    /**
     * @return the userInfo
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }


    /**
     * This method connects to the database server using a web Service
     *
     * @param serviceName
     * @return
     */
    public boolean connectToService(String serviceName) {
        boolean connected = false;
        HessianProxyFactory factory = new HessianProxyFactory();
        String webUrl = "http://" + PersistanceManager.getInstance().getLoginServer() + ":" + CustServiceConstants.PORT + "/" + CustServiceConstants.APPURLNAME + "/";
//        System.out.println(webUrl);
        try {
            if (serviceName.equals(CustUtil.PERFSERVICE)) {
                webUrl += serviceName;
                PreferenceWebService webService = (PreferenceWebService) factory.create(PreferenceWebService.class, webUrl);
                if (webService != null) {
                    setPreferenceWebService(webService);
                    connected = true;
                }

            } else if (serviceName.equals(getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.userservice")[0])) {
                webUrl += serviceName;
                UserWebService webService = (UserWebService) factory.create(UserWebService.class, webUrl);
                if (webService != null) {
                    setUserWebService(webService);
                    connected = true;
                }
            } else if (serviceName.equals(getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.customerservice")[0])) {
                webUrl += serviceName;
                CustomerWebService webService = (CustomerWebService) factory.create(CustomerWebService.class, webUrl);
                if (webService != null) {
                    setCustomerWebService(webService);
                    connected = true;
                }
            } else if (serviceName.equals(getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.countryservice")[0])) {
                webUrl += serviceName;
                CountryWebService webService = (CountryWebService) factory.create(CountryWebService.class, webUrl);
                if (webService != null) {
                    setCountryWebService(webService);
                    connected = true;
                }
            } else if (serviceName.equals(getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.supplierservice")[0])) {
                webUrl += serviceName;
                SupplierWebService webService = (SupplierWebService) factory.create(SupplierWebService.class, webUrl);
                if (webService != null) {
                    setSupplierWebService(webService);
                    connected = true;
                }
            } else if (serviceName.equals(getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.productservice")[0])) {
                webUrl += serviceName;
                ProductWebService webService = (ProductWebService) factory.create(ProductWebService.class, webUrl);
                if (webService != null) {
                    setProductWebService(webService);
                    connected = true;
                }
            } else if (serviceName.equals(getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.salesmanservice")[0])) {
                webUrl += serviceName;
                SalesManWebService webService = (SalesManWebService) factory.create(SalesManWebService.class, webUrl);
                if (webService != null) {
                    setSalesManWebService(webService);
                    connected = true;
                }
            } else if (serviceName.equals(getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.buysellservice")[0])) {
                webUrl += serviceName;
                BuySellWebService webService = (BuySellWebService) factory.create(BuySellWebService.class, webUrl);
                if (webService != null) {
                    setBuysellWebService(webService);
                    connected = true;
                }
            }
        System.out.println(webUrl);            
        } catch (Exception e) {
            System.out.println("Error while connecting, Please re-try in some time " + e.getMessage());
        }
        return connected;
    }

}
