package com.view.countries;

public class Pojo {
    String pop,area,names,cap;
    public Pojo(String pop, String area, String name, String cap) {
        this.pop=pop;
        this.area=area;
        this.names=name;
        this.cap=cap;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }
}
