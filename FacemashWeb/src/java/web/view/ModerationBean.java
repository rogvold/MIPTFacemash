/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.view;

import facemash.entity.Girl;
import facemash.manager.GirlManagerLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author rogvold
 */
@ManagedBean(name = "moderationBean")
@SessionScoped
public class ModerationBean implements Serializable {

    @EJB
    GirlManagerLocal gm;
    public static final int pSize = 20;
    private List<Girl> fullList;
    private List<Girl> shortList;
    private int page;
    private String updatingImg;


    public String getUpdatingImg() {
        return updatingImg;
    }

    public void setUpdatingImg(String updatingImg) {
        this.updatingImg = updatingImg;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Girl> getShortList() {
        return shortList;
    }

    @PostConstruct
    private void init() {
        fullList = gm.getAllGirls(10000);
        System.out.println("fulllist lendth=" + fullList.size());
        initShort();
    }

    private void initShort() {
        shortList = fullList.subList(page * pSize, (page + 1) * pSize);
        page += 1;
    }

    public List<Girl> getAllGirls() {
        return gm.getAllGirls(10000);
    }

    public void deleteGirl(Long girlId) {
        gm.deleteGirl(girlId);
    }

    public void deleteGirl(String ownerVkId, Long ownerId) {
        gm.deleteGirl(ownerVkId, ownerId);
    }

    public void deleteGirl2(String ownerVkId) {
        try {
            Girl g = gm.getGirlByVkId(ownerVkId);
            gm.deleteGirl(g.getId());
        } catch (Exception e) {
        }
    }

    public void updateGirl(Long girlId) {
        gm.updateGirlImage(girlId, updatingImg);
    }

    public void updateGirl2(String vkId) {
        try {
            Girl g = gm.getGirlByVkId(vkId);
            gm.updateGirlImage(g.getId(), updatingImg);
        } catch (Exception e) {
        }
    }



//    public void updateGirl(String ownerVkId,Long girlId) {
//        gm.updateGirlImage(girlId, updatingImg);
//    }
    public String imgByVkId(String vkId) {
        Girl g = gm.getGirlByVkId(vkId);
        try {
            return g.getImg();
        } catch (Exception e) {
            return "";
        }

    }

    public double ratingByVkId(String vkId) {
        try {
            Girl g = gm.getGirlByVkId(vkId);
            return g.getRating();
        } catch (Exception e) {
            return -1.0;
        }
    }

    public void nextPage() {
        System.out.println("nextPage...");
        initShort();
    }

    public List<Girl> getBlackList() {
        return gm.getBlackList();
    }

    public void recover(Long girlId) {
        gm.recoverGirl(girlId);
    }

}
