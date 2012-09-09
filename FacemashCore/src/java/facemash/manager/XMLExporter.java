/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facemash.manager;

import com.thoughtworks.xstream.XStream;
import facemash.entity.Girl;
import facemash.xml.entity.XMLGirl;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import xml.entity.XMLDatabase;

/**
 *
 * @author rogvold
 */
@Stateless
public class XMLExporter implements XMLExporterLocal {

    @PersistenceContext(unitName = "FacemashPU")
    EntityManager em;

    private List<Girl> getAllGirlsInDatabase() {
        Query q = em.createQuery("select g from Girl g");
        try {
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("getAllGirlsInDatabase(): exception occured ; exc = " + e);
            return null;
        }
    }

    private List<XMLGirl> getAllXMLGirlsInDatabase() {
        List<Girl> glist = getAllGirlsInDatabase();
        List<XMLGirl> xlist = new ArrayList();
        for (Girl g : glist) {
            xlist.add(girlToXMLGirl(g));
        }
        return xlist;
    }

    private XMLGirl girlToXMLGirl(Girl girl) {
        XMLGirl xmlGirl = new XMLGirl(girl.getVkId(), girl.getImg(), girl.getName(), girl.getRating(), girl.getAmountOfTournaments(), girl.getStatus());
        return xmlGirl;
    }

    @Override
    public String getXMLStringFromDatabase() {
        try {
            XMLDatabase xdb = new XMLDatabase();
            xdb.setGirls(getAllXMLGirlsInDatabase());
            XStream xs = new XStream();
            xs.alias("girl", XMLGirl.class);
            xs.alias("root", XMLDatabase.class);
            String xml = xs.toXML(xdb);
            return xml;
        } catch (Exception e) {
            System.out.println("getXMLStringFromDatabase() exception exc = " + e.toString());
            return null;
        }
    }
}
