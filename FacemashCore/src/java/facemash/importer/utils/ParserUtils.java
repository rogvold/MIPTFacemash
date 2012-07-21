/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facemash.importer.utils;

import facemash.entity.Girl;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author rogvold
 */
public class ParserUtils {

    public static final int TIMEOUT = 3000;

    public static String getShort(String s) {
        int l = s.lastIndexOf("/") + 1;
        return s.substring(l);
    }

    public static String getAvatarUrlFromProfile(String vkId) {
        try {
            System.out.println("try to get large avatar for vkId = " + vkId);
            Document doc = Jsoup.parse(new URL("http://vk.com/" + vkId), TIMEOUT);
            Element element = doc.select("div[id=profile_avatar] a img").first();
            return element.attr("src");
        } catch (Exception ex) {
            System.out.println("cannot get large avatar");
        }
        return null;
    }

    public static List<Girl> getUsersListFromPage(File file) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(file);
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        byte[] byteArray = new byte[(int) file.length()];
        dataInputStream.readFully(byteArray);
        String html = new String(byteArray);
        Document doc = Jsoup.parse(html);



//        String prefix = file.getName();
//        prefix = prefix.substring(0, prefix.indexOf("."))+"_files/";

        System.out.println("starting parsing ...");
//        Elements elements = doc.select("div[class=people_row three_col_row clear_fix] div[class=img search_bigph_wrap fl_l] a");
//        List<User> list = new ArrayList();
//        for (Element el : elements){
//            if (el.attributes().size() == 2) 
//                System.out.println("candidate = " + el);
//        }
        Elements elements = doc.select("div[class=people_row three_col_row clear_fix]");
//        System.out.println("elements = " + elements);
        Element a;
        String img_src, id, name;
        List<Girl> list = new ArrayList();
        for (Element el : elements) {
            a = el.select("div[class=img search_bigph_wrap fl_l] a").first();
            if (a.attributes().size() != 2) {
                continue;
            }

            img_src = el.select("div[class=img search_bigph_wrap fl_l] a img").first().attr("src");
            name = el.select("div[class=info fl_l] div[class=labeled name] a").first().text();
            id = a.attr("href");

            if (img_src.indexOf("camera_b.gif") >= 0) {
                continue;
            }
            //!!!
//            list.add(new Girl(getShort(id), prefix + getShort(img_src), name, 0.0));
            list.add(new Girl(getShort(id), img_src, name, 0.0));
        }
        return list;
    }
}
