/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facemash.manager;

import com.thoughtworks.xstream.XStream;
import facemash.entity.Girl;
import facemash.importer.utils.ParserUtils;
import facemash.xml.entity.XMLGirl;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import xml.entity.XMLDatabase;

/**
 *
 * @author rogvold
 */
@Stateless
public class XMLImporter implements XMLImporterLocal {

    private static final String HARDCODED_PASSWORD = "hardcode";
    @PersistenceContext(unitName = "FacemashPU")
    EntityManager em;
    
    @EJB
    GirlManagerLocal gm;

    private boolean girlExists(String vkId) {
        Query q = em.createQuery("select g from Girl g where g.vkId = '" + vkId + "'");
        try {
            Girl g = (Girl) q.getSingleResult();
            return true;
        } catch (NoResultException nre) {
            return false;
        } catch (NonUniqueResultException nure) {
            return true;
        }
    }

    private void deleteAllDataFromDatabase() {
        Query q;
        try {
            q = em.createNativeQuery("delete from Girl ");
            q.executeUpdate();
        } catch (Exception e) {
            System.out.println("Exception occured while deleting all data from database... exc = " + e.toString());
        }
    }

    private void addOneGirl(String vkId, String img, int status, int amountOfTournaments, double rating) {
        if (girlExists(vkId)) {
            System.out.println("Girl (vkId = " + vkId + ") already exists");
            return;
        }
        Girl g = new Girl(vkId, img, "default", rating, status, amountOfTournaments);
        em.merge(g);
        System.out.println("new girl successfully added to database");
    }

    private void addOneGirlWithLargeAvatar(String vkId, String img, int status, int amountOfTournaments, double rating) {
        if (girlExists(vkId)) {
            System.out.println("Girl (vkId = " + vkId + ") already exists");
            return;
        }
        Girl g = new Girl(vkId, img, "default", rating, status, amountOfTournaments);
        g = em.merge(g);
        gm.enlargePictureOfGirl(g);
        System.out.println("new girl successfully added to database with trying to enlarge photo");
    }

    private void addOneGirlFromXMLGirl(XMLGirl xmlGirl) {
        addOneGirl(xmlGirl.getVkId(), xmlGirl.getImg(), xmlGirl.getStatus(), xmlGirl.getAmountOfTournaments(), xmlGirl.getRating());
    }

    private void addGirlsFromXMLGirlList(List<XMLGirl> list) {
        if (list == null) {
            System.out.println("addGirlsFromXMLGirlList(): list is null");
            return;
        }
        for (XMLGirl xg : list) {
            addOneGirlFromXMLGirl(xg);
        }
    }

    private List<XMLGirl> getXMLGirlListFromXMLString(String xml) {
        try {
            XStream xs = new XStream();
            xs.alias("girl", XMLGirl.class);
            xs.alias("root", XMLDatabase.class);
            XMLDatabase xdb = (XMLDatabase) xs.fromXML(xml);
            return xdb.getGirls();
        } catch (Exception e) {
            System.out.println("getXMLGirlListFromXMLString() exception : " + e.toString());
        }
        return null;
    }

    private void addGirlsFromXMLString(String xml) {
        addGirlsFromXMLGirlList(getXMLGirlListFromXMLString(xml));
    }

    @Override
    public void updateDB(String xml) {
        deleteAllDataFromDatabase();
        addGirlsFromXMLString(xml);
    }

    @Override
    public void updateDB(String xml, String password) {
        if (password.equals(XMLImporter.HARDCODED_PASSWORD) == false) {
            return;
        }
        updateDB(xml);
    }

    @Override
    public void addUsersByHtml(String html) {
        try {
            List<Girl> girls = ParserUtils.getUsersListFromPage(html);
            for (Girl g : girls) {
                addOneGirl(g.getVkId(), g.getImg(), g.getStatus(), 0, Girl.DEFAULT_RATING);
            }
        } catch (Exception e) {
            System.out.println("addUsersByHtml(String html) : exception  exc = " + e);
        }
    }

    @Override
    public void addUsersWithLargeAvatarsByHtml(String html) {
        if (html == null) return;
        try {
            List<Girl> girls = ParserUtils.getUsersListFromPage(html);
            for (Girl g : girls) {
                System.out.println("adding girl g = " + g);
                addOneGirlWithLargeAvatar(g.getVkId(), g.getImg(), g.getStatus(), 0, Girl.DEFAULT_RATING);
            }
        } catch (Exception e) {
            System.out.println("addUsersByHtml(String html) : exception  exc = " + e);
        }
    }
}
