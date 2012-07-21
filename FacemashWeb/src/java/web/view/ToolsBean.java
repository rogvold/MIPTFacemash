/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.view;

import facemash.manager.GirlManagerLocal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author rogvold
 */
@ManagedBean
@RequestScoped
public class ToolsBean {

    @EJB
    GirlManagerLocal gm;
    
    private String vkId;
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVkId() {
        return vkId;
    }

    public void setVkId(String vkId) {
        this.vkId = vkId;
    }
    
    public void initImg(){
        setImg(gm.getAvatarUrlFromProfile(vkId));
    }
    
    public void enlarge(){
        gm.enlargeAllPhotos();
    }
    
}
