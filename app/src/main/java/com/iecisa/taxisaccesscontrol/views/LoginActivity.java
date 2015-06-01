package com.iecisa.taxisaccesscontrol.views;

import android.app.Activity;
import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.iecisa.model.rest.RestDataSource;
import com.iecisa.taxisaccesscontrol.App;
import com.iecisa.taxisaccesscontrol.R;
import com.iecisa.taxisaccesscontrol.mvp.presenters.LoginPresenter;
import com.iecisa.taxisaccesscontrol.mvp.views.LoginView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends Activity implements LoginView {

    private LoginPresenter mLoginPresenter = null;

    @InjectView(R.id.username_et)
    EditText mUserNameView;
    @InjectView(R.id.password_et)
    EditText mPasswordView;
    private ProgressDialog mLoadingPd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.inject(this);

        mLoginPresenter = new LoginPresenter(this);
        mLoadingPd = new ProgressDialog(this);
        mLoadingPd.setCancelable(false);
        mLoadingPd.setMessage(getString(R.string.str_loading));
        /*mLoadingPd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                DownloadFileRequest.this.cancel(true);
                Toast.makeText(mContext, mContext.getResources().getString(R.string.info_message_cancel), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public void onStart(){
        super.onStart();
        mLoginPresenter.start();
    }

    @Override
    public void onStop(){
        mLoginPresenter.stop();
        super.onStop();
    }

    @OnClick(R.id.login_btn)
    public void attemptLogin() {
        // Reset errors.
        mUserNameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String userName = mUserNameView.getText().toString();
        String password = mPasswordView.getText().toString();

        // Check for a valid email address.
        if (userName.isEmpty()) {
            mUserNameView.setError(getString(R.string.error_field_required));
            mUserNameView.requestFocus();
        } else if (password.isEmpty()) {
            mPasswordView.setError(getString(R.string.error_field_required));
            mPasswordView.requestFocus();
        } else {
            mLoginPresenter.attemptLogin(userName, password);
        }
    }

    @Override
    public void showLoading() {
        mLoadingPd.show();
    }

    @Override
    public void hideLoading() {
        mLoadingPd.dismiss();
    }

    @Override
    public void showLoginError() {
        mPasswordView.setError(getString(R.string.error_field_password_wrong));
        mPasswordView.requestFocus();
    }

    @Override
    public void showConexionError() {
        Toast.makeText(this, getString(R.string.error_conexion), Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginOK(RestDataSource restDataSource) {
        //Todo: Comprobar si el app se maneja desde la vista o el presentador
        App app = (App) getApplication();
        app.setRestDataSource(restDataSource);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

}



