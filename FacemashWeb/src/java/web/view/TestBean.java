/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.view;

import facemash.entity.Girl;
import facemash.manager.GirlManagerLocal;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rogvold
 */
@ManagedBean(name="testBean")
@ViewScoped
public class TestBean implements Serializable{
    @EJB
    GirlManagerLocal gm;
    

    public TestBean(){
    }

        
    public List<Girl> getAllGirls(){
        return gm.getAllGirls();
    }
    
    public double shortDouble(double d){
        return Math.ceil(d*100)/100;
    }
    
    public String colorByCount(int k){
        if (k == 1) return "#FFD700";
        if (k==2) return "#C0C0C0";
        if (k==3) return "#CD7F32";
        return "black";
    };
    
}
