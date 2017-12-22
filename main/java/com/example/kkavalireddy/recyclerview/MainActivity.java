package com.example.kkavalireddy.recyclerview;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public boolean isForward;

    /*RecyclerView.SmoothScroller smoothScroller;*/

    List<String> imageNames = new ArrayList<String>(Arrays.asList("", "", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven"));

    List<Integer> images = new ArrayList<Integer>(Arrays.asList(android.R.color.transparent, android.R.color.transparent, R.drawable.five, R.drawable.nine, R.drawable.five, R.drawable.nine, R.drawable.five, R.drawable.nine, R.drawable.five, R.drawable.nine, R.drawable.five, R.drawable.nine));

    //SnappingRecyclerView recyclerView;
    RecyclerView recyclerView;

    LinearLayoutManager manager;

    ImageView imageView;

    Drawable dPage_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.background_image);

         dPage_header = getResources().getDrawable(R.mipmap.ic_launcher);
            //imageView.setImageDrawable(dPage_header);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        //recyclerView.enableViewScaling(true);
        /*recyclerView.fling((int)(100*0.7),0);*/


       /* SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(recyclerView);*/


        CustomAdapter adapter = new CustomAdapter(this, images, imageNames);
        recyclerView.setAdapter(adapter);

//        Toast.makeText(this, "First Completely " + manager.findFirstCompletelyVisibleItemPosition() + "\nFirst Visible :  " + manager.findFirstVisibleItemPosition() + "\n Last Visible:  " + manager.findLastVisibleItemPosition() + "\n Last Completely visible:  " + manager.findLastCompletelyVisibleItemPosition(), Toast.LENGTH_SHORT).show();


        final LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {

            /*@Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 1000 / displayMetrics.densityDpi;
            }*/

            @Override
            protected int getHorizontalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }

            @Override
            protected int calculateTimeForScrolling(int dx) {
                return 150;
            }
        };

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private boolean directionLeft;
            private boolean directionRight;
            //private int overallXScrol = 0;
            //int percentage;


            // setting the opacity (alpha)



            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


                //Toast.makeText(MainActivity.this, "LastCompletely: \n"+manager.findLastCompletelyVisibleItemPosition()+" LastVisible: \n"+manager.findLastVisibleItemPosition()+" FirstCompletely: \n"+manager.findFirstCompletelyVisibleItemPosition()+" FirstVisible: \n"+manager.findFirstVisibleItemPosition(), Toast.LENGTH_SHORT).show();

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (directionLeft) {
                        //Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();

                        if (manager.findFirstVisibleItemPosition() == 0) {

                            linearSmoothScroller.setTargetPosition(0);
                            manager.startSmoothScroll(linearSmoothScroller);

                            //manager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), 0);
                            //manager.setSmoothScrollbarEnabled(true);
                            //manager.scrollToPosition(0);
                            // manager.scrollToPositionWithOffset(0, 20);
                        }

                        if (manager.findFirstVisibleItemPosition() == 1 && manager.findFirstCompletelyVisibleItemPosition() != 1) {
                            linearSmoothScroller.setTargetPosition(2);
                            manager.startSmoothScroll(linearSmoothScroller);

                            //manager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), 2);
                            //manager.setSmoothScrollbarEnabled(true);
                            //manager.scrollToPosition(2);
                            //manager.scrollToPositionWithOffset(2, 20);
                        }
                    }

                    if (directionRight) {
                        //Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                        if (manager.findFirstVisibleItemPosition() == 1) {
                            linearSmoothScroller.setTargetPosition(2);
                            manager.startSmoothScroll(linearSmoothScroller);

                            //manager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), 4);
                            //manager.setSmoothScrollbarEnabled(true);
                            //manager.scrollToPosition(2);
                            // manager.scrollToPositionWithOffset(2, 20);
                        }

                        if (manager.findFirstVisibleItemPosition() == 0) {
                            linearSmoothScroller.setTargetPosition(0);
                            manager.startSmoothScroll(linearSmoothScroller);

                            //manager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), 0);
                            //manager.setSmoothScrollbarEnabled(true);
                            //manager.scrollToPosition(0);
                            //manager.scrollToPositionWithOffset(0, 20);
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int offset = recyclerView.computeHorizontalScrollOffset();
                int extent = recyclerView.computeHorizontalScrollExtent();
                int range = recyclerView.computeHorizontalScrollRange();

                int percentage = (int) (100.0 * offset / (float) (range - extent));
                float percent = 0;
                Log.e("Percentage"," Percentage="+percentage);

                percent = (percentage/21f)*100.0f;
                Log.e("Pecent","Percent= "+percent);
                Log.e("Offset"," "+offset);
                //overallXScrol = overallXScrol + dx;

                if(percent == 0) {
                    dPage_header.setAlpha(255);
                    imageView.setImageDrawable(dPage_header);
                }
                if(percent > 0 && percent <= 100f) {
                    dPage_header.setAlpha(((255) - (int) (percent * 2.55f)));
                    imageView.setImageDrawable(dPage_header);
                }
                else if(percent > 100f) {
                    dPage_header.setAlpha(0);
                    imageView.setImageDrawable(dPage_header);
                }


               /* Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
                mLoadAnimation.setDuration(2000);

                Animation myLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);
                myLoadAnimation.setDuration(2000);*/
                //Log.e("Hmm:","Offset:"+offset+"Extent:"+extent+"Range:"+range);

                //Toast.makeText(MainActivity.this, ""+percentage, Toast.LENGTH_SHORT).show();
               /* if(percentage>22)
                    imageView.getDrawable().setAlpha(0);*/
                if (dx < 0) {
                    directionLeft = true;
                    directionRight = false;


                    /*if(percent>0 && percent<55)
                   dPage_header.setAlpha((int) (percent*2.55f));
                    else if(percent == 0)
                        dPage_header.setAlpha(255);
                    else
                        dPage_header.setAlpha(0);*/
                   /* if(percent==0)
                        dPage_header.setAlpha(255);
                    imageView.setImageDrawable(dPage_header);
                   if(percent>0 && percent<=100f)
                        dPage_header.setAlpha((int) (percent*2.55f));
                    imageView.setImageDrawable(dPage_header);
                    if(percent>100f)
                        dPage_header.setAlpha(0);
                    imageView.setImageDrawable(dPage_header);*/


                    //Log.e("Percentage: ", "Left: " + percent);

                  /*  if(percentage>0 && percentage < 20) {
                        dPage_header.setAlpha((int) (percentage*2.55f));

                    }
                    if(percentage == 0){

                        dPage_header.setAlpha(255);
                    }

                    if(percentage > 20)
                    {
                        dPage_header.setAlpha(0);
                    }*/

                    /*if(percentage>0 && percentage < 20) {
                        dPage_header.setAlpha((int) (percentage*2.55f));

                    }
                    else if(percentage == 0){

                        dPage_header.setAlpha(255);
                    }
                    else
                    {
                        dPage_header.setAlpha(0);
                    }*/
                    /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                       *//* if (percentage > 0 && percentage < 22)
                            imageView.setImageAlpha((int) (percentage * 2.55f));
                        else
                            imageView.setImageAlpha((int) (percentage * 2.55f));*//*



                        // Log.e("Percentage = "," "+(overallXScrol*100));
                  *//*  if(percentage<5)
                        imageView.getDrawable().setAlpha(255);*//*

                   *//* if (percentage <= 12 ) {
                        //imageView.startAnimation(mLoadAnimation);
                        imageView.getDrawable().setAlpha(255);
//                        imageView.startAnimation(mLoadAnimation);
                    }
                    else
                    {
//                        imageView.startAnimation(myLoadAnimation);

                        imageView.getDrawable().setAlpha(0);
                    }*//*
                    }*/
                }

                   /* if(percentage!=0)
                    {*/
                // percentage = (percentage/22)*100;
                      /*  imageView.getDrawable().setAlpha((int) ((float)percentage*2.55f));
*/
//                    }
                else {
                    directionLeft = false;
                    directionRight = true;


                    /*if(percent==0)
                        dPage_header.setAlpha(255);
                    imageView.setImageDrawable(dPage_header);
                    if(percent>0 && percent<=100f)
                    dPage_header.setAlpha((int) (percent*2.55f));
                    imageView.setImageDrawable(dPage_header);
                    if(percent>100f)
                        dPage_header.setAlpha(0);
                    imageView.setImageDrawable(dPage_header);*/


                  /*  if(percentage>0 && percentage < 20) {
                        dPage_header.setAlpha((int) (percentage*2.55f));

                    }
                    if(percentage == 0){

                        dPage_header.setAlpha(255);
                    }

                    if(percentage > 20)
                    {
                        dPage_header.setAlpha(0);
                    }*/
                   /* if(percentage!=0)

                    {*/
                    //Log.e("Percentage: ", "Right: " + percent);
                   /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        *//*if (percentage > 0 && percentage < 22)
                            imageView.setImageAlpha((int) (percentage * 2.55f));
                        else
                            imageView.setImageAlpha((255));*//*



                        //Log.e("Percentage = "," "+(overallXScrol*100));
                        //percentage = (percentage/22)*100;
                        //imageView.getDrawable().setAlpha((int) ((float)percentage*2.55f));
//                    }
                   *//* else {
                        imageView.getDrawable().setAlpha(255);
                    }*//*
                   *//* if (percentage >= 12) {

                        imageView.getDrawable().setAlpha(0);
