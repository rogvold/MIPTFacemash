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
public interface XMLImporterLocal {
    
    public void updateDB(String xml);
    
    public void updateDB(String xml,String password);
    
    public void addUsersByHtml(String html);
    
    public void addUsersWithLargeAvatarsByHtml(String html);
    
}
