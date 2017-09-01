package com.cust.persistance.managers;

import com.cust.domain.vo.AddressXML;
import com.cust.domain.vo.ElegantCustomer;
import com.cust.domain.vo.ElegantUser;
import com.thoughtworks.xstream.XStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Inderjit
 */
public class AuditManager {

    private HttpURLConnection estServlet;

    public boolean connectAndDeleteData(final String hostName, final String portNo, ElegantCustomer cust) throws Exception {
        boolean deleteDone = true;
        XStream xstream = new XStream();
        cust.setAddressesXML(null);
        String custXML = xstream.toXML(cust);
        
        try {
//            estServlet = AppManager.getInstance().connectToService(hostName, portNo, "estimationService");
//            DataOutputStream out = new DataOutputStream(estServlet.getOutputStream());
//            String parameters = "arg0=deleteList&arg1=" + custXML;
//            out.writeBytes(parameters);
//            out.flush();
//            out.close();
//            int resp = estServlet.getResponseCode();
//            if (resp == 200) {
//                ObjectInputStream in = new ObjectInputStream(estServlet.getInputStream());
//                deleteDone = (Boolean) in.readObject();
//            }
//
        } catch (Exception httpException) {
            JOptionPane.showMessageDialog(null, "Server Unavailable " + httpException.getMessage());
        }

        return deleteDone;
    }

    public boolean connectAndUpdateData(final String hostName, final String portNo, ElegantCustomer cust, AddressXML adXML) throws Exception {
        boolean updateDone = true;
        XStream xstream = new XStream();
        try {
//            estServlet = AppManager.getInstance().connectToService(hostName, portNo, "estimationService");
//            writeCustomerCustService(cust, adXML);
//            int resp = estServlet.getResponseCode();
//            if (resp == 200) {
//                ObjectInputStream in = new ObjectInputStream(estServlet.getInputStream());
//                updateDone = (Boolean) in.readObject();
//            }

        } catch (Exception httpException) {
            JOptionPane.showMessageDialog(null, "Server Unavailable " + httpException.getMessage());
        }

        return updateDone;
    }

    public ArrayList<ElegantCustomer> connectAndRetriveData(final String hostName, final String portNo, ElegantUser user) throws Exception {
        ArrayList<ElegantCustomer> custListForUser = null;
        String id = new Long(user.getUserID()).toString();
        try {
//            estServlet = AppManager.getInstance().connectToService(hostName, portNo, "estimationService");
//            writeParametersCustService("getList", id);
//            int resp = estServlet.getResponseCode();
//            if (resp == 200) {
//                ObjectInputStream in = new ObjectInputStream(estServlet.getInputStream());
//                custListForUser = (ArrayList<ElegantCustomer>) in.readObject();
//            }
        } catch (Exception httpException) {
            JOptionPane.showMessageDialog(null, "Server Unavailable " + httpException.getMessage());
        }
        return custListForUser;
    }


    public boolean writeParametersCustService(String parm1, String parm2) {
        boolean done = false;
        try {
            DataOutputStream out = new DataOutputStream(estServlet.getOutputStream());
            String parameters = "arg0=" + parm1 + "&arg1=" + parm2;
            out.writeBytes(parameters);
            out.flush();
            out.close();
            done = true;
        } catch (Exception e) {
        }
        return done;
    }

    public ArrayList<ElegantCustomer> connectAndRetriveDataForRepList(final String hostName, final String portNo, ElegantUser user, Date frDt, Date toDt, Integer crdays, Double crlimit) throws Exception {
        ArrayList<ElegantCustomer> custListForUser = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String id = new Long(user.getUserID()).toString(), parm1 = sdf.format(frDt), parm2 = sdf.format(toDt);
        String parm3 = crdays.toString(), parm4 = crlimit.toString();
        try {
//            estServlet = AppManager.getInstance().connectToService(hostName, portNo, "estimationService");
//            writeParametersCustService("getrepList", id, parm1, parm2, parm3, parm4);
//            int resp = estServlet.getResponseCode();
//            if (resp == 200) {
//                ObjectInputStream in = new ObjectInputStream(estServlet.getInputStream());
//                custListForUser = (ArrayList<ElegantCustomer>) in.readObject();
//            }
        } catch (Exception httpException) {
            JOptionPane.showMessageDialog(null, "Server Unavailable " + httpException.getMessage());
        }
        return custListForUser;
    }

    public boolean writeCustomerCustService(ElegantCustomer cust, AddressXML addressXML) {
        boolean done = false;
        XStream xstream = new XStream();
        cust.setAddressesXML(null);
        String custXML = xstream.toXML(cust);
        String adressXML = xstream.toXML(addressXML);

        try {
            DataOutputStream out = new DataOutputStream(estServlet.getOutputStream());
            String parameters = "arg0=saveList" + "&arg1=" + custXML + "&arg2=" + adressXML;
            out.writeBytes(parameters);
            out.flush();
            out.close();
            done = true;
        } catch (Exception httpException) {
            JOptionPane.showMessageDialog(null, "Server Unavailable " + httpException.getMessage());
        }
        return done;
    }

    public boolean writeParametersCustService(String action, String id, String frDt, String toDt, String crDays, String crLimit) {
        boolean done = false;
        try {
            DataOutputStream out = new DataOutputStream(estServlet.getOutputStream());
            String parameters = "arg0=" + action + "&arg1=" + id + "&arg2=" + frDt + "&arg3=" + toDt + "&arg4=" + crDays + "&arg5=" + crLimit;
            out.writeBytes(parameters);
            out.flush();
            out.close();
            done = true;
        } catch (Exception e) {
        }
        return done;
    }
}
