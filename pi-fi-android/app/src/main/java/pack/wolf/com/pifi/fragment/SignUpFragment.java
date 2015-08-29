package pack.wolf.com.pifi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;

import pack.wolf.com.pifi.R;
import pack.wolf.com.pifi.model.User;
import pack.wolf.com.pifi.model.UserRequest;
import pack.wolf.com.pifi.service.api.AuthenticationService;
import pack.wolf.com.pifi.service.api.UserService;
import pack.wolf.com.pifi.service.impl.AuthenticationServiceImpl;
import pack.wolf.com.pifi.service.impl.UserServiceImpl;
import pack.wolf.com.pifi.util.DialogUtil;

public class SignUpFragment extends Fragment {

    private static View rootView;
    private Context context;
    private UserRequest userRequest;
    private User user;

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public SignUpFragment() {
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
        try {
            rootView = inflater.inflate(R.layout.fragment_signup, container, false);
        } catch (InflateException e) {
        }

        // get context
        context = inflater.getContext();

        // get user creds
        EditText username_box = (EditText) rootView.findViewById(R.id.username);
        EditText password_box = (EditText) rootView.findViewById(R.id.password);
        EditText password_confirm_box = (EditText) rootView.findViewById(R.id.password_confirm);
        final String username = username_box.getText().toString();
        final String password = password_box.getText().toString();
        final String password_confirm = password_confirm_box.getText().toString();

        // create user
        userRequest = new UserRequest();

        // sign up
        Button button_signup = (Button) rootView.findViewById(R.id.signup_button);
        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserService userService = new UserServiceImpl();
                userService.createUser(context,userRequest,DialogUtil.getProgressDialog(context,getString(R.string.signing_up)));
            }
        });

        return rootView;

    }


}
