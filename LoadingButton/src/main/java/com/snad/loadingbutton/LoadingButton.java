package com.snad.loadingbutton;

import com.snad.loadingbutton.util.ResUtil;
import ohos.agp.components.*;
import ohos.agp.utils.Color;
import ohos.app.Context;

public class LoadingButton extends DependentLayout {
    private Context context;
    private ComponentContainer rootLayout;
    private RoundProgressBar mProgressBar;
    private Text mTextField;
    private int mDefaultTextSize;
    private String mLoadingMessage;
    private String mButtonText;
    private float mTextSize;
    private int mTextColor;
    private boolean mIsLoadingShowing;
    private int mCurrentInDirection;
    private boolean mTextSwitcherReady;
    private Color DEFAULT_COLOR =Color.WHITE;


    public LoadingButton(Context context) {
        this(context, null);
    }
    public LoadingButton(Context context, AttrSet attrs) {
        this(context, attrs,"");
    }

    public LoadingButton(Context context, AttrSet attrs, String defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context =context;
        init(attrs);
    }

    public float getTextSize() {
        return mTextSize;
    }


    public void setProgressColor(int colorRes) {
        int color = ResUtil.getColor(context, colorRes);
        mProgressBar.setProgressColor( new Color( color));
    }

    public void setProgressColor(Color color) {
        mProgressBar.setProgressColor( color);
    }

    public void setAnimationInDirection(int inDirection) {
        mCurrentInDirection = inDirection;
    }

    public void setText(String text) {
        if (text != null) {
            mButtonText = text;
            if (null!= mTextField) {
                mTextField.setText(mButtonText);
            }
        }
    }

    public void setLoadingText(String text) {
        if (text != null) {
            mLoadingMessage = text;
        }
    }

    public void showLoading() {
        if (!mIsLoadingShowing) {
            mProgressBar.setVisibility(Component.VISIBLE);
            mTextField.setText(mLoadingMessage);
            mIsLoadingShowing = true;
            setEnabled(false);
        }
    }

    public void showButtonText() {
        if (mIsLoadingShowing) {
            mProgressBar.setVisibility(Component.INVISIBLE);
            mTextField.setText(mButtonText);
            mIsLoadingShowing = false;
            setEnabled(true);
        }
    }

    public boolean isLoadingShowing() {
        return mIsLoadingShowing;
    }

    private void init(AttrSet attrset) {
        mDefaultTextSize = (int)ResUtil.getDimen(context, ResourceTable.Float_text_default_size);
        mIsLoadingShowing = false;
        rootLayout = (ComponentContainer) LayoutScatter.getInstance(context)
                .parse(ResourceTable.Layout_component_loading_button, null, false);

        mProgressBar = (RoundProgressBar)rootLayout.findComponentById(ResourceTable.Id_roundprogress);
        mTextField = (Text)rootLayout.findComponentById(ResourceTable.Id_text1);

        if(null != attrset)
        {

            float textSize = attrset.getAttr("pbTextSize").isPresent() ?
                    attrset.getAttr("pbTextSize").get().getDimensionValue() :mDefaultTextSize;
            setTextSize(textSize);

            String text = attrset.getAttr("pbText").isPresent() ?
                    attrset.getAttr("pbText").get().getStringValue() :"";
            setText(text);

            mLoadingMessage = attrset.getAttr("pbLoadingText").isPresent() ?
                    attrset.getAttr("pbLoadingText").get().getStringValue() : null;

            if (mLoadingMessage == null) {
                mLoadingMessage = ResUtil.getString(context, ResourceTable.String_default_loading);
            }

            Color progressColor = attrset.getAttr("pbProgressColor").isPresent() ?
                    attrset.getAttr("pbProgressColor").get().getColorValue() :DEFAULT_COLOR;
            setProgressColor(progressColor);

            Color textColor = attrset.getAttr("pbTextColor").isPresent() ?
                    attrset.getAttr("pbTextColor").get().getColorValue() :DEFAULT_COLOR;
            setTextColor(textColor.getValue());

    } else {
        mLoadingMessage = ResUtil.getString(context, ResourceTable.String_default_loading);
        setProgressColor( DEFAULT_COLOR);
        setTextColor( DEFAULT_COLOR.getValue());
        setTextSize(mDefaultTextSize);
    }

        super.addComponent( rootLayout);
    }

    private void setTextSize(float size) {
        mTextSize = size;
        mTextField.setTextSize( (int)mTextSize);
    }

    private void setTextColor(int textColor) {
        mTextColor = textColor;
        mTextField.setTextColor( new Color(mTextColor));
    }


}
