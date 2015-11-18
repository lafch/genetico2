package net.uch.cursoLibre.managedBeans.beans;

import org.richfaces.model.Ordering;

public class Column {

    String sToolTip = "";
    String name;
    Ordering ordering;
    String filterValue;

    public Column( String name ) {
        this.name = name;
    }

    public Column( String name, String sToolTip ) {
        this.name = name;
        this.sToolTip = sToolTip;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public void setOrdering( Ordering ordering ) {
        this.ordering = ordering;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue( String filterValue ) {
        this.filterValue = filterValue;
    }

    public String getToolTip() {
        return sToolTip;
    }

    public void setToolTip( String sToolTip ) {
        this.sToolTip = sToolTip;
    }
    
    
}
