package org.kobehjk.net.ui.fragmet;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/*MobAD
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
**/

import com.qq.e.ads.interstitial.AbstractInterstitialADListener;
import com.qq.e.ads.interstitial.InterstitialAD;
import com.qq.e.ads.interstitial.InterstitialADListener;
import com.qq.e.comm.util.AdError;

import org.kobehjk.net.api.Constants;
import org.kobehjk.net.entity.MediaEntity;
import org.kobehjk.net.support.recyclerview.MGridLayoutManager;
import org.kobehjk.net.ui.activity.MainActivity;
import org.kobehjk.net.ui.adapter.MediasAdapter;
import org.kobehjk.net.presenter.HomeMediasPresenter;
import org.kobehjk.net.presenter.impl.HomeMediasPresenterImpl;
import org.kobehjk.net.ui.activity.VideoPlayActivity;
import org.kobehjk.net.view.HomeMediasView;

import java.util.List;

import kobehjk.org.demo.R;


/**
 * Created by sunger on 2015/10/23.
 */
public class HomeMediasFragment extends RefreshAndLoadFragment implements HomeMediasView, MediasAdapter.OnItemClickListener {
    private static final String KEY_VIDEO_ID = "id";
    private static final String KEY_VIDEO_TYPE = "type";
    private static final int PAGER_SIZE = 20;
    private static final int GRID_COLUMN = 2;
    static final int ACTION_NONE = 0;
    private MainActivity mainActivity;
    private RecyclerView mRecyclerView;
    private MediasAdapter mAdapter;
    private HomeMediasPresenter mPresenter;
    private int id;
    private int type;

//MobAd
//    private InterstitialAd mInterstitialAd;

//GTDAd
    private InterstitialAD iad;
    private boolean loadSuccess = false;


    public static Fragment newInstance(int id, int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_VIDEO_ID, id);
        bundle.putInt(KEY_VIDEO_TYPE, type);
        Fragment fragment = new HomeMediasFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;

    }

    @Override
    protected void onFragmentLoadMore() {
        mPresenter.loadMore(id, type, getCurrentPage(), PAGER_SIZE);
    }

    @Override
    void onRefreshData() {
        mPresenter.refresh(id, type, PAGER_SIZE);
    }

    @Override
    protected void onFragmentCreate() {
        super.onFragmentCreate();
        id = getArguments().getInt(KEY_VIDEO_ID);
        type = getArguments().getInt(KEY_VIDEO_TYPE);
        mPresenter = new HomeMediasPresenterImpl(this);
        mRecyclerView = getRecyclerView();
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MediasAdapter(getActivity());
        setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setHasMoreData(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new MGridLayoutManager(getActivity(), GRID_COLUMN, mAdapter));
        autoRefresh();
        /*MobAd
        mInterstitialAd = new InterstitialAd(mainActivity);
        mInterstitialAd.setAdUnitId("ca-app-pub-2364076622212252/1238664018");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });
        **/
        //GTDAd
        showAD();
    }

    private void autoRefresh() {
        getSwipeRefreshWidget().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentState = STATE_REFRESH;
                getSwipeRefreshWidget().setRefreshing(true);
                mPresenter.refresh(id, type, PAGER_SIZE);
            }
        }, 600);
    }

    @Override
    public void showError() {
        currentState = ACTION_NONE;
        if (getSwipeRefreshWidget().isRefreshing()) {
            getSwipeRefreshWidget().setRefreshing(false);
            mainActivity.showMsgBelowTabLayout(getString(R.string.msg_error_refresh));
        } else {
            mainActivity.showMsgInBottom(R.string.msg_error_load);
            mAdapter.setHasFooter(false);
        }
    }

    @Override
    public void refreshView(List<MediaEntity> entities) {
        showRefreshData(entities);
    }

    @Override
    public void loadMoreView(List<MediaEntity> entities) {
        showMoreData(entities);
    }


    @Override
    public void onItemClick(MediaEntity entity) {
        /* MobAd
        if (mInterstitialAd.isLoaded()&& mainActivity.watchCount == 4) {
            mainActivity.watchCount = 0;
            startActivity(VideoPlayActivity.createIntent(getActivity(), entity.getId()));
            mInterstitialAd.show();
        } else {
            mainActivity.watchCount++;
            startActivity(VideoPlayActivity.createIntent(getActivity(), entity.getId()));
        }
        **/
        if (loadSuccess && mainActivity.watchCount == 4) {
            mainActivity.watchCount = 0;
            loadSuccess = false;
            iad.loadAD();
            startActivity(VideoPlayActivity.createIntent(getActivity(), entity.getId()));
            iad.show();
        } else {
            mainActivity.watchCount++;
            startActivity(VideoPlayActivity.createIntent(getActivity(), entity.getId()));
        }
    }

    private InterstitialAD getIAD() {
        if (iad == null) {
            iad = new InterstitialAD(mainActivity, Constants.APPID, Constants.InterteristalPosID);
        }
        return iad;
    }

    private void showAD() {
        getIAD().setADListener(new AbstractInterstitialADListener() {

            @Override
            public void onNoAD(AdError error) {
                Log.i(
                        "AD_DEMO",
                        String.format("LoadInterstitialAd Fail, error code: %d, error msg: %s",
                                error.getErrorCode(), error.getErrorMsg()));
            }

            @Override
            public void onADReceive() {
                Log.i("AD_DEMO", "onADReceive");
                loadSuccess = true;
//                iad.show();
            }
        });
        iad.loadAD();
    }


}