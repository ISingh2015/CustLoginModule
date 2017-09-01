package com.cust.persistance.managers;

import com.cust.common.ApplicationException;
import com.cust.common.FilterCriteria;
import com.cust.common.QueryCriteria;
import com.cust.common.ServiceControl;
import com.cust.common.ServicePayload;
import com.cust.common.SessionInfo;
import com.cust.domain.vo.ElegantHitCounter;
import com.cust.domain.vo.ElegantUser;
import com.cust.domain.vo.ElegantUserAccess;
import com.cust.domain.vo.ElegantUserActivation;
import com.cust.domain.vo.ElegantUserLogin;
import com.cust.domain.vo.PreferenceVO;
import com.cust.domain.webservice.UserWebService;
import com.cust.persistance.PersistanceManager;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Inderjit
 */
public class LoginManager {

    ServiceControl serviceControl = null;
    SessionInfo sessionInfo = null;

    public LoginManager() {
        serviceControl = PersistanceManager.getInstance().getServiceControl();
        sessionInfo = PersistanceManager.getInstance().getSessionInfo();

    }

    public ElegantUser dogetUserByCredentials(String userName, String pwd) throws Exception {
        ElegantUser user = null;
        ArrayList<ElegantUserAccess> userAccessList;
        UserWebService userWebService;
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.userservice")[0])) {
                userWebService = PersistanceManager.getInstance().getUserWebService();
                ServicePayload servicePayload = userWebService.getLoginByUserNameAndPassword(serviceControl, userName, pwd);
                user = (ElegantUser) servicePayload.getResponseValue().get(0);
                if (user != null) {
                    userAccessList = user.getElegantUserAccessList();
                    PersistanceManager.getInstance().setUserAccessList(userAccessList);
                    PersistanceManager.getInstance().setElegantUser(user);
//                    PreferenceVO prefVO = (PreferenceVO) servicePayload.getResponseValue().get(1);
//                    if (prefVO!=null) PersistanceManager.getInstance().setPreferenceVO(prefVO);
                }
            }
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " dogetUserByCredentials :-> " + e.getMessage());
        }
        return user;
    }

    public ArrayList<ElegantUser> getAllUsers() throws Exception {
        UserWebService userWebService;
        ArrayList<ElegantUser> userList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();

        try {
            FilterCriteria filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("disabled");
            filterCriteria.setFilterCondition("=");
            filterCriteria.setFilterFieldValue("0");
            filterCriteriaList.add(filterCriteria);

            queryCriteria.setFilterCriteria(filterCriteriaList);

            serviceControl.setQueryCriteria(queryCriteria);

            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.userservice")[0])) {
                userWebService = PersistanceManager.getInstance().getUserWebService();
                ServicePayload servicePayload = userWebService.getAllUsers(serviceControl, PersistanceManager.getInstance().getElegantUser().getCompID());
                userList = (ArrayList<ElegantUser>) servicePayload.getResponseValue().get(0);
                serviceControl.setQueryCriteria(null);
            }
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " getAllUsers :-> " + e.getMessage());
        }
        return userList;
    }

    public ArrayList<ElegantUser> getUserByMobile(String name) throws Exception {
        UserWebService userWebService;
        ArrayList<ElegantUser> userList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();

        try {
            FilterCriteria filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("mobileNo");
            filterCriteria.setFilterCondition("=");
            filterCriteria.setFilterFieldValue(name);
            filterCriteriaList.add(filterCriteria);

            queryCriteria.setFilterCriteria(filterCriteriaList);

            serviceControl.setQueryCriteria(queryCriteria);

            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.userservice")[0])) {
                userWebService = PersistanceManager.getInstance().getUserWebService();
                ServicePayload servicePayload = userWebService.getUserByCriteria(serviceControl);
                userList = (ArrayList<ElegantUser>) servicePayload.getResponseValue().get(0);
                serviceControl.setQueryCriteria(null);
            }
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " getUserByMobile :-> " + e.getMessage());
        }
        return userList;
    }

    public ArrayList<ElegantUser> getUserByName(String name) {
        UserWebService userWebService;
        ArrayList<ElegantUser> userList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();

        try {
            FilterCriteria filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("userLoginName");
            filterCriteria.setFilterCondition("=");
            filterCriteria.setFilterFieldValue(name);
            filterCriteriaList.add(filterCriteria);

            queryCriteria.setFilterCriteria(filterCriteriaList);

            serviceControl.setQueryCriteria(queryCriteria);

            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.userservice")[0])) {
                userWebService = PersistanceManager.getInstance().getUserWebService();
                sessionInfo.getUserInfo().setLoginId(name);
                ServicePayload servicePayload = userWebService.getUserByCriteria(serviceControl);
                userList = (ArrayList<ElegantUser>) servicePayload.getResponseValue().get(0);
                serviceControl.setQueryCriteria(null);
            }
        } catch (ApplicationException e) {
            System.out.println(this.getClass().getName() + " getUserByName :-> " + e.getMessage());
        }
        return userList;
    }

    public ArrayList<ElegantUser> getUserByEmailAndMobile(String emailId, String mobileNo) throws Exception {
        UserWebService userWebService;
        ArrayList<ElegantUser> userList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();

        try {
            FilterCriteria filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("emailId");
            filterCriteria.setFilterCondition("=");
            filterCriteria.setFilterFieldValue(emailId);
            filterCriteriaList.add(filterCriteria);

            filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("mobileNo");
            filterCriteria.setFilterCondition("=");
            filterCriteria.setFilterFieldValue(mobileNo);
            filterCriteriaList.add(filterCriteria);

            queryCriteria.setFilterCriteria(filterCriteriaList);

            serviceControl.setQueryCriteria(queryCriteria);
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.userservice")[0])) {
                userWebService = PersistanceManager.getInstance().getUserWebService();
                ServicePayload servicePayload = userWebService.getUserByCriteria(serviceControl);
                userList = (ArrayList<ElegantUser>) servicePayload.getResponseValue().get(0);
                serviceControl.setQueryCriteria(null);
            }
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " getUserByEmailAndMobile :-> " + e.getMessage());
        }
        return userList;
    }

    public ArrayList<ElegantUser> getUserByEmail(String emailId) throws Exception {
        UserWebService userWebService;
        ArrayList<ElegantUser> userList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();

        try {
            FilterCriteria filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("emailId");
            filterCriteria.setFilterCondition("=");
            filterCriteria.setFilterFieldValue(emailId);
            filterCriteriaList.add(filterCriteria);

            queryCriteria.setFilterCriteria(filterCriteriaList);

            serviceControl.setQueryCriteria(queryCriteria);
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.userservice")[0])) {
                userWebService = PersistanceManager.getInstance().getUserWebService();
                ServicePayload servicePayload = userWebService.getUserByCriteria(serviceControl);
                userList = (ArrayList<ElegantUser>) servicePayload.getResponseValue().get(0);
                serviceControl.setQueryCriteria(null);
            }
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " getUserByEmail :-> " + e.getMessage());
        }
        return userList;
    }

    public ArrayList<ElegantUser> saveUserList(ArrayList<ElegantUser> userList, boolean createAccessRights) {
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.userservice")[0])) {
                UserWebService userWebService = PersistanceManager.getInstance().getUserWebService();
                ServicePayload servicePayload = userWebService.saveUserList(serviceControl, userList, createAccessRights);
                userList = (ArrayList<ElegantUser>) servicePayload.getResponseValue().get(0);
                if (userList.size() == 1) {
                    ElegantUser elegantUser = userList.get(0);
                    ElegantUserLogin elegantUserLogin = new ElegantUserLogin();
                    ArrayList<ElegantUserLogin> elegantUserLoginList = new ArrayList<ElegantUserLogin>();
                    elegantUserLogin.setCompID(elegantUser.getCompID());
                    elegantUserLogin.setUserID(elegantUser.getUserID());
                    elegantUserLogin.setLoginDate(new Date());
                    elegantUserLogin.setLoginIP(InetAddress.getLocalHost().getHostAddress());
                    elegantUserLoginList.add(elegantUserLogin);
                    servicePayload = userWebService.saveUserLoginList(serviceControl, elegantUserLoginList);
                }
            }
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " saveUserList :-> " + e.getMessage());
        }
        return userList;
    }

    public ArrayList<ElegantUserActivation> getUserActivation(ArrayList<ElegantUserActivation> userList, String mailTo) throws Exception {

        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.userservice")[0])) {
                UserWebService userWebService = PersistanceManager.getInstance().getUserWebService();
                ServicePayload servicePayload = userWebService.saveUserActivation(serviceControl, userList, mailTo);
                userList = (ArrayList<ElegantUserActivation>) servicePayload.getResponseValue().get(0);
            }
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " getUserActivation :-> " + e.getMessage());
        }
        return userList;
    }

    public boolean doLogOffUser() throws Exception {

        Boolean loggedOff = false;
        try {
            ElegantUser user = PersistanceManager.getInstance().getElegantUser();
            ArrayList<ElegantUser> userList = new ArrayList<>();
            user.setLoggedIn(0);
            userList.add(user);
            saveUserList(userList, false);
            loggedOff = true;
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " doLogOffUser :-> " + e.getMessage());
        }
        return loggedOff;
    }

    public boolean doSaveUserLogin(ElegantUser user) {
        Boolean loggedin = false;
        try {
            ArrayList<ElegantUser> userList = new ArrayList<>();
            user.setLoggedIn(1);
            user.setLoggedInIp(InetAddress.getLocalHost().getHostAddress());
            user.setLoggedInDate(new Date());
            userList.add(user);
            saveUserList(userList, false);
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " doSaveUserLogin :-> " + e.getMessage());
        }
        return loggedin;
    }

    public PreferenceVO getPreferenceList() {
        PreferenceVO preferenceVO = null;
        try {
            if (PersistanceManager.getInstance().connectToService("PreferenceService")) {
                ArrayList<PreferenceVO> preferenceVOList = (ArrayList<PreferenceVO>) PersistanceManager.getInstance().getPreferenceWebService().getServiceNames(serviceControl, 6000).getResponseValue().get(0);
                if (!preferenceVOList.isEmpty()) {
                    PersistanceManager.getInstance().setPreferenceVO(preferenceVOList.get(0));
                }
            }
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " getPreferenceList :-> " + e.getMessage());
        }
        return preferenceVO;
    }

    public ElegantHitCounter getElegantHitCounter(ElegantHitCounter elegantHitCounter) throws Exception {
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.userservice")[0])) {
//                LoginVO loginVO = (LoginVO) PersistanceManager.getInstance().getViewModelList().get("loginModel");
//                serviceControl.getSessionInfo().getUserInfo().setLoginId(loginVO.getName());

                UserWebService userWebService = PersistanceManager.getInstance().getUserWebService();
                elegantHitCounter = (ElegantHitCounter) userWebService.getHitCount(serviceControl, elegantHitCounter).getResponseValue().get(0);
            }
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " getElegantHitCounter :-> " + e.getMessage());
        }
        return elegantHitCounter;
    }

    public ElegantHitCounter saveElegantHitCounter(ElegantHitCounter elegantHitCounter) throws Exception {
        try {
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.userservice")[0])) {
//                LoginVO loginVO = (LoginVO) PersistanceManager.getInstance().getViewModelList().get("loginModel");
//                serviceControl.getSessionInfo().getUserInfo().setLoginId(loginVO.getName());

                UserWebService userWebService = PersistanceManager.getInstance().getUserWebService();
                elegantHitCounter = (ElegantHitCounter) userWebService.getHitCount(serviceControl, elegantHitCounter).getResponseValue().get(0);
                long newHitCount = elegantHitCounter.getHitCounter() + 1;
                elegantHitCounter.setHitCounter(newHitCount);
                elegantHitCounter = (ElegantHitCounter) userWebService.saveHitCount(serviceControl, elegantHitCounter).getResponseValue().get(0);
            }
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " saveElegantHitCounter :-> " + e.getMessage());
        }
        return elegantHitCounter;
    }

    public ElegantUser getUserByLoginOrEmail(String userNameOrEmail) throws Exception {
        ElegantUser user = null;
        UserWebService userWebService;        
        try {

            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.userservice")[0])) {
                userWebService = PersistanceManager.getInstance().getUserWebService();
                user = (ElegantUser) userWebService.getLoginByUserNameOrEmail(serviceControl, userNameOrEmail).getResponseValue().get(0);
            }
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " getUserByLoginOrEmail :-> " + e.getMessage());
        }
        return user;
    }

    public ElegantUser sendUserGeneratePassword(ElegantUser user) throws Exception {
        ArrayList<ElegantUser> userList = new ArrayList<> ();
        UserWebService userWebService;        
        try {
            userList.add(user);
            if (PersistanceManager.getInstance().connectToService(PersistanceManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.userservice")[0])) {
                userWebService = PersistanceManager.getInstance().getUserWebService();
                userList = (ArrayList<ElegantUser>) userWebService.saveUserGenPassword(serviceControl, userList).getResponseValue().get(0);
                user = userList.get(0);
            }
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + " sendUserGeneratePassword :-> " + e.getMessage());
        }
        return user;
    }
    
}
