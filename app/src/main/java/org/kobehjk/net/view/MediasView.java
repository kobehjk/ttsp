package org.kobehjk.net.view;

import org.kobehjk.net.entity.MediaEntity;

import java.util.List;

/**
 * Created by sunger on 15/10/26.
 */
public interface MediasView {
    void showError();

    void showVideo(List<MediaEntity> entities);
}
