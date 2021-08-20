package com.blcs.comlibs.widget.dialogFragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blcs.comlibs.R;


public class LinCustomDialogFragment extends BaseDialogFragment implements View.OnClickListener {
    private static final String TAG = "CustomDialogFragment";
    private static LinCustomDialogFragment customDialogFragment;

    private int mipmap;
    private int size = 0;
    private Bitmap bitmap = null;
    private String title;
    private String content;
    private int DialogType;
    public static final int TYPE_IMAGE = 0;
    public static final int TYPE_SURE = 1;
    public static final int TYPE_CANCLE = 2;
    public static final int TYPE_EDITEXT = 3;
    public static final int TYPE_LOADING = 4;
    public static final int TYPE_IMAGE_BIG = 5;
    private EditText et_dialog_fragment_content;
    private int contentColor = -1;

    public static LinCustomDialogFragment init() {
        if (customDialogFragment == null) {
            customDialogFragment = new LinCustomDialogFragment();
        }
        return customDialogFragment;
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_dialog_custom, container, false);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        LinearLayout ll_dialog_fragment = view.findViewById(R.id.ll_dialog_fragment);
        //TYPE_IMAGE
        ImageView iv_dialog_fragment = view.findViewById(R.id.iv_dialog_fragment);
        //TYPE_SURE
        LinearLayout ll_dialog_fragment_sure = view.findViewById(R.id.ll_dialog_fragment_sure);
        TextView mTitle = view.findViewById(R.id.tv_dialog_fragment_title);
        TextView mContent = view.findViewById(R.id.tv_dialog_fragment_content);
        mContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        TextView sureClick = view.findViewById(R.id.tv_dialog_fragment_sure);
        sureClick.setOnClickListener(this);
        //TYPE_CANCLE
        TextView cancleClick = view.findViewById(R.id.tv_dialog_fragment_cancle);
        View viewCancle = view.findViewById(R.id.view_dialog_fragment_cancle);
        cancleClick.setOnClickListener(this);
        //TYPE_EDITEXT
        et_dialog_fragment_content = view.findViewById(R.id.et_dialog_fragment_content);
        //TYPE_LOADING
        LinearLayout ll_dialog_fragment_loading = view.findViewById(R.id.ll_dialog_fragment_loading);
        //TYPE_IMAGE_BIG
//        RelativeLayout rl_dialog_fragment_big = view.findViewById(R.id.rl_dialog_fragment_big);
//        RxScaleImageView rx_dialog_fragment_big = view.findViewById(R.id.rx_dialog_fragment_big);
//        ImageView iv_dialog_fragment_close = view.findViewById(R.id.iv_dialog_fragment_close);
        switch (DialogType) {
            case TYPE_IMAGE:
                ll_dialog_fragment.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent));
                iv_dialog_fragment.setPadding(0, size, 0, size);
                iv_dialog_fragment.setVisibility(View.VISIBLE);
                if (bitmap==null){
                    iv_dialog_fragment.setImageResource(mipmap);
                }else{
                    iv_dialog_fragment.setImageBitmap(bitmap);
                }
                setGravity(Gravity.CENTER_HORIZONTAL);
                break;
            case TYPE_SURE:
                common(ll_dialog_fragment_sure, mTitle,mContent);
                mContent.setVisibility(View.VISIBLE);
                setGravity(Gravity.CENTER);
                break;
            case TYPE_CANCLE:
                common(ll_dialog_fragment_sure, mTitle,mContent);
                mContent.setVisibility(View.VISIBLE);
                cancleClick.setVisibility(View.VISIBLE);
                viewCancle.setVisibility(View.VISIBLE);
                setGravity(Gravity.CENTER);
                break;
            case TYPE_EDITEXT:
                common(ll_dialog_fragment_sure, mTitle,mContent);
                mContent.setVisibility(View.GONE);
                cancleClick.setVisibility(View.VISIBLE);
                viewCancle.setVisibility(View.VISIBLE);
                et_dialog_fragment_content.setVisibility(View.VISIBLE);
                setGravity(Gravity.CENTER);
                break;
            case TYPE_LOADING:
                ll_dialog_fragment_loading.setVisibility(View.VISIBLE);
                setGravity(Gravity.CENTER);
                break;
            case TYPE_IMAGE_BIG:
//                rl_dialog_fragment_big.setVisibility(View.VISIBLE);
//                if(bitmap!=null){
//                    rx_dialog_fragment_big.setImage(ImageSource.bitmap(bitmap));
//                }else{
//                    rx_dialog_fragment_big.setImage(ImageSource.resource(mipmap));
//                }
//                iv_dialog_fragment_close.setOnClickListener(this);
//                setGravity(Gravity.NO_GRAVITY);
                break;
        }
    }

    private void common(LinearLayout ll_dialog_fragment_sure, TextView mTitle,TextView mContent) {
        ll_dialog_fragment_sure.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(title)) {
            mTitle.setVisibility(View.VISIBLE);
            mTitle.setText(title);

        }
        mContent.setText(content);
        Log.e(TAG, "common: "+contentColor );
        mContent.setTextColor(contentColor==-1?Color.BLACK:contentColor);
    }

    /**
     * 设置图片
     */
    public LinCustomDialogFragment setImage(int mipmap) {
        this.mipmap = mipmap;
        this.bitmap = null;
        return this;
    }
    /**
     * 设置图片
     */
    public LinCustomDialogFragment setImage(Bitmap bitmap) {
        this.bitmap = bitmap;
        return this;
    }
    /**
     * 设置图片padding
     */
    public LinCustomDialogFragment setImagePadding(int size) {
        this.size = size;
        return this;
    }

    /**
     * 设置标题
     */
    public LinCustomDialogFragment setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 设置内容
     */
    public LinCustomDialogFragment setContent(String content) {
        this.content = content;
        return this;
    }
    /**
     * 设置内容
     */
    public LinCustomDialogFragment setContentColor(int color) {
        this.contentColor = color;
        return this;
    }

    /**
     * 显示格式
     */
    public LinCustomDialogFragment setType(int type) {
        this.DialogType = type;
        return this;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_dialog_fragment_sure) {
            if (onSureListener != null) {
                onSureListener.clickSure();
            }
            if (onSureCancleListener != null) {
                onSureCancleListener.clickSure(et_dialog_fragment_content.getText().toString());
            }
        } else if (i == R.id.tv_dialog_fragment_cancle) {
            if (onSureCancleListener != null) {
                onSureCancleListener.clickCancle();
            }
        }
        dismiss();
    }

    public OnSureListener onSureListener;
    public OnSureCancleListener onSureCancleListener;

    public interface OnSureListener {
        void clickSure();
    }

    public interface OnSureCancleListener {
        void clickSure(String EdiText);
        void clickCancle();
    }

    public LinCustomDialogFragment setOnClickListener(OnSureListener listener) {
        onSureListener = listener;
        return this;
    }

    public LinCustomDialogFragment setOnClickListener(OnSureCancleListener listener) {
        onSureCancleListener = listener;
        return this;
    }

}
