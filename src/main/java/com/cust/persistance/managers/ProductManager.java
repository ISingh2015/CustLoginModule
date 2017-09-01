/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cust.persistance.managers;

import com.cust.common.ServiceControl;
import com.cust.common.SessionInfo;
import com.cust.domain.vo.ElegantProduct;
import com.cust.domain.vo.ElegantUser;
import com.cust.domain.webservice.ProductWebService;
import com.cust.persistance.PersistanceManager;
import java.util.ArrayList;

/**
 *
 * @author Inderjit
 */
public class ProductManager {

    private ProductWebService productWebService = null;
    ServiceControl serviceControl = null;
    SessionInfo sessionInfo = null;

    public ProductManager() {
        serviceControl = PersistanceManager.getInstance().getServiceControl();
        sessionInfo = PersistanceManager.getInstance().getSessionInfo();
    }

    public boolean deleteProduct(ElegantProduct product) throws Exception {
        boolean deleteDone = true;
        ArrayList<ElegantProduct> productList = new ArrayList<>();
        productList.add(product);
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.productservice")[0])) {
                productWebService = PersistanceManager.getInstance().getProductWebService();
                deleteDone = (Boolean) productWebService.deleteProductList(serviceControl, productList).getResponseValue().get(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return deleteDone;
    }

    public boolean saveOrUpdateProduct(ArrayList<ElegantProduct> prodList) throws Exception {
        boolean updateDone = true;
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.productservice")[0])) {
                productWebService = PersistanceManager.getInstance().getProductWebService();
                productWebService.saveProductList(serviceControl, prodList);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return updateDone;
    }

    public ArrayList<ElegantProduct> getProductById(ElegantUser user, int prodId) throws Exception {
        ArrayList<ElegantProduct> productList = null;
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.productservice")[0])) {
                productWebService = PersistanceManager.getInstance().getProductWebService();
                productWebService.getProductById(serviceControl, user.getCompID(), prodId);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productList;
    }

    public ArrayList<ElegantProduct> getAllProducts(ElegantUser user) throws Exception {
        ArrayList<ElegantProduct> productList = null;
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.productservice")[0])) {
                productWebService = PersistanceManager.getInstance().getProductWebService();
                productList = (ArrayList<ElegantProduct>) productWebService.getAllProducts(serviceControl, user.getCompID()).getResponseValue().get(0);
                serviceControl.setQueryCriteria(null);                
//           if (productList!=null) Collections.sort(productList);
//           System.out.println( "Total Items " + productList.size());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return productList;
    }

    public Double getProductStockById(ElegantUser user, long billId, long prodId) throws Exception {
        Double prodStock=0d;
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.productservice")[0])) {
                productWebService = PersistanceManager.getInstance().getProductWebService();
                prodStock = (Double) productWebService.getProductStockById(serviceControl, user.getCompID(), billId, prodId).getResponseValue().get(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return prodStock;
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

}
