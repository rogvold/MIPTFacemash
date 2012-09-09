/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.view;

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

    public void propose(String vkId) {
        gm.addProposal(vkId);
        text = "Спасибо, ваша заявка будет рассмотрена администратором";
//        System.out.println("");
    }
}
