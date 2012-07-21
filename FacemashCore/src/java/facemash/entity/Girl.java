/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facemash.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author rogvold
 */
@Entity
@Table
public class Girl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String vkId;
    @Column
    private String img;
    @Column
    private String name;

    @Column
    private double rating;
    
    @Column
    private int amountOfTournaments;
    
    public Girl() {
    }

    public Girl(String vkId, String img, String name, double rating) {
        this.vkId = vkId;
        this.img = img;
        this.name = name;
        this.rating = rating;
        this.amountOfTournaments = 0;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getAmountOfTournaments() {
        return amountOfTournaments;
    }

    public void setAmountOfTournaments(int amountOfTournaments) {
        this.amountOfTournaments = amountOfTournaments;
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

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Girl)) {
            return false;
        }
        Girl other = (Girl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "facemash.entity.Girl[ id=" + id + " ]";
    }
}