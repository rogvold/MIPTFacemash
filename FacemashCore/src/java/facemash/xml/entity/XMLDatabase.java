package xml.entity;

import facemash.xml.entity.XMLGirl;
import java.util.List;

/**
 *
 * @author rogvold
 */
public class XMLDatabase {

    private List<XMLGirl> girls;


    public List<XMLGirl> getGirls() {
        return girls;
    }

    public void setGirls(List<XMLGirl> girls) {
        this.girls = girls;
    }
}
