package com.example.log3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    Context context;
    ArrayList contact_id, contact_name, contact_email, contact_dob, contact_image;
    CustomAdapter(Context context,
                  ArrayList contact_id,
                  ArrayList contact_name,
                  ArrayList contact_dob,
                  ArrayList contact_email,
                  ArrayList contact_image) {
        this.context = context;
        this.contact_id = contact_id;
        this.contact_name = contact_name;
        this.contact_dob = contact_dob;
        this.contact_email = contact_email;
        this.contact_image = contact_image;
    }
    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.contact_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position)  {
        holder.contact_id_txt.setText(String.valueOf(contact_id.get(position)));
        holder.name_txt.setText(String.valueOf(contact_name.get(position)));
        holder.dob_txt.setText(String.valueOf(contact_dob.get(position)));
        holder.email_txt.setText(String.valueOf(contact_email.get(position)));
//        holder.image_txt.setText(String.valueOf(contact_image.get(position)));
        int imageResourceId; // Biến để lưu trữ ID tài nguyên hình ảnh
        int imageValue = Integer.parseInt(contact_image.get(position).toString());
        // Sử dụng switch-case để gán hình ảnh từ drawable dựa trên giá trị contact_image
        switch (imageValue) {
            case 1:
                imageResourceId = R.drawable.card;
                break;
            case 2:
                imageResourceId = R.drawable.android;
                break;
            case 3:
                imageResourceId = R.drawable.apple;
                break;
            // Thêm các trường hợp khác tùy theo giá trị của contact_image
            default:
                imageResourceId = R.drawable.card; // Hình ảnh mặc định nếu không có trường hợp nào khớp
                break;
        }
        // Đặt hình ảnh cho ImageView dựa trên ID tài nguyên
        holder.imageView.setImageResource(imageResourceId);
    }

    @Override
    public int getItemCount() {
        return contact_id.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView contact_id_txt, name_txt, dob_txt, email_txt;
        ImageView imageView;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contact_id_txt=itemView.findViewById(R.id.contact_id_txt);
            name_txt=itemView.findViewById(R.id.name_txt);
            dob_txt=itemView.findViewById(R.id.dob_txt);
            email_txt=itemView.findViewById(R.id.email_txt);
//            image_txt=itemView.findViewById(R.id.image_txt);
            imageView=itemView.findViewById(R.id.imageView);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
