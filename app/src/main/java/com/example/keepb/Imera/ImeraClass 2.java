package com.example.keepb.Imera;

public class ImeraClass {

    private long  idday;
    private String imera;
    private String breaktime;
    private String breakwhat;
    private String dectime;
    private String decwhat;
    private String mesitime;
    private String mesiwhat;
    private String apotime;
    private String apowhat;
    private String launchtime;
    private String launchwhat;
    private String water;
    private String alcool;
    private String gym;
    private String ypnos;
    private String epileon;
    private String anapsiktiko;

    public ImeraClass( String imera, String breaktime, String breakwhat, String dectime, String decwhat, String mesitime, String mesiwhat, String apotime, String apowhat, String launchtime, String launchwhat, String water, String alcool,String anapsiktiko ,String gym, String ypnos, String epileon) {

        this.imera = imera;
        this.breaktime = breaktime;
        this.breakwhat = breakwhat;
        this.dectime = dectime;
        this.decwhat = decwhat;
        this.mesitime = mesitime;
        this.mesiwhat = mesiwhat;
        this.apotime = apotime;
        this.apowhat = apowhat;
        this.launchtime = launchtime;
        this.launchwhat = launchwhat;
        this.water = water;
        this.alcool = alcool;
        this.anapsiktiko =anapsiktiko;
        this.gym = gym;
        this.ypnos = ypnos;
        this.epileon = epileon;

    }



    public  ImeraClass(){

    }

    public String getAnapsiktiko() {
        return anapsiktiko;
    }

    public void setAnapsiktiko(String anapsiktiko) {
        this.anapsiktiko = anapsiktiko;
    }

    public long getIdday() {
        return idday;
    }

    public void setIdday(long idday) {
        this.idday = idday;
    }

    public String getImera() {
        return imera;
    }

    public void setImera(String imera) {
        this.imera = imera;
    }

    public String getBreaktime() {
        return breaktime;
    }

    public void setBreaktime(String breaktime) {
        this.breaktime = breaktime;
    }

    public String getBreakwhat() {
        return breakwhat;
    }

    public void setBreakwhat(String breakwhat) {
        this.breakwhat = breakwhat;
    }

    public String getDectime() {
        return dectime;
    }

    public void setDectime(String dectime) {
        this.dectime = dectime;
    }

    public String getDecwhat() {
        return decwhat;
    }

    public void setDecwhat(String decwhat) {
        this.decwhat = decwhat;
    }

    public String getMesitime() {
        return mesitime;
    }

    public void setMesitime(String mesitime) {
        this.mesitime = mesitime;
    }

    public String getMesiwhat() {
        return mesiwhat;
    }

    public void setMesiwhat(String mesiwhat) {
        this.mesiwhat = mesiwhat;
    }

    public String getApotime() {
        return apotime;
    }

    public void setApotime(String apotime) {
        this.apotime = apotime;
    }

    public String getApowhat() {
        return apowhat;
    }

    public void setApowhat(String apowhat) {
        this.apowhat = apowhat;
    }

    public String getLaunchtime() {
        return launchtime;
    }

    public void setLaunchtime(String launchtime) {
        this.launchtime = launchtime;
    }

    public String getLaunchwhat() {
        return launchwhat;
    }

    public void setLaunchwhat(String launchwhat) {
        this.launchwhat = launchwhat;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getAlcool() {
        return alcool;
    }

    public void setAlcool(String alcool) {
        this.alcool = alcool;
    }

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

    public String getYpnos() {
        return ypnos;
    }

    public void setYpnos(String ypnos) {
        this.ypnos = ypnos;
    }

    public String getEpileon() {
        return epileon;
    }

    public void setEpileon(String epileon) {
        this.epileon = epileon;
    }
}