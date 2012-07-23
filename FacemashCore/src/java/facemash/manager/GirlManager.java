/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facemash.manager;

import facemash.entity.Girl;
import facemash.importer.utils.ParserUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author rogvold
 */
@Stateless
public class GirlManager implements GirlManagerLocal {

    @PersistenceContext(unitName = "FacemashPU")
    EntityManager em;
    private static Random random = new Random();
    public static final int MAX_RANDOM = 100000000;
    public static final int MAX_GIRLS_AMOUNT_IN_LIST = 50;

    @Override
    public void addGirl(String vk_id, String name, String img) {
//        EntityManager em = db.getEntityManager();
        try {
            Girl girl = new Girl(vk_id, img, name, 100.0);
            if (em == null) {
                System.out.println("em is null");
            }
            if (getGirlByVkId(vk_id) == null) {
                em.persist(girl);
                System.out.println("added new girl vkId = " + vk_id);
            }
        } catch (Exception ex) {
            System.out.println("exc = " + ex);
//            db.rollback();
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    private boolean alreadyEnlarged(String s) {
        return (s.indexOf("a_") >= 0);
    }

    @Override
    public Girl getGirlByVkId(String vkId) {
//        EntityManager em = db.getEntityManager();
        if (vkId == null) {
            return null;
        }
        try {
            Query q = em.createQuery("select g from Girl g where g.vkId='" + vkId + "'");
            Girl girl = null;
            List<Girl> list = q.getResultList();
            if ((list != null) && (!list.isEmpty())) {
                return list.get(0);
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }

    @Override
    public void updateGirl(Long girlId, double newRating, int n) {
//        EntityManager em = db.getEntityManager();
        try {
            Girl girl = em.find(Girl.class, girlId);
            girl.setRating(newRating);
            girl.setAmountOfTournaments(n);
            Girl merge = em.merge(girl);
        } catch (Exception ex) {
//            db.rollback();
        }
    }

    private int getK(Girl girl) {
        if (girl.getAmountOfTournaments() <= 30) {
            return 30;
        }
        if (girl.getRating() <= 2400) {
            return 15;
        }
        if (girl.getRating() >= 2400) {
            return 10;
        }
        return 10;
    }

    @Override
    public void updateRating(Girl a, Girl b, int sA) {
        try {
            if (sA == -1) {
                return;
            }
            double Ea = 1.0 / (1 + Math.pow(10.0, (b.getRating() - a.getRating()) / 400.0));
            double Eb = 1.0 / (1 + Math.pow(10.0, (a.getRating() - b.getRating()) / 400.0));
            int sB = 0;
            if (sA == 0) {
                sB = 1;
            }
            a.setRating(a.getRating() + getK(a) * (sA - Ea));
            b.setRating(b.getRating() + getK(b) * (sB - Eb));
            updateGirl(a.getId(), a.getRating(), a.getAmountOfTournaments() + 1);
            updateGirl(b.getId(), b.getRating(), b.getAmountOfTournaments() + 1);

        } catch (Exception e) {
            System.out.println("updateRating(Girl a, Girl b, int sA) exc = " + e);
        }

    }

    @Override
    public Girl getRandomGirl() {
        try {
            List<Girl> list = em.createQuery("select g from Girl g where g.status= " + Girl.STATUS_NORMAL + " order by g.id asc").getResultList();
            int r = random.nextInt(MAX_RANDOM) % list.size();
            return list.get(r);
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Girl getRandomGirlExceptForThisOne(Long girlId) {
        if (girlId == null) {
            return null;
        }
        Girl girl = null;
        try {
            while (true) {
                girl = getRandomGirl();
                if (girl.getId().equals(girlId) == false) {
                    break;
                }
            }
            return girl;
        } catch (Exception e) {
            return null;
        }

    }

    private void saveToDatabase(List<Girl> girls) {
        if (girls == null) {
            System.out.println("void list....");
            return;
        }
        System.out.println("saving to DB...");
        System.out.println("girls list = " + girls);
        for (Girl g : girls) {
            addGirl(g.getVkId(), g.getName(), g.getImg());
        }
    }

    @Override
    public void importGirlsFromHtml(String filename) {
        try {
            System.out.println("importGirlsFromHtml(): filename = " + filename);
            File file = new File("" + filename);

//            System.out.println("shortfilename = " + filename.substring(0,filename.indexOf(".")));
            List<Girl> list = ParserUtils.getUsersListFromPage(file);
            saveToDatabase(list);
        } catch (IOException ex) {
            System.out.println("exception : ex = " + ex);
            System.out.println("file not found ;");
            Logger.getLogger(GirlManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteGirls(String secret) {
        System.out.println("deleting all girls");
        if (secret.equals("sabirmipt") == false) {
            return;
        }
        try {
            Query query = em.createQuery("delete from Girl g ");
            int deleted = query.executeUpdate();
        } catch (Exception e) {
            System.out.println("EJB:deleteGirls exc = " + e);
        }


    }

    @Override
    public List<Girl> getAllGirls() {
        try {
            Query q = em.createQuery("select g from Girl g where g.status= " + Girl.STATUS_NORMAL + " order by g.rating desc");
            q.setMaxResults(MAX_GIRLS_AMOUNT_IN_LIST);
            return q.getResultList();
        } catch (Exception exc) {
            System.out.println("EJB:getAllGirls: exc = " + exc);
        }
        return null;

    }

    @Override
    public Girl getGirlById(Long girlId) {
        if (girlId == null) {
            return null;
        }
        try {
            return em.find(Girl.class, girlId);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public String getAvatarUrlFromProfile(String vkId) {
        return ParserUtils.getAvatarUrlFromProfile(vkId);
    }

    private void enlargePictureOfGirl(Girl girl) {
        if (alreadyEnlarged(girl.getImg())) {
            System.out.println("already enlarged");
            return;
        }
        String s = ParserUtils.getAvatarUrlFromProfile(girl.getVkId());
        if (s != null) {
            System.out.println("picture of girl enlarged (vkId = " + girl.getVkId() + ")");
            girl.setImg(s);
            em.merge(girl);
        }

    }

    @Override
    public void enlargeAllPhotos() {
        System.out.println("enlarging all photos...");
        List<Girl> list = getAllGirls(100000);
        int i = 0;
        for (Girl g : list) {
            i++;
            System.out.println(i + ")");
            enlargePictureOfGirl(g);
        }
    }

    @Override
    public List<Girl> getAllGirls(int amount) {
        try {
            Query q = em.createQuery("select g from Girl g where g.status= " + Girl.STATUS_NORMAL + "  order by g.rating asc");
            q.setMaxResults(amount);
            return q.getResultList();
        } catch (Exception exc) {
            System.out.println("EJB:getAllGirls: exc = " + exc);
        }
        return null;
    }

    @Override
    public void deleteGirl(Long girlId) {
        try {
            if (girlId == null) {
                return;
            }
            Girl g = em.find(Girl.class, girlId);
            System.out.println("removing girl id = " + girlId);
//            em.
//            g.setStatus(Girl.STATUS_DELETED);
//            em.merge(g);
            em.remove(g);
//            em.getTransaction().commit();
//            Query q = em.createQuery("delete from Girl g where g.id=" + girlId);
//            q.executeUpdate();
        } catch (Exception e) {
            System.out.println("cannot erase girl exc = " + e);
        }
    }

    @Override
    public Girl updateGirlImage(Long girlId, String newImg) {
        System.out.println("updateGirl: girlId = " + girlId + " / newImg = " + newImg);
        if (girlId == null) {
            return null;
        }
        try {

            Girl girl = em.find(Girl.class, girlId);
            if (newImg == null) {
                return girl;
            }
            girl.setImg(newImg);
            girl = em.merge(girl);
            return girl;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void toModeration(Long girlId) {
//        throw new UnsupportedOperationException("Not supported yet.");
        try {
            Girl g = em.find(Girl.class, girlId);
            g.setStatus(Girl.STATUS_MODERATION);
            em.merge(g);
        } catch (Exception e) {
            System.out.println("cannot add girl to moderation");
        }
    }

    @Override
    public List<Girl> getBlackList() {
//        throw new UnsupportedOperationException("Not supported yet.");
        try {
            Query q = em.createQuery("select g from Girl g where g.status=" + Girl.STATUS_MODERATION + " order by g.id asc");
            List<Girl> list = q.getResultList();
            return list;
        } catch (Exception e) {
            System.out.println("cannot get black list exc = " + e);
        }
        return null;
    }

    @Override
    public void recoverGirl(Long girlId) {
        try {
            Girl g = em.find(Girl.class, girlId);
            g.setStatus(Girl.STATUS_NORMAL);
            em.merge(g);
        } catch (Exception e) {
            System.out.println("cannot recover girl; exc = " + e);
        }
    }
}
