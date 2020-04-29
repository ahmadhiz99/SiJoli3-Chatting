package com.SiJoLi.SiJoLi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class Home extends Fragment {

    ViewFlipper viewFlipper;
    private TextView emailshow;
    public TextView emailget,saldo;
    public Long jmlsaldo;
    private Button btnChangePassword, btnRemoveUser,
            changePassword, remove, signOut;
    private TextView email, emailhome;
    private EditText oldEmail, password, newPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
//    private GoogleMap mMap;
    private Uri filePath;
    Bitmap bitmap;
    ImageView imgtest;

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<modelUser, UserViewHolder> mAdapter;
    private ArrayList<modelUser> listArtikel;

    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    ImageView imageView;
    String urlimage;
    String url;
     String uid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home,
                container, false);

//        imgtest = rootView.findViewById(R.id.img_test);
//        imgtest.setImageResource(R.drawable.ic_launcher_background);

        //current user
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("UserAccount");
        String b = url;
        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
         uid = currentUser.getUid();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        myRef.child(uid).child("Photo").child("Galeri").setValue(b);

        imageView = rootView.findViewById(R.id.img_user);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mRecycler = rootView.findViewById(R.id.list_user);
        mRecycler.setHasFixedSize(true);
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);
        mRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        // Set up FirebaseRecyclerAdapter with the Query
        Query query = getQuery(mDatabase);
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<modelUser>()
                .setQuery(query, modelUser.class)
                .build();
        mAdapter = new FirebaseRecyclerAdapter<modelUser, UserViewHolder>(options) {
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                //presitance
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new UserViewHolder(inflater.inflate(R.layout.item_user, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull final UserViewHolder holder, int position, @NonNull final modelUser model) {
                holder.bindToArtikel(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getContext(), DetailUser.class);
                        intent.putExtra(DetailUser.EXTRA_ID, uid);
                        intent.putExtra(DetailUser.EXTRA_NAMA, model.getnama());
                        intent.putExtra(DetailUser.EXTRA_KOTA, model.getkota());
                        intent.putExtra(DetailUser.EXTRA_PHOTO_PROFIL, model.getPhotoProfil());
                        intent.putExtra(DetailUser.EXTRA_LATITUDE, model.getLatitude());
                        intent.putExtra(DetailUser.EXTRA_LONGITUDE, model.getLongitude());
                        toast("Click");
                        startActivity(intent);

                    }
                });
            }
        };

        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
//        UserViewHolder.setDisplayImage(imageslink, UserViewHolder);

        return rootView;
    }

    private void toast(String text) {
        Toast.makeText(getActivity(), text ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    private Query getQuery(DatabaseReference mDatabase){
        Query query = mDatabase.child("UserAccount");
        return query;
    }
//    private void setupViewPager(ViewPager viewPager) {
//        HomeControllerFragment.ViewPagerAdapter adapter = new Hom.ViewPagerAdapter(getActivity().getSupportFragmentManager());
//        adapter.addFrag(new Home(), "TELUSURI");
//        adapter.addFrag(new BlankFragment(), "FEED");
//        viewPager.setAdapter(adapter);
//    }
}
