package com.changhao.weidu_project.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.ui.base.BaseActivity;
import com.changhao.weidu_project.ui.fragment.CircleFragment;
import com.changhao.weidu_project.ui.fragment.HomeFragment;
import com.changhao.weidu_project.ui.fragment.MineFragment;
import com.changhao.weidu_project.ui.fragment.OrderFragment;
import com.changhao.weidu_project.ui.fragment.ShoppingCartFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rd_btn_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rd_btn_circle:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rd_btn_shopping_cart:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.rd_btn_order:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.rd_btn_mine:
                        viewPager.setCurrentItem(4);
                        break;
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        radioGroup.check(R.id.rd_btn_home);
                        break;
                    case 1:
                        radioGroup.check(R.id.rd_btn_circle);
                        break;
                    case 2:
                        radioGroup.check(R.id.rd_btn_shopping_cart);
                        break;
                    case 3:
                        radioGroup.check(R.id.rd_btn_order);
                        break;
                    case 4:
                        radioGroup.check(R.id.rd_btn_mine);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i) {
                    case 0:
                        return new HomeFragment();
                    case 1:
                        return new CircleFragment();
                    case 2:
                        return new ShoppingCartFragment();
                    case 3:
                        return new OrderFragment();
                    case 4:
                        return new MineFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });

    }

    @Override
    protected int getViewId() {
        return R.layout.activity_main;
    }
}
