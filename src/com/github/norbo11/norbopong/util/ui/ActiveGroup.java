package com.github.norbo11.norbopong.util.ui;

import java.util.ArrayList;
import java.util.Iterator;

import com.github.norbo11.norbopong.util.interfaces.GroupableByActivity;

public class ActiveGroup<T extends GroupableByActivity> implements Iterable<T>
{
    private ArrayList<T> items = new ArrayList<>();
    
    @SafeVarargs
    public ActiveGroup(T... items)
    {
        for (T tb : items)
        {
            this.items.add(tb);
            tb.setGroup(this);
        }
    }

    public void setActiveItem(T item)
    {        
        for (T tb : items)
        {
            if (tb != item)
            {
                tb.setActive(false);
            }
        }
    }

    public ArrayList<T> getTextBoxes()
    {
        return items;
    }

    @Override
    public Iterator<T> iterator()
    {
        return items.iterator();
    }
}
