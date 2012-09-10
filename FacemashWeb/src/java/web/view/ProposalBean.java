/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.view;

import facemash.entity.Girl;
import facemash.manager.GirlManagerLocal;
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
public class ProposalBean {

    @EJB
    GirlManagerLocal gm;
    private String text;
    private String friendImg;
    private String friendVkId;

    @PostConstruct
    private void init() {
        text = " ";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFriendImg() {
        return friendImg;
    }

    public void setFriendImg(String friendImg) {
        this.friendImg = friendImg;
    }

    public String getFriendVkId() {
        return friendVkId;
    }

    public void setFriendVkId(String friendVkId) {
        this.friendVkId = friendVkId;
    }

    public void proposeGirlFriend() {
        try {
            gm.addFriendProposal(friendVkId, friendImg);
            text = "Спасибо, ваша заявка будет рассмотрена администратором";
        } catch (Exception e) {
        }
    }

    public void propose(String vkId) {
        gm.addProposal(vkId);
        text = "Спасибо, ваша заявка будет рассмотрена администратором";
//        System.out.println("");
    }
}
