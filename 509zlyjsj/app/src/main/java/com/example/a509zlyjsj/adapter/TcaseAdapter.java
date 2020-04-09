package com.example.a509zlyjsj.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a509zlyjsj.R;
import com.example.a509zlyjsj.activities.TcaseViewActivity;
import com.example.a509zlyjsj.bean.ListBean;
import com.example.a509zlyjsj.common.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TcaseAdapter extends RecyclerView.Adapter {
    private List<ListBean> list;//向rv中填充的数据
    private Context context;//上下文
    private LayoutInflater layoutInflater;//动态布局

    //自定义 构造方法
    public TcaseAdapter(Context context)
    {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }
    //自定义 ViewHolder子类，容纳item视图
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvtitle,tvauthor,tvtime;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            tvtitle=itemView.findViewById(R.id.textView);
            tvauthor=itemView.findViewById(R.id.textView2);
            tvtime=itemView.findViewById(R.id.textView3);
        }
    }
    //自定义 设置数据list
    public void setList(List<ListBean> list)
    {
        this.list=list;
        notifyDataSetChanged();//通知RV刷新数据
    }


    @NonNull
    @Override  //为每一个item生成一个view
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=layoutInflater.inflate(
                R.layout.item,parent,false);

        return new ViewHolder(v);
    }

    @Override  //为每个item填充数据，设置点击事件
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ListBean  bean=list.get(position);
        if(bean==null)
            return;
        ViewHolder viewHolder=(ViewHolder)holder;
        Picasso.with(context)//新版的Picasso方法改为get()
                .load(Common.IMAGEURL+bean.getThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imageView);
        viewHolder.tvtitle.setText(bean.getName());
        if(bean.getAuthor()!="")
            viewHolder.tvauthor.setText(bean.getAuthor());
        else
            viewHolder.tvauthor.setText("未知");
        viewHolder.tvtime.setText(bean.getUpdate_time());

        //item条目点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取出当前item的id
                int id=list.get(position).getId();
                Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(context, TcaseViewActivity.class);
                intent.putExtra("resid",bean.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override  //确定item个数
    public int getItemCount() {
        if(list!=null)
            return list.size();
        else
            return 0;
    }
}