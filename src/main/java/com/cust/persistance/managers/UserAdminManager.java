/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cust.persistance.managers;

import com.cust.common.ServiceControl;
import com.cust.common.SessionInfo;
import com.cust.domain.vo.ElegantUser;
import com.cust.domain.webservice.UserWebService;
import com.cust.persistance.PersistanceManager;
import java.util.ArrayList;

/**
 *
 * @author ISanhot
 */
public class UserAdminManager {

    UserWebService userWebService = null;
    ServiceControl serviceControl = null;
    SessionInfo sessionInfo = null;

    public UserAdminManager() {
        serviceControl = PersistanceManager.getInstance().getServiceControl();
        sessionInfo = PersistanceManager.getInstance().getSessionInfo();
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

    public ArrayList<ElegantUser> getAllUsers(ElegantUser user) throws Exception {
        ArrayList<ElegantUser> userList = null;
        try {
//            LoginVO loginVO = (LoginVO) PersistanceManager.getInstance().getViewModelList().get("loginModel");
//            serviceControl.getSessionInfo().getUserInfo().setLoginId(loginVO.getName());

            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.userservice")[0])) {
                userWebService = PersistanceManager.getInstance().getUserWebService();
                userList = (ArrayList<ElegantUser>) userWebService.getAllUsers(serviceControl, user.getCompID()).getResponseValue().get(0);
            }
//            if (userList != null) {
//                Collections.sort(userList);
//            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }


    public boolean saveOrUpdateUser(ArrayList<ElegantUser> userList) throws Exception {
        boolean updateDone = true;
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.userservice")[0])) {
//                LoginVO loginVO = (LoginVO) PersistanceManager.getInstance().getViewModelList().get("loginModel");
//                serviceControl.getSessionInfo().getUserInfo().setLoginId(loginVO.getName());

                userWebService = PersistanceManager.getInstance().getUserWebService();
                userWebService.saveUserList(serviceControl, userList, true);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return updateDone;
    }

    public boolean deleteUser(ElegantUser userAdmin) throws Exception {
        boolean deleteDone = false;
        ArrayList<ElegantUser> deleteUserAdminList = new ArrayList<>();
        deleteUserAdminList.add(userAdmin);
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.userservice")[0])) {
//                LoginVO loginVO = (LoginVO) PersistanceManager.getInstance().getViewModelList().get("loginModel");
//                serviceControl.getSessionInfo().getUserInfo().setLoginId(loginVO.getName());

                userWebService = PersistanceManager.getInstance().getUserWebService();
                deleteDone = (Boolean) userWebService.deleteUserList(serviceControl, deleteUserAdminList).getResponseValue().get(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return deleteDone;
    }

}
