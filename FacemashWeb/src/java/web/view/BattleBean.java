/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.view;

import facemash.entity.Girl;
import facemash.manager.GirlManagerLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author rogvold
 */
@ManagedBean
@ViewScoped
public class BattleBean implements Serializable {

    @EJB
    GirlManagerLocal gm;
    private Girl first;
    private Girl second;

    @PostConstruct
    private void init() {
        setFirst(gm.getRandomGirl());
        setSecond(gm.getRandomGirlExceptForThisOne(first.getId()));
    }

    public Girl getFirst() {
        return first;
    }

    private void setFirst(Girl first) {
        this.first = first;
    }

    public Girl getSecond() {
        return second;
    }

    private void setSecond(Girl second) {
        this.second = second;
    }

    public void firstClicked() {
        System.out.println("first clicked; ratings:" + first.getRating() + "/" + second.getRating() + "; " + first.getVkId() + "/" + second.getVkId());
        gm.updateRating(first, second, 1);
        nexTurn();
    }

    public void secondClicked() {
        System.out.println("second clicked; ratings:" + first.getRating() + "/" + second.getRating() + "; " + first.getVkId() + "/" + second.getVkId());
        gm.updateRating(first, second, 0);
        nexTurn();
    }

    public void skipClicked() {
        System.out.println("skip clicked; ratings:" + first.getRating() + "/" + second.getRating() + "; " + first.getVkId() + "/" + second.getVkId());
        gm.updateRating(first, second, -1);
        nexTurn();
    }

    private void nexTurn() {
        try {
            setFirst(gm.getRandomGirl());
            setSecond(gm.getRandomGirlExceptForThisOne(first.getId()));
        } catch (Exception e) {
            System.out.println("nextTurn exc = " + e);
        }

    }
    
    public void addGirlToModeration(Long girlId){
        System.out.println("girl id = " + girlId + " added to black list");
        gm.toModeration(girlId);
    }
    
}
