package org.kobehjk.net.app;

import android.app.Activity;
import android.text.TextUtils;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;

import org.kobehjk.net.entity.MediaEntity;
import org.kobehjk.net.entity.SimpleUserEntity;
import org.kobehjk.net.utils.GlideUtils;
import org.kobehjk.net.entity.VideoItemEntity;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.badgeview.BGABadgeImageView;
import kobehjk.org.demo.R;

/**
 * Created by kobehjk on 15/11/22.
 */
public class AppUtils {


    public static void loadBigUserAvata(Activity activity, SimpleUserEntity entity, BGABadgeImageView imageView) {
        loadImage(activity, R.mipmap.ic_verified_account_24dp, entity, imageView);
    }

    public static void loadSmallUserAvata(Activity activity, SimpleUserEntity entity, BGABadgeImageView imageView) {
        loadImage(activity, R.mipmap.ic_verified_account_16dp, entity, imageView);
    }

    private static void loadImage(Activity activity, int v, SimpleUserEntity entity, BGABadgeImageView imageView) {
        if (TextUtils.isEmpty(entity.getAvatar()))
            return;
        BitmapTypeRequest builder = Glide.with(activity).load(entity.getAvatar()).asBitmap();
        if (entity.getVerified()) {
            GlideUtils.loadBadgeImage(v, builder, imageView);
        } else {
            GlideUtils.loadCircleImage(builder, imageView);
        }
    }


    public static List<MediaEntity> toMediaList(List<VideoItemEntity> dataList) {
        List<MediaEntity> data = new ArrayList<>();
        for (VideoItemEntity entity : dataList) {
            MediaEntity mediaEntity = new MediaEntity();
            mediaEntity.setId(entity.getMedia().getId());
            mediaEntity.setUser(entity.getMedia().getUser());
            mediaEntity.setLikes_count(entity.getMedia().getLikes_count());
            mediaEntity.setCaption(entity.getRecommend_caption());
            mediaEntity.setCover_pic(entity.getRecommend_cover_pic());
            data.add(mediaEntity);
        }
        return data;
    }
}
