package com.abhi.twitter.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhi.twitter.Camera;
import com.abhi.twitter.Login_activity;
import com.abhi.androidprojecttwitter.R;
import com.abhi.twitter.adapter.TweetAdapter;
import com.abhi.twitter.api.ApiClass;
import com.abhi.twitter.model.TweetM;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    Camera cm = new Camera();
    Login_activity la = new Login_activity();
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate( R.layout.fragment_home, container, false );
        recyclerView = root.findViewById( R.id.HomeRV );

        loadCurrentUser();


        return root;
    }

    private void loadCurrentUser() {
        String token;
        if (la.Token.isEmpty()) {
            token = cm.token;
            //Toast.makeText(getContext(), "token " +token, Toast.LENGTH_SHORT).show();

        } else {
            token = la.Token;
            // Toast.makeText(getContext(), "token " +token, Toast.LENGTH_SHORT).show();

        }

        ApiClass usersAPI = new ApiClass();

        Call<List<TweetM>> userCall = usersAPI.calls().GetTweet( token );
        userCall.enqueue( new Callback<List<TweetM>>() {
            @Override
            public void onResponse(Call<List<TweetM>> call, Response<List<TweetM>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText( getContext(), "Code " + response.code(), Toast.LENGTH_SHORT ).show();
                    return;
                }
                List<TweetM> tweetMS = response.body();

                TweetAdapter tweetAdapter = new TweetAdapter( getContext(), tweetMS );
                recyclerView.setAdapter( tweetAdapter );
                recyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
            }

            @Override
            public void onFailure(Call<List<TweetM>> call, Throwable t) {
                Toast.makeText( getContext(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();

            }
        } );


    }
}