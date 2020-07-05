package tafadzwa.nutritionist;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import tafadzwa.nutritionist.R.drawable;
import tafadzwa.nutritionist.R.id;
import tafadzwa.nutritionist.R.layout;

public class ViewPagerAdapter extends PagerAdapter {
    private final Context context ;
    private LayoutInflater layoutInflater;
    private final Integer [] images = {
            drawable.aa, drawable.a , drawable.b , drawable.c,  drawable.d , drawable.aa, drawable.e, drawable.g , drawable.h, drawable.aa
    };
    public ViewPagerAdapter(Context context ) {
        this .context = context ;
    }
    @Override
    public int getCount () {
        return this.images.length ;
    }
    @Override
    public boolean isViewFromObject (
            View view , Object object ) {
        return view == object ;
    }
    @Override
    public Object instantiateItem (
            ViewGroup container , int position ) {
        this.layoutInflater = (LayoutInflater ) this.context. getSystemService( Context .
                LAYOUT_INFLATER_SERVICE );
        View view = this.layoutInflater.inflate
                (layout .viewpager , null );
        ImageView imageView = view .findViewById (id .imageView
        );
        imageView. setImageResource(this.images[
                position]);
        ViewPager vp = ( ViewPager ) container;
        vp . addView ( view , 0 );
        return view ;
    }
    @Override
    public void destroyItem ( ViewGroup container, int position , Object object ) {
        ViewPager vp = ( ViewPager ) container;
        View view = ( View ) object ;
        vp . removeView (view );
    }
}