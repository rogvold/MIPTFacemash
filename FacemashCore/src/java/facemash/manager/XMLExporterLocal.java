/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facemash.manager;

import javax.ejb.Local;

/**
 *
 * @author rogvold
 */
@Local
public interface XMLExporterLocal {
 
    public String getXMLStringFromDatabase();
    
}
