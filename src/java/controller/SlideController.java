/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.SlideModel;
import persistence.Slide;

/**
 *
 * @author M4700
 */
@ManagedBean(name = "slideControlle")
//@RequestScoped
@ViewScoped
public class SlideController implements Serializable {

    private ArrayList<String> images;

    public SlideController() {
        images = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            images.add("slide" + i + ".png");
        }
    }

    public ArrayList<String> getImages() {
        return images;
    }
//    ArrayList<Slide> arr;
//    private List<String> images;
//    SlideModel slmd = new SlideModel();
//
//     @PostConstruct
//    public void init() {
//        arr = slmd.getAll();
//        images = new ArrayList<>();
//        Slide sl ;
//        String name;
//         for (int i = 0; i < arr.size(); i++) {
//           sl = arr.get(i);
//           name = sl.getName();
//           images.add(name);
//         }
//    }
//
//    public List<String> getImages() {
////        for (int i = 0; i < arr.size(); i++) {
////          System.out.println("ten slide: "+ images.get(i));
////        }
////            
//        return images;
//        
//    }
//    

//    public static void main(String[] args) {
//        SlideController s = new SlideController();
//        s.init();
//        s.getImages();
//    }
}
