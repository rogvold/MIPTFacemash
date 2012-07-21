/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.edit;

import facemash.entity.Girl;
import facemash.manager.GirlManagerLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import web.view.HttpUtils;

/**
 *
 * @author rogvold
 */
@ManagedBean
@ViewScoped
public class EditGirlBean {
    
    @EJB
    GirlManagerLocal gm;
    private String currentVkId;
    private String updatingImg;
    private Girl currentGirl;
    private Girl newGirl;
    
    public EditGirlBean() {
    }
    
    @PostConstruct
    private void init() {
        currentVkId = HttpUtils.getRequestParam("girlId");
        currentGirl = gm.getGirlByVkId(currentVkId);
        if (currentGirl == null) {
            newGirl = new Girl();
        }
    }
    
    public boolean currentGirlExists() {
        return (currentGirl != null);
    }
    
    public String getUpdatingImg() {
        return updatingImg;
    }
    
    public void setUpdatingImg(String updatingImg) {
        this.updatingImg = updatingImg;
    }
    
    public Girl getCurrentGirl() {
        return currentGirl;
    }
    
    public void setCurrentGirl(Girl currentGirl) {
        this.currentGirl = currentGirl;
    }
    
    public String getCurrentVkId() {
        return currentVkId;
    }
    
    public void setCurrentVkId(String currentVkId) {
        this.currentVkId = currentVkId;
    }
    
    public Girl getNewGirl() {
        return newGirl;
    }
    
    public void setNewGirl(Girl newGirl) {
        this.newGirl = newGirl;
    }
    
    public void addNewGirl() {
        if (newGirl.getVkId() == null) {
            return;
        }
        try {
            System.out.println("img = " + newGirl.getImg());
            if (newGirl.getImg() == null || newGirl.getImg().length() == 0) {
                gm.addGirl(newGirl.getVkId(), "new", gm.getAvatarUrlFromProfile(newGirl.getVkId()));                
            }
        } catch (Exception e) {
        }
        
        gm.addGirl(newGirl.getVkId(), "new", newGirl.getImg());
    }
    
    public void updateGirl(Long girlId) {
        gm.updateGirlImage(girlId, updatingImg);
    }
    
    public void updateRating() {
        gm.updateGirl(currentGirl.getId(), currentGirl.getRating(), currentGirl.getAmountOfTournaments());
    }
    
    public void deleteGirl(Long girlId) {
        gm.deleteGirl(girlId);
    }
}
