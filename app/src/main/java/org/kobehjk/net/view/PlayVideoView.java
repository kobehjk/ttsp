package org.kobehjk.net.view;

import org.kobehjk.net.entity.CommentEntity;
import org.kobehjk.net.entity.MediaEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/11/10.
 */
public interface PlayVideoView {

    void showMediaData(MediaEntity mediaEntity);

    void showLoadMediaError();

    void refreshComment(List<CommentEntity> dataList);


    void showMoreComments(List<CommentEntity> dataList);

}
