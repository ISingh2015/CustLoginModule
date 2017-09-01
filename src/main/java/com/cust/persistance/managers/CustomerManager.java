package com.cust.persistance.managers;

import com.cust.common.ServiceControl;
import com.cust.common.ServicePayload;
import com.cust.common.SessionInfo;
import com.cust.domain.vo.ElegantCustomer;
import com.cust.domain.vo.ElegantUser;
import com.cust.domain.webservice.CustomerWebService;
import com.cust.persistance.PersistanceManager;
import java.util.ArrayList;

/**
 *
 * @author Inderjit
 */
public class CustomerManager {

    private CustomerWebService customerWebService;
    ServiceControl serviceControl = null;
    SessionInfo sessionInfo = null;

    public CustomerManager() {
        serviceControl = PersistanceManager.getInstance().getServiceControl();
        sessionInfo = PersistanceManager.getInstance().getSessionInfo();
    }

    public boolean deleteCustomer(ElegantCustomer cust) throws Exception {
        boolean deleteDone = true;
        ArrayList<ElegantCustomer> elegantCustomerList = new ArrayList<ElegantCustomer>();
        elegantCustomerList.add(cust);
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.customerservice")[0])) {
                customerWebService = PersistanceManager.getInstance().getCustomerWebService();
                deleteDone = (Boolean) customerWebService.deleteCustomerList(serviceControl, elegantCustomerList).getResponseValue().get(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return deleteDone;
    }

    public ArrayList<ElegantCustomer> saveOrUpdateCustomer(ArrayList<ElegantCustomer> custList) throws Exception {
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.customerservice")[0])) {
                customerWebService = PersistanceManager.getInstance().getCustomerWebService();
                ServicePayload servicePayload = customerWebService.saveCustomerList(serviceControl, custList);
                custList = (ArrayList<ElegantCustomer>) servicePayload.getResponseValue().get(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return custList;
    }

    public ElegantCustomer getCustomerById(ElegantUser user, int custId) throws Exception {
        ElegantCustomer custForUser = null;
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.customerservice")[0])) {
                customerWebService = PersistanceManager.getInstance().getCustomerWebService();
                ServicePayload servicePayload = customerWebService.getCustomerById(serviceControl, user.getCompID(), custId);
                custForUser = (ElegantCustomer) servicePayload.getResponseValue().get(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return custForUser;
    }

    public ArrayList<ElegantCustomer> getAllCustomer(ElegantUser user) throws Exception {
        ArrayList<ElegantCustomer> custList = null;
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.customerservice")[0])) {
                customerWebService = PersistanceManager.getInstance().getCustomerWebService();
                ServicePayload servicePayload = customerWebService.getAllCustomers(serviceControl, user.getCompID());
                custList = (ArrayList<ElegantCustomer>) servicePayload.getResponseValue().get(0);
            }
//            if (custList!=null) Collections.sort(custList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return custList;
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
