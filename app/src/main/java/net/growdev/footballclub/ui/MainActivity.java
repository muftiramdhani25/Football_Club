package net.growdev.footballclub.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import net.growdev.footballclub.R;
import net.growdev.footballclub.data.model.ResponseAllTeam;
import net.growdev.footballclub.data.model.TeamsItem;
import net.growdev.footballclub.data.remote.ApiClient;
import net.growdev.footballclub.data.remote.ApiInterface;
import net.growdev.footballclub.helper.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvTeam;
    SwipeRefreshLayout swipeRefreshLayout;

    private List<TeamsItem> teamDataList;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTeam = findViewById(R.id.recycler_main);
        swipeRefreshLayout = findViewById(R.id.swipe_main);

        getData();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(false);
            getData();
        });
    }

    private void getData(){
        showProgress();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseAllTeam> call = apiInterface.getAllTeam(Constant.LEAGUE_NAME);
        call.enqueue(new Callback<ResponseAllTeam>() {
            @Override
            public void onResponse(Call<ResponseAllTeam> call, Response<ResponseAllTeam> response) {
                progressDialog.dismiss();

                ResponseAllTeam teamResponse = response.body();

                teamDataList = teamResponse.getTeams();

                rvTeam.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rvTeam.setAdapter(new MainAdapter(MainActivity.this, teamDataList));
            }

            @Override
            public void onFailure(Call<ResponseAllTeam> call, Throwable throwable) {
                progressDialog.dismiss();

                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgress(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}
