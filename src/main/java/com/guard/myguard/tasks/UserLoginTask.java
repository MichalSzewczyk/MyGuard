package com.guard.myguard.tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.guard.myguard.MapsActivity;
import com.guard.myguard.R;
import com.guard.myguard.model.db.UserData;
import com.guard.myguard.services.interfaces.ParsingService;

import static com.guard.myguard.utils.Utils.showToast;

public class UserLoginTask extends AsyncTask<Void, Void, UserData> {
    protected final String login;
    protected final String mPassword;
    private final ParsingService parsingService;
    protected final Activity activity;

    public UserLoginTask(String login, String password, ParsingService parsingService, Activity activity) {
        this.login = login;
        this.mPassword = password;
        this.parsingService = parsingService;
        this.activity = activity;
    }

    @Override
    protected UserData doInBackground(Void... params) {
        return new UserData("My nick", "Ice pgone", "User phone");
    }

    @Override
    protected void onPostExecute(UserData result) {
        String message;
        if (result == null) {
            message = "User not found.";
        } else {
            message = "Logged in.";
        }
        showToast(activity, message);
    }

    protected void handleSuccess(UserData result) {
        Intent intent = new Intent(activity, MapsActivity.class);
        String serializedResult = null;
        try {
            serializedResult = parsingService.serialize(result);
        } catch (Throwable throwable) {
            Log.e(throwable.getLocalizedMessage(), throwable.toString());
        }
        intent.putExtra(activity.getString(R.string.user_data), serializedResult);
        activity.finish();
        activity.startActivity(intent);
    }

}
