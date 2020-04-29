package com.SiJoLi.SiJoLi;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.ViewFlipper;
        import androidx.annotation.Nullable;
        import androidx.appcompat.widget.Toolbar;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentManager;
        import androidx.fragment.app.FragmentPagerAdapter;
        import androidx.viewpager.widget.ViewPager;

        import com.SiJoLi.SiJoLi.Account.LoginMenu;
        import com.google.android.material.tabs.TabLayout;
        import com.google.firebase.auth.FirebaseAuth;

        import java.util.ArrayList;
        import java.util.List;

public class HomeControllerFragment extends Fragment {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
        R.drawable.icon_sepeda,
                R.drawable.ic_photocamera,
    };

    ViewFlipper viewFlipper;
    private TextView emailshow;
    public TextView emailget, saldo;
    public Long jmlsaldo;
    private Button btnChangePassword, btnRemoveUser,
            changePassword, remove, signOut;
    private TextView email, emailhome;
    private EditText oldEmail, password, newPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_controller,
                container, false);

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager_fragment_controller);
        setupViewPager(viewPager);

//        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//        setupTabIcons();

        return rootView;
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
//        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new Home(), "TELUSURI");
        adapter.addFrag(new BlankFragment(), "FEED");
        viewPager.setAdapter(adapter);
    }

}