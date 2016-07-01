package org.jbfavre.thot.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.jbfavre.thot.R;

public class SetupActivity extends Activity {

    @BindView(R.id.endpoint_editText) EditText endpointEditText;
    @BindView(R.id.ssl_switch) Switch sslSwitch;
    @BindView(R.id.auth_switch) Switch authSwitch;
    @BindView(R.id.auth_layout) ViewGroup authLayout;
    @BindView(R.id.auth_login_editText) EditText authLoginEditText;
    @BindView(R.id.auth_password_editText) EditText authPasswordEditText;
    @BindView(R.id.refresh_switch) Switch refreshSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        ButterKnife.bind(this);

        authSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                authLayout.setVisibility(b ? View.VISIBLE : View.GONE);
            }
        });
    }

    @OnClick(R.id.auth_check_button)
    public void authCheckOnClick(){
        // TODO ENABLE / DISABLE OK BUTTON
    }

    @OnClick(R.id.ok_button)
    public void okOnClick(){
        // TODO Save into Shared Preferences all the values
        // https://developer.android.com/training/basics/data-storage/shared-preferences.html
    }
}
