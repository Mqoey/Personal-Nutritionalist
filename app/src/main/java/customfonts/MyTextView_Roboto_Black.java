package customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView_Roboto_Black extends TextView {

    public MyTextView_Roboto_Black(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.init();
    }

    public MyTextView_Roboto_Black(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public MyTextView_Roboto_Black(Context context) {
        super(context);
        this.init();
    }

    private void init() {
        if (!this.isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(this.getContext().getAssets(), "fonts/Roboto-Black.ttf");
            this.setTypeface(tf);
        }
    }
}
