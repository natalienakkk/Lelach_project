//package il.cshaifasweng.OCSFMediatorExample.entities;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//@Table(name = "images")
//
//public class Image implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Column(name = "Image_name")
//    private String name;
//    private String imgURL;
//
//    public Image() {
//    }
//
//    public String getImgURL() {
//        return imgURL;
//    }
//
//    public void setImgURL(String imgURL) {
//        this.imgURL = imgURL;
//    }
//
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "image")
//    private Item item;
//
//    public Image(String imgURL) {
//        this.imgURL = imgURL;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//
//    public int getId() {
//        return id;
//    }
//
//}
