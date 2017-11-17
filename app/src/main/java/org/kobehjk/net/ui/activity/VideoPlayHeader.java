package org.kobehjk.net.ui.activity;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

//MobAD
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.AdView;

import org.kobehjk.net.api.Constants;
import org.kobehjk.net.app.AppUtils;
import org.kobehjk.net.entity.MediaEntity;
import org.kobehjk.net.utils.DateUtils;
import org.kobehjk.net.ui.widget.VideoControllerView;
import org.kobehjk.net.widget.TextImageView;

import cn.bingoogolapple.badgeview.BGABadgeImageView;
import kobehjk.org.demo.R;

import android.util.Log;

import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.ads.banner.BannerView;
import com.qq.e.comm.util.AdError;


/**
 * Created by kobehjk on 2015/12/1.
 */
public class VideoPlayHeader {

    private BGABadgeImageView mBgaBadgeImageView;
    private TextView mTextViewScreenName;
    private TextView mTextViewCreatedAt;
    private TextImageView mTextViewPlaysCount;
    private VideoControllerView mVideoControllerView;
    private TextView mTextViewCaption;
    private ImageView mImageViewCommunicationMessage;
    private TextView mTextViewCommunicationMessage;
    private ImageView mImageViewRepeat;
    private ImageView mImageViewThumbUp;
    private TextView mTextViewThumbUp;
    private TextView mTextViewRepeat;
    private Activity mActivity;
//MobAd
//    private AdView mAdView;

    //GTDAd
    private ViewGroup bannerContainer;
    private BannerView bv;

    public VideoPlayHeader(Activity activity, View view) {
        this.mActivity = activity;
        initAdView(activity,view);
        mBgaBadgeImageView = (BGABadgeImageView) view.findViewById(R.id.imageView_avatar);
        mTextViewScreenName = (TextView) view.findViewById(R.id.textView_screen_name);
        mTextViewCreatedAt = (TextView) view.findViewById(R.id.textView_created_at);
        mTextViewPlaysCount = (TextImageView) view.findViewById(R.id.textView_plays_count);
        mVideoControllerView = (VideoControllerView) view.findViewById(R.id.videoControllerView);
        mTextViewCaption = (TextView) view.findViewById(R.id.textView_caption);
        mImageViewCommunicationMessage = (ImageView) view.findViewById(R.id.imageView_communication_message);
        mTextViewCommunicationMessage = (TextView) view.findViewById(R.id.textView_communication_message);
        mImageViewRepeat = (ImageView) view.findViewById(R.id.imageView_repeat);
        mImageViewThumbUp = (ImageView) view.findViewById(R.id.imageView_thumb_up);
        mTextViewThumbUp = (TextView) view.findViewById(R.id.textView_thumb_up);
        mTextViewRepeat = (TextView) view.findViewById(R.id.textView_repeat);

        bannerContainer = (ViewGroup) view.findViewById(R.id.bannerContainer);
    }

    public void bindData(final MediaEntity mediaEntity) {
        AppUtils.loadSmallUserAvata(mActivity, mediaEntity.getUser(), mBgaBadgeImageView);
        mTextViewScreenName.setText(mediaEntity.getUser().getScreen_name());
        mTextViewCreatedAt.setText(DateUtils.getDifference(mediaEntity.getCreated_at()));
        mTextViewPlaysCount.setTextImageStart(15, R.mipmap.ic_visibility_white_24dp, " " + mediaEntity.getPlays_count());
        mTextViewCaption.setText(mediaEntity.getCaption());
        mTextViewCommunicationMessage.setText(mediaEntity.getComments_count() + "");
        mTextViewThumbUp.setText(mediaEntity.getLikes_count() + "");
        mTextViewRepeat.setText(mediaEntity.getReposts_count() + "");
        mVideoControllerView.setVideoPath(mediaEntity.getVideo());
        mVideoControllerView.start();
        mImageViewThumbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.thumb_up_scale);
                if (mediaEntity.getLiked()) {
                    mediaEntity.setLiked(false);
                    mediaEntity.setLikes_count(mediaEntity.getLikes_count() - 1);
                    mImageViewThumbUp.setImageResource(R.mipmap.ic_thumb_up_white_18dp);
                    mImageViewThumbUp.startAnimation(animation);
                } else {
                    mediaEntity.setLiked(true);
                    mediaEntity.setLikes_count(mediaEntity.getLikes_count() + 1);
                    mImageViewThumbUp.setImageResource(R.mipmap.ic_thumb_up_blue_18dp);
                    mImageViewThumbUp.startAnimation(animation);
                }
            }
        });
        if (mediaEntity.getLiked()) {
            mImageViewThumbUp.setImageResource(R.mipmap.ic_thumb_up_blue_18dp);
        } else {
            mImageViewThumbUp.setImageResource(R.mipmap.ic_thumb_up_white_18dp);
        }

        initBanner();
        bv.loadAD();
    }
    public VideoControllerView getVideoControllerView() {
        return mVideoControllerView;
    }


    private void initBanner() {
        this.bv = new BannerView(mActivity, ADSize.BANNER, Constants.APPID, Constants.BannerPosID);
        // 注意：如果开发者的banner不是始终展示在屏幕中的话，请关闭自动刷新，否则将导致曝光率过低。
        // 并且应该自行处理：当banner广告区域出现在屏幕后，再手动loadAD。
        bv.setRefresh(30);
        bv.setADListener(new AbstractBannerADListener() {

            @Override
            public void onNoAD(AdError error) {
                Log.i(
                        "AD_DEMO",
                        String.format("Banner onNoAD，eCode = %d, eMsg = %s", error.getErrorCode(),
                                error.getErrorMsg()));
            }

            @Override
            public void onADReceiv() {
                Log.i("AD_DEMO", "ONBannerReceive");
            }
        });
        bannerContainer.addView(bv);
    }

    private void initAdView(Activity activity, View view){
        /*
        MobAd

        mAdView = (AdView)view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        Log.d("kobehjkddsp",String.valueOf(adRequest.isTestDevice(activity)) );
        mAdView.loadAd(adRequest);
        **/
    }




}
