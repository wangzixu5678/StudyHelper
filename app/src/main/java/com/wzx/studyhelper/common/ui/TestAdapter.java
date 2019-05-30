package com.wzx.studyhelper.common.ui;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wzx.studyhelper.R;

import java.util.List;

public class TestAdapter extends BaseQuickAdapter<Person, BaseViewHolder> {
    public TestAdapter(@Nullable List<Person> data) {
        super(R.layout.edittext_layout, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, Person item) {
        helper.setText(R.id.edittext, item.getName());
        ((EditText) helper.getView(R.id.edittext)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mData.get(helper.getLayoutPosition()).setName(editable.toString());
            }
        });
    }
}
