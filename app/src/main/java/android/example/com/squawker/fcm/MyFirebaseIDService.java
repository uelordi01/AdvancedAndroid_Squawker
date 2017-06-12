package android.example.com.squawker.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by uelordi on 12/06/17.
 */

public class MyFirebaseIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.v(getClass().getName(), "Token: "+refreshedToken);
       sendRegistrationToServer(refreshedToken);

    }
    private void sendRegistrationToServer(String token) {

    }
}
