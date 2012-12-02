package com.github.norbo11.norbopong.util.interfaces;

import com.github.norbo11.norbopong.util.ui.ActiveGroup;

public interface GroupableByActivity
{
    public void setActive(boolean active);
    public void setGroup(ActiveGroup<?> group);
}
