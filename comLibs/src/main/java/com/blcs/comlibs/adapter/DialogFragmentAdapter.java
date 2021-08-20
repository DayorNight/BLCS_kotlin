package com.blcs.comlibs.adapter;

import com.blcs.comlibs.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;


public class DialogFragmentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public DialogFragmentAdapter() {
        super(R.layout.adapter_dialog_fragment_text);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_adapter_dialog_fragment,item);
    }
}
