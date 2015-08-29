package pack.wolf.com.pifi.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        updateTitleString();

        return null;
    }

    public void updateTitleString () {

//        if (StringUtils.isNotBlank(getTitleString())) {
//            if (getActivity() instanceof MainTabActivity) {
//                getActivity().setTitle(getTitleString());
//            }
//        }
    }

    public abstract String getTitleString ();
}
