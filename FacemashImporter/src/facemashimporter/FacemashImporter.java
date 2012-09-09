/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facemashimporter;

import facemash.entity.Girl;
import facemash.importer.utils.ParserUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author rogvold
 */
public class FacemashImporter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        List<Girl> girls = ParserUtils.getUsersListFromPage(new File("/home/rogvold/Documents/GitHub/MIPTFacemash/MIPTFacemash/FacemashImporter/1.html"));
        
        System.out.println(girls);
    }
}
