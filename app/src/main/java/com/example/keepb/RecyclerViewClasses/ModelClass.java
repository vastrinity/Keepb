package com.example.keepb.RecyclerViewClasses;

public class ModelClass {



    private long id;
    private String date;
    private String kila;
    private String lipos;
    private String ygra;

    private String mys;
    private String meta_age;
    private String fat_level;








    //CONSTRUCTOR

    public ModelClass( String date, String kila, String lipos, String ygra, String mys, String meta_age, String fat_level) {

        this.date = date;
        this.kila = kila;
        this.lipos = lipos;
        this.ygra = ygra;
        this.mys = mys;
        this.meta_age = meta_age;
        this.fat_level = fat_level;


    }

    public ModelClass() {

    }
//GETTERS


    public String getDate() {
        return date;
    }

    public String getKila() {
        return kila;
    }

    public String getLipos() {
        return lipos;
    }

    public String getYgra() {
        return ygra;
    }

    public String getMys() {
        return mys;
    }

    public String getMeta_age() {
        return meta_age;
    }

    public String getFat_level() {
        return fat_level;
    }






//SETTERS


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setKila(String kila) {
        this.kila = kila;
    }

    public void setLipos(String lipos) {
        this.lipos = lipos;
    }

    public void setYgra(String ygra) {
        this.ygra = ygra;
    }

    public void setMys(String mys) {
        this.mys = mys;
    }

    public void setMeta_age(String meta_age) {
        this.meta_age = meta_age;
    }

    public void setFat_level(String fat_level) {
        this.fat_level = fat_level;
    }
}
