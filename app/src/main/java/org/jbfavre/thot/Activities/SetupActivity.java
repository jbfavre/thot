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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import org.jbfavre.thot.API.ThotApiProvider;
import org.jbfavre.thot.Model.ApiSuccessResponse;
import org.jbfavre.thot.Model.Tag;
import org.jbfavre.thot.R;
import org.jbfavre.thot.helpers.SharedPreferencesHelper;

import java.util.List;

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
        boolean sslRequired = sslSwitch.isChecked();
        final String endpoint = (sslRequired ? "https://" : "http://") + endpointEditText.getText().toString();

        final boolean authRequired = authSwitch.isChecked();
        final String login = authLoginEditText.getText().toString();
        final String password = authPasswordEditText.getText().toString();
        final boolean refreshAtStartup = refreshSwitch.isChecked();
        SharedPreferencesHelper.saveConfiguration(this, endpoint, authRequired, login, password, refreshAtStartup);

        ThotApiProvider thotApiProvider = new ThotApiProvider(this);
        Call<ApiSuccessResponse> call = thotApiProvider.selfossApi.login( login, password );
        call.enqueue(new Callback<ApiSuccessResponse>() {
            @Override
            public void onResponse(Call<ApiSuccessResponse> call, Response<ApiSuccessResponse> response) {
                boolean success = response.body().isSuccess();
                if(success) {
                    String authToken = getToken(response.raw().headers().get("Set-Cookie"));
                    SharedPreferencesHelper.saveToken(SetupActivity.this, authToken);
                }else {
                    SharedPreferencesHelper.clearConfiguration(SetupActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ApiSuccessResponse> call, Throwable t) {
                String str = t.getMessage();
            }
        });


    }

    private String getToken(String s) {
        return s.substring(0, s.indexOf(";"));
    }
}
