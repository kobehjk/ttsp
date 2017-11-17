package org.kobehjk.net.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import org.kobehjk.net.entity.UserEntity;
import org.kobehjk.net.ui.adapter.CommonTabAdapter;
import org.kobehjk.net.ui.fragmet.FollowersFragment;
import org.kobehjk.net.ui.fragmet.FriendshipsFragment;
import org.kobehjk.net.ui.fragmet.RepostsMediasFragment;
import org.kobehjk.net.ui.fragmet.UserMediasFragment;
import org.kobehjk.net.app.App;

import kobehjk.org.demo.R;

/**
 * Created by kobehjk on 2015/11/20.
 */
public class UserInfoActivity extends BaseCompatActivity {
    private UserEntity mUserEntity;
    private ViewPager mViewPager;
    private UserInfoHeader mUserInfoHeader;
    private CommonTabAdapter mAdapter;
    private String video;
    private String reposts;
    private String following;
    private String followers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mUserEntity = App.getInstance().getOauthUserEntity().getUser();
        video = mUserEntity.getVideos_count() + "\n" + getString(R.string.video);
        reposts = mUserEntity.getReposts_count() + "\n" + getString(R.string.reposts);
        following = mUserEntity.getFriends_count() + "\n" + getString(R.string.following);
        followers = mUserEntity.getFollowers_count() + "\n" + getString(R.string.following);
        mUserInfoHeader = new UserInfoHeader(this, findView(R.id.layout_header_view));
        mUserInfoHeader.bindData(mUserEntity);
        setUpCommonBackTooblBar(R.id.toolbar, " ");
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.title_home));
        collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.addTab(tabLayout.newTab().setText(video));
        tabLayout.addTab(tabLayout.newTab().setText(reposts));
        tabLayout.addTab(tabLayout.newTab().setText(followers));
        tabLayout.addTab(tabLayout.newTab().setText(following));
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager mViewPager) {
        mAdapter = new CommonTabAdapter(getSupportFragmentManager());
        mAdapter.addItem(UserMediasFragment.newInstance(mUserEntity.getId()), video);
        mAdapter.addItem(RepostsMediasFragment.newInstance(mUserEntity.getId()), reposts);
        mAdapter.addItem(FriendshipsFragment.newInatance(mUserEntity.getId()), following);
        mAdapter.addItem(FollowersFragment.newInatance(mUserEntity.getId()), followers);
        mViewPager.setAdapter(mAdapter);
    }

}
