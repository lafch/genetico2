// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Data.java
package org.richfaces;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

// Referenced classes of package org.richfaces:
//            Bean, ChildBean
public class Data {

    public boolean isC3rendered() {
        return c3rendered;
    }

    public void setC3rendered(boolean c3rendered) {
        this.c3rendered = c3rendered;
    }

    public String toggleColumn() {
        c3rendered = !c3rendered;
        return null;
    }

    public List getNumbers() {
        return numbers;
    }

    public void setNumbers(List numbers) {
        this.numbers = numbers;
    }

    public Data() {
        mounths = new ArrayList();
        numbers = new ArrayList();
        c3rendered = true;
        c2rendered = true;
        Properties properties = System.getProperties();
        Enumeration keys = properties.keys();
        for (int i = 0; i < 5; i++) {
            Bean bean = new Bean();
            int l = (int) (Math.random() * 5D) + 1;
            bean.setTotal(0);
            bean.setMounth(mnames[i]);
            mounths.add(bean);
            for (int j = 0; j < l; j++) {
                ChildBean child = new ChildBean();
                child.setName((String) keys.nextElement());
                int qty = (int) (Math.random() * 10D);
                bean.setTotal(bean.getTotal() + qty);
                child.setQty(qty);
                bean.getDetail().add(child);
            }

        }

        for (int i = 0; i < 16; i++) {
            numbers.add(new Integer(i));
        }

    }

    public List getMounths() {
        return mounths;
    }

    public void setMounths(List mounths) {
        this.mounths = mounths;
    }

    public boolean isC2rendered() {
        return c2rendered;
    }

    public void setC2rendered(boolean c2rendered) {
        this.c2rendered = c2rendered;
    }

    public int getC2span() {
        return c3rendered ? 1 : 2;
    }

    public int getC1span() {
        int i = c2rendered ? 1 : ((int) (c3rendered ? 2 : 3));
        return i;
    }
    private static final String mnames[] = {
        "Jan", "Feb", "Mar", "Apr", "May"
    };
    private List mounths;
    private List numbers;
    private boolean c3rendered;
    private boolean c2rendered;
}
