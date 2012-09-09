/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facemash.manager;

import facemash.entity.Girl;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rogvold
 */
@Local
public interface GirlManagerLocal {

    public boolean isGirlExits(String vkId);

    public void addGirl(String vk_id, String name, String img);

    public void addProposal(String vk_id);

    public Girl getGirlByVkId(String vkId);

    public void updateGirl(Long girlId, double newRating, int n);

    public void updateRating(Girl a, Girl b, int sA);

    public Girl getRandomGirl();

    public Girl getRandomGirlExceptForThisOne(Long girlId);

    public void importGirlsFromHtml(String filename);

    public void deleteGirls(String secret);

    public List<Girl> getAllGirls();

    public Girl getGirlById(Long girlId);

    public String getAvatarUrlFromProfile(String vkId);

    public void enlargeAllPhotos();

    public void deleteGirl(String ownerVkId, Long girlId);

    public List<Girl> getAllGirls(int amount);

    public void deleteGirl(Long girlId);

    public Girl updateGirlImage(Long girlId, String newImg);

    public void toModeration(Long girlId);

    public List<Girl> getBlackList();

    public void recoverGirl(Long girlId);

    public void enlargePictureOfGirl(Girl girl);
    
    public List<Girl> getProposals();
}
