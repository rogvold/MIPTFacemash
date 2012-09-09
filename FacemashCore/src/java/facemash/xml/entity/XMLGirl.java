package facemash.xml.entity;


/**
 *
 * @author rogvold
 */
public class XMLGirl{

    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_DELETED = 1;
    public static final int STATUS_HIDDEN = 2;
    public static final int STATUS_MODERATION = 3;

    private String vkId;
    private String img;
    private String name;
    private double rating;
    private int amountOfTournaments;
    private int status;

    public XMLGirl(String vkId, String img, String name, double rating, int amountOfTournaments, int status) {
        this.vkId = vkId;
        this.img = img;
        this.name = name;
        this.rating = rating;
        this.amountOfTournaments = amountOfTournaments;
        this.status = status;
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


     public void trimAllStringFields() {
        this.vkId = this.vkId.trim();
        this.name = this.name.trim();
        this.img = this.img.trim();
    }
    

}