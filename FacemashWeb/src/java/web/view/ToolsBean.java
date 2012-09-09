/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.view;

import facemash.manager.GirlManagerLocal;
import facemash.manager.XMLImporterLocal;
import javax.annotation.PostConstruct;
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
    
    @EJB
    XMLImporterLocal xmlImp;
    
    
    private String generatedXML;
    
    private String text;
    
    private String vkId;
    private String img;
    private String inputHtml;
    
    public String getImg() {
        return img;
    }

    @PostConstruct
    private void init(){
        text = "ready";
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

    public String getGeneratedXML() {
        return generatedXML;
    }

    public void setGeneratedXML(String generatedXML) {
        this.generatedXML = generatedXML;
    }

    public String getInputHtml() {
        return inputHtml;
    }

    public void setInputHtml(String inputHtml) {
        this.inputHtml = inputHtml;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
    
    public void addGirlsWithLargePhotos(){
        xmlImp.addUsersWithLargeAvatarsByHtml(inputHtml);
        text = "finished";
    }
    
}
