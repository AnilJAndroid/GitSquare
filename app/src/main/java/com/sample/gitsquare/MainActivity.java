package com.sample.gitsquare;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sample.gitsquare.Adapter.ContributeAdapter;
import com.sample.gitsquare.Model.ContributeModel;
import com.sample.gitsquare.utills.Commons;
import com.sample.gitsquare.utills.Utils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe_refresh_layout;
    private TextView txt_filter_contributions;
    private String base_url = "https://api.github.com/";
    private Retrofit retrofit;
    private ContributeAdapter adapter;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder().baseUrl(base_url).build();

        initview();
        getListContributors();
        txt_filter_contributions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    adapter.filter_contributes(true);
                    flag=false;
                }else {
                    adapter.filter_contributes(false);
                    flag=true;
                }
            }
        });
    }
    /*initilize view*/
    void initview(){
        swipe_refresh_layout = findViewById(R.id.swipe_refresh_layout);
        txt_filter_contributions = findViewById(R.id.txt_filter_contributions);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new ContributeAdapter(getApplicationContext(),new ArrayList<ContributeModel>());
        recyclerView.setAdapter(adapter);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(0,0);
        swipe_refresh_layout.setOnRefreshListener(this);
    }

    /*getAllContributors*/
    void getListContributors(){
       if(Utils.isOnline(MainActivity.this)){
            Call_API();
       }else {
           swipe_refresh_layout.setRefreshing(false);
           Utils.noInternetDialog(MainActivity.this);
       }
    }

    @Override
    public void onRefresh() {
        getListContributors();
    }
    public interface GitHubService {
        @GET("repos/square/retrofit/contributors")
        Call<ResponseBody> listRepos();
    }
    void Call_API(){
        Commons.showProgress(MainActivity.this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                GitHubService service = retrofit.create(GitHubService.class);
                Call<ResponseBody> contributes = service.listRepos();
                contributes.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                        Commons.progressDismiss();
                        try {
                            final List<ContributeModel> models = new ArrayList<>();
                            JSONArray jsonArray = new JSONArray(response.body().string());
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                ContributeModel model = new ContributeModel();
                                model.setAvtar_url(object.getString("avatar_url"));
                                model.setContributions(object.getInt("contributions"));
                                model.setRepos_url(object.getString("repos_url"));
                                model.setUsername(object.getString("login"));
                                models.add(model);
                            }
                            swipe_refresh_layout.setRefreshing(false);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.addModels(models);
                                }
                            });
                        } catch (IOException|JSONException e) {
                            Utils.showDialog(MainActivity.this,e.getMessage());
                            swipe_refresh_layout.setRefreshing(false);
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Commons.progressDismiss();
                        Utils.showDialog(MainActivity.this,t.getMessage());
                    }
                });
            }
        }).start();
    }
}
