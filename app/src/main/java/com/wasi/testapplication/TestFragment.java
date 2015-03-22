package com.wasi.testapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wasi.myapplication.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class TestFragment extends Fragment {

    int sum = 0;

    @InjectView(R.id.textview_count)
    TextView textview_count;
    @InjectView(R.id.edittext_value)
    EditText editText_value;
    @InjectView(R.id.textview_pager_index)
    TextView textview_pager_index;

    int positionInPager;
    public static TestFragment createInstance(int positionInPager){
        TestFragment f = new TestFragment();
        f.positionInPager = positionInPager;
        return f;
    }

    public TestFragment() {
    }

    @OnClick(R.id.button_increment)
    public void submit(Button view) {
        String valString = editText_value.getText().toString();
        if (!valString.isEmpty()){
            int value = Integer.parseInt(valString);
            sum = sum + value;
            EventBus.getDefault().post(new ValueChangedMessage(sum));
        }
    }

    //This is called when we receive a message/event
    public void onEvent(ValueChangedMessage event) {
            sum = event.value;
            textview_count.setText("" + sum);
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_test, container, false);
        ButterKnife.inject(this, v);
        textview_pager_index.setText("Fragment #"+this.positionInPager);
        return v;
     }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

}
