package com.vengeful.sloths.Views.ViewFactory;

import com.vengeful.sloths.Views.View;

/**
 * Created by zach on 3/11/16.
 */
public abstract class ViewItem {
    String name;

    public ViewItem() {}

    public ViewItem(String name) {
        this.name = name;
    }

    public String getItemName() {
        return this.name;
    }
}
