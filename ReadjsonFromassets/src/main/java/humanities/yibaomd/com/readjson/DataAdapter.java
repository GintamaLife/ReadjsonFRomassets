package humanities.yibaomd.com.readjson;

/**
 * Created by android on 10/26/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
private Context context;
    private List<DataModel.GeneralNewsBean> dataList;
    private Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, pubDate, category_name;
        public ImageView myimage;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            category_name = (TextView) view.findViewById(R.id.category_name);
            pubDate = (TextView) view.findViewById(R.id.pubDate);
            myimage= (ImageView) view.findViewById(R.id.image);

        }
    }


    public DataAdapter(Context context, List<DataModel.GeneralNewsBean> moviesList) {
        this.dataList = moviesList;
        this.context=context;
    }

    public DataAdapter(Context context, List<DataModel.GeneralNewsBean> dataList, Activity activity) {
        this.dataList = dataList;
        this.activity = activity;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final DataModel.GeneralNewsBean generalNewsBean = dataList.get(position);

        holder.title.setText(generalNewsBean.getData().getTitle());
        holder.category_name.setText(generalNewsBean.getData().getCategory_name());
        holder.pubDate.setText(generalNewsBean.getData().getPubDate());

//




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setStartAnimations(activity, R.anim.slide_in_right, R.anim.slide_out_left);
                builder.setExitAnimations(activity, R.anim.slide_in_left, R.anim.slide_out_right);
                builder.setToolbarColor(activity.getResources().getColor(R.color.colorPrimary));
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(activity, Uri.parse(generalNewsBean.getData().getDetail_description_url()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
