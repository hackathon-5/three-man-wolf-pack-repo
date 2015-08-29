package pack.wolf.com.pifi.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;

import pack.wolf.com.pifi.R;
import pack.wolf.com.pifi.service.BluetoothService;
import pack.wolf.com.pifi.service.api.AuthenticationService;
import pack.wolf.com.pifi.service.impl.AuthenticationServiceImpl;
import pack.wolf.com.pifi.util.BluetoothUtil;
import pack.wolf.com.pifi.util.DialogUtil;

public class SignInFragment extends Fragment {

    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    private static final int REQUEST_ENABLE_DSC = 3;

    private static View rootView;
    private Context context;

    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public SignInFragment() {
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
        try {
            rootView = inflater.inflate(R.layout.fragment_signin, container, false);
        } catch (InflateException e) {
        }

        // get context
        context = inflater.getContext();

        Log.e("blah", "\n\n" + BluetoothUtil.getBlueToothAddress(getActivity()) + "\n\n");

        // get user creds
        EditText username_box = (EditText) rootView.findViewById(R.id.username);
        EditText password_box = (EditText) rootView.findViewById(R.id.password);
        final String username = username_box.getText().toString();
        final String password = password_box.getText().toString();

        // sign in
        Button button_signin = (Button) rootView.findViewById(R.id.signin_button);
        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthenticationService auth = new AuthenticationServiceImpl();
                auth.login(username,password,context,new SignInListener(context),DialogUtil.getProgressDialog(context,getString(R.string.signing_in)));
            }
        });

        getActivity().startService(new Intent(getActivity(), BluetoothService.class));

        return rootView;

    }

    // sign in response listener
    private static class SignInListener implements Response.Listener {

        Context context;

        SignInListener(Context context) {
            this.context = context;
        }

        @Override
        public void onResponse(Object response) {
            Object status = response;
        }
    }


}