//                        imageView.startAnimation(myLoadAnimation);
                    }
                    else if (percentage <= 1) {
                        imageView.getDrawable().setAlpha((int) (255));
                    }*//*
                    }*/
                }
               /* if (percentage > 0 && percentage < 20) {
                    imageView.setImageAlpha(percentage );
                } else if (percentage > 20 && percentage != 0) {
                    imageView.setImageAlpha(0);
                } else {
                    imageView.setImageAlpha(255);

                }*/
            }


        });

    }
}

//Log.e("Irritating : ","LastCompletely: \n"+manager.findLastCompletelyVisibleItemPosition()+" LastVisible: \n"+manager.findLastVisibleItemPosition()+" FirstCompletely: \n"+manager.findFirstCompletelyVisibleItemPosition()+" FirstVisible: \n"+manager.findFirstVisibleItemPosition());

               /* if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (directionLeft) {
                        if (manager.findFirstCompletelyVisibleItemPosition() == 1 && manager.findFirstVisibleItemPosition() == 0) {

                            manager.scrollToPosition(3);
                        }
                    }
                }*/
//Toast.makeText(MainActivity.this, " "+dx+" "+dy, Toast.LENGTH_SHORT).show();
 /*  if(manager.findFirstVisibleItemPosition() == 0 && manager.findFirstCompletelyVisibleItemPosition() == 0 && manager.findLastCompletelyVisibleItemPosition() == 1 && manager.findLastVisibleItemPosition() == 2 )
               {
//                   manager.scrollToPosition(4);
               }
               else if(manager.findFirstVisibleItemPosition() == 0 && manager.findLastCompletelyVisibleItemPosition() == 1 && manager.findLastVisibleItemPosition() == 2  && manager.findFirstCompletelyVisibleItemPosition() == 1)
               {
                   manager.scrollToPosition(4);
               }*/

//if(newState < imageNames.size()-1) {


            /*  if (manager.findLastCompletelyVisibleItemPosition() == 1 && manager.findLastCompletelyVisibleItemPosition() == 0) {

                }
                else if(manager.){
                  manager.scrollToPosition(3);
              }*/
//            manager.scrollToPosition((manager.findFirstCompletelyVisibleItemPosition() == 0 && manager.findLastCompletelyVisibleItemPosition() ==1) ? 0 : 3);
//}
               /* if(manager.f)*/




                /*{
                    smoothScroller.setTargetPosition(2);
                    layoutManager.startSmoothScroll(smoothScroller);*//*
//                    ((LinearLayoutManager) recyclerView.getLayoutManager()).setSmoothScrollbarEnabled(true);
//                    recyclerView.getLayoutManager().scrollToPosition(2);
                }*/

                     /*  smoothScroller = new
                LinearSmoothScroller(this) {
                    @Override protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }
                };*/

//        recyclerView.setHasFixedSize(true);
       /* LayoutTransition transition = new LayoutTransition();
        transition.enableTransitionType(LayoutTransition.CHANGE_APPEARING);*/
        /*SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);*/