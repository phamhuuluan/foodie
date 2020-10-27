package com.t3h.foodie;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.t3h.foodie.adapter.SearchAdapter;
import com.t3h.foodie.api.ApiBuilder;
import com.t3h.foodie.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
//    private RecyclerView recyclerView;
//    private RecyclerView.LayoutManager layoutManager;
//    private List<Product> product;
//    ProgressBar progressBar;
//    private SearchAdapter searchAdapter;
    private SearchView searchView;


    private ListView listView;
    String[] nameList = {"VietNam ", "England ", "ThaiLand ", "India ", "Singapore ", "China ", "Japan ", "Combodia ", "Cuba ", "France "};
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
//        initViews();
//        fetchProduct("");
//        listView = (ListView) findViewById(R.id.myListView);
//        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,nameList );
//
//        listView.setAdapter(arrayAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar_search_menu, menu);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        return true;
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.toolbar_search_menu, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
////        SearchView searchView = (SearchView) item.getActionView();
////        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////            @Override
////            public boolean onQueryTextSubmit(String s) {
////                return false;
////            }
////
////            @Override
////            public boolean onQueryTextChange(String s) {
////                arrayAdapter.getFilter().filter(s);
////                return false;
////            }
////        });
//        return super.onCreateOptionsMenu(menu);
//    }

    //
//    private void initViews() {
//        progressBar = findViewById(R.id.progress);
//        recyclerView = findViewById(R.id.rc_search);
//        searchAdapter = new SearchAdapter(getLayoutInflater());
//        recyclerView.setAdapter(searchAdapter);
//        recyclerView.setHasFixedSize(true);
//    }

//    public  void fetchProduct(String key){
//        ApiBuilder.getInstance().getSearch(key).enqueue(new Callback<List<Product>>() {
//            @Override
//            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
//                progressBar.setVisibility(View.GONE);
//                List<Product> data = response.body();
//                searchAdapter.setData(data);
//            }
//
//            @Override
//            public void onFailure(Call<List<Product>> call, Throwable t) {
//                progressBar.setVisibility(View.GONE);
//                Toast.makeText(SearchActivity.this, "Error on:" + t.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


}
