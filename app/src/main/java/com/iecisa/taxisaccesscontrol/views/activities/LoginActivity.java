package com.iecisa.taxisaccesscontrol.views.activities;

import android.app.Activity;
import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.iecisa.taxisaccesscontrol.TaxiAccessControlApp;
import com.iecisa.taxisaccesscontrol.R;
import com.iecisa.taxisaccesscontrol.model.Repository;
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

        initializeProgressDialog();
        initializePresenter();
    }

    private void initializeProgressDialog(){
        mLoadingPd = new ProgressDialog(this);
        mLoadingPd.setCancelable(false);
        mLoadingPd.setMessage(getString(R.string.str_loading));
    }

    private void initializePresenter() {
        mLoginPresenter = new LoginPresenter();
        mLoginPresenter.attachView(this);
        mLoginPresenter.attachBundle(new Bundle());
        mLoginPresenter.initializePresenter();
    }

    @Override
    public void onStart(){
        super.onStart();
        mLoginPresenter.onStart();
    }

    @Override
    public void onStop(){
        mLoginPresenter.onStop();
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
    public void loginOK(Repository repository) {
        //Todo: Comprobar si el app se maneja desde la vista o el presentador
        TaxiAccessControlApp app = (TaxiAccessControlApp) getApplication();
        app.setRepository(repository);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

}



