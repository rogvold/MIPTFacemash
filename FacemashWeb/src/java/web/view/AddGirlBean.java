/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.view;

import facemash.manager.GirlManagerLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author rogvold
 */
@ManagedBean(name = "addGirlBean")
@ViewScoped
public class AddGirlBean implements Serializable {

    @EJB
    GirlManagerLocal gm;
    private String vkId;
    private String name;
    private String img;
    private String filename;
    private String secret;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public AddGirlBean() {
//     filename = "1.html";
    }

    public void addGirls() {
        gm.importGirlsFromHtml(filename);
    }

    public void deleteGirls() {
        gm.deleteGirls(secret);
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVkId() {
        return vkId;
    }

    public void setVkId(String vkId) {
        this.vkId = vkId;
    }

    public void addGirl() {
        gm.addGirl(vkId, name, img);
        System.out.println("girl added (vkId = " + vkId);
    }
}
