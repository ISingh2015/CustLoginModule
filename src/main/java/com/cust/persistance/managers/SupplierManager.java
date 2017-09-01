package com.cust.persistance.managers;

import com.cust.common.ServiceControl;
import com.cust.common.SessionInfo;
import com.cust.domain.vo.ElegantSupplier;
import com.cust.domain.vo.ElegantUser;
import com.cust.domain.webservice.SupplierWebService;
import com.cust.persistance.PersistanceManager;
import java.util.ArrayList;

/**
 *
 * @author Inderjit
 */
public class SupplierManager {

    private SupplierWebService supplierWebService = null;
    ServiceControl serviceControl = null;
    SessionInfo sessionInfo = null;

    public SupplierManager() {
        serviceControl = PersistanceManager.getInstance().getServiceControl();
        sessionInfo = PersistanceManager.getInstance().getSessionInfo();
    }

    public boolean deleteSupplier(ElegantSupplier sup) throws Exception {
        boolean deleteDone = true;
        ArrayList<ElegantSupplier> deleteSupList = new ArrayList<>();
        deleteSupList.add(sup);
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.supplierservice")[0])) {
                supplierWebService = PersistanceManager.getInstance().getSupplierWebService();
                deleteDone = (Boolean) supplierWebService.deleteSupplierList(serviceControl, deleteSupList).getResponseValue().get(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return deleteDone;
    }

    public boolean saveOrUpdateSupplier(ArrayList<ElegantSupplier> supplierList) throws Exception {
        boolean updateDone = true;
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.supplierservice")[0])) {
                supplierWebService = PersistanceManager.getInstance().getSupplierWebService();
                supplierWebService.saveSupplierList(serviceControl, supplierList);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return updateDone;
    }

    public ArrayList<ElegantSupplier> getSupplierById(ElegantUser user, int supId) throws Exception {
        ArrayList<ElegantSupplier> supListForUser = null;
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.supplierservice")[0])) {
                supplierWebService = PersistanceManager.getInstance().getSupplierWebService();
                supplierWebService.getSupplierById(serviceControl, user.getCompID(), supId);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return supListForUser;
    }

    public ArrayList<ElegantSupplier> getAllSupplier(ElegantUser user) throws Exception {
        ArrayList<ElegantSupplier> supList = null;
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.supplierservice")[0])) {
                supplierWebService = PersistanceManager.getInstance().getSupplierWebService();
                supList = (ArrayList<ElegantSupplier>) supplierWebService.getAllSuppliers(serviceControl, user.getCompID()).getResponseValue().get(0);
            }
//            if (supList!=null) Collections.sort(supList);            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return supList;
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
