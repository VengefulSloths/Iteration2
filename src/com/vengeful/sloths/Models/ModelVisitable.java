package com.vengeful.sloths.Models;

/**
 * Created by zach on 2/22/16.
 */
public interface ModelVisitable {

    void accept(ModelVisitor modelVisitor);
}
