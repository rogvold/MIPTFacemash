/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facemash.entity;

import java.io.Serializable;


/**
 *
 * @author rogvold
 */

public class Girl implements Serializable {

    public static final double DEFAULT_RATING = 400.0;
    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_DELETED = 1;
    public static final int STATUS_HIDDEN = 2;
    public static final int STATUS_MODERATION = 3;
    public static final int STATUS_HIDDEN_FRIEND = 4;
    private static final long serialVersionUID = 1L;

    private Long id;

    private String vkId;

    private String img;

    private String name;

    private double rating;

    private int amountOfTournaments;

    private int status;

    public Girl() {
    }

    public Girl(String vkId, String img, String name, double rating) {
        this.vkId = vkId;
        this.img = img;
        this.name = name;
        this.rating = rating;
        this.amountOfTournaments = 0;
    }

    public Girl(String vkId, String img, String name, double rating, int status, int amountOfTournaments) {
        this.vkId = vkId;
        this.img = img;
        this.name = name;
        this.rating = rating;
        this.status = status;
        this.amountOfTournaments = amountOfTournaments;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        return "\n facemash.entity.Girl[ id=" + id + "\n vkId = "+vkId+"; \n img_src = "+img+" ]";
    }
}