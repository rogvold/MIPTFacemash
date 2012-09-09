/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.view;

import facemash.manager.XMLExporterLocal;
import facemash.manager.XMLImporterLocal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author rogvold
 */
@ManagedBean
public class XmlBean {

    @EJB
    XMLExporterLocal xmlExp;
    
    @EJB
    XMLImporterLocal xmlImp;
    
    private String password;
    private String xml;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }
    
    public void updateDB(){
        xmlImp.updateDB(xml, password);
    }
    
    public String getDatabase() {
        return xmlExp.getXMLStringFromDatabase();
    }
}
