package humanities.yibaomd.com.readjson;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private List<DataModel.GeneralNewsBean> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DataAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        loadJSONFromAsset();
        try {

            JSONObject obj = new JSONObject(loadJSONFromAsset());
            Gson gson = new Gson();
            DataModel dataModel = gson.fromJson(obj.toString(), DataModel.class);
            Toast.makeText(MainActivity.this, obj.toString(), Toast.LENGTH_SHORT).show();

            dataList = dataModel.getGeneral_news();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.line_divider);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this, drawable, 5));
//        recyclerView.addItemDecoration(new RecyclerViewDivider(context, LinearLayoutManager.VERTICAL));
//        recyclerView.addItemDecoration();
//        recyclerView.addItemDecoration(new RecyclerViewDivider(context,LinearLayoutManager.VERTICAL, R.drawable.iv_mystore));
        if (dataList.size() > 0) {
            mAdapter = new DataAdapter(context, dataList, MainActivity.this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
//            添加默认分割线

//添加自定义分割线，可以设置可自定义分割线drawable

//添加自定义分割线，可以设置可高度和颜色
//            recyclerView.addItemDecoration(new RecyclerViewDivider(context,LinearLayoutManager.VERTICAL,10, ContextCompat.getColor(context, R.color.gray)));
            recyclerView.setAdapter(mAdapter);


        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("news.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
