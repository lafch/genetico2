// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bean.java
package org.richfaces;

import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ValueChangeEvent;

public class Bean {

    public Bean() {
        detail = new ArrayList();
        checked = true;
    }

    public List getDetail() {
        return detail;
    }

    public void setDetail(List detail) {
        this.detail = detail;
    }

    public String getMounth() {
        return mounth;
    }

    public void setMounth(String mounth) {
        this.mounth = mounth;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isChecked() {
        System.out.println((new StringBuilder()).append("Invoke get checked for mounth ").append(getMounth()).append(", checked:").append(checked).toString());
        return checked;
    }

    public void setChecked(boolean checked) {
        System.out.println((new StringBuilder()).append("Invoke set checked for mounth ").append(getMounth()).append(", checked:").append(checked).toString());
        this.checked = checked;
    }

    public String check() {
        checked = !checked;
        System.out.println((new StringBuilder()).append("Invoke check action for mounth ").append(getMounth()).append(", checked:").append(checked).toString());
        return null;
    }

    public void checkChanged(ValueChangeEvent event) {
        System.out.println((new StringBuilder()).append("Checked changed for mounth ").append(getMounth()).toString());
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    private List detail;
    private String mounth;
    private int total;
    private int price;
    private boolean checked;
}
