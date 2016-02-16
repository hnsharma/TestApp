package com.textapp.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

import com.textapp.R;


public class TransparentProgressDialog extends Dialog 
{


	Context	context;

	public TransparentProgressDialog(Context context) {
		super(context, R.style.TransparentProgressDialog);
		this.context		=	context;
		WindowManager.LayoutParams wlmp = getWindow().getAttributes();
		wlmp.gravity = Gravity.CENTER_HORIZONTAL;
		getWindow().setAttributes(wlmp);
		setTitle(null);
		setCancelable(false);
		setOnCancelListener(null);

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		  LayoutInflater inflater = ((Activity) context).getLayoutInflater();

         View convertView = inflater.inflate(R.layout.progress_bar_view, null, false);
		layout.addView(convertView, params);
		addContentView(layout, params);
		//setContentView(R.layout.progress_bar_view);
	}

	@Override
	public void show() 
	{
        try {
		super.show();

        }
        catch (Exception e)
        {

        }
		/*RotateAnimation anim = new RotateAnimation(0.0f, 360.0f , Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
		anim.setInterpolator(new LinearInterpolator());
		anim.setRepeatCount(Animation.INFINITE);
		anim.setDuration(3000);
		iv.setAnimation(anim);
		iv.startAnimation(anim);*/
	}
	@Override
	public void dismiss()
	{
        try {
            super.dismiss();
        }
        catch (Exception e)
        {

        }
	}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // if(isShowing())
        if(((Activity)context)!=null&&!((Activity)context).isFinishing())
        {
            ((Activity)context).finish();
        }
    }
}
