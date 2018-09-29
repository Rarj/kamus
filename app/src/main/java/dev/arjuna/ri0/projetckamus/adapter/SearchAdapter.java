package dev.arjuna.ri0.projetckamus.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dev.arjuna.ri0.projetckamus.DetailActivity;
import dev.arjuna.ri0.projetckamus.R;
import dev.arjuna.ri0.projetckamus.model.KamusModel;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private ArrayList<KamusModel> list = new ArrayList<>();

    public SearchAdapter() {

    }

    public void replaceAll(ArrayList<KamusModel> items){
        list = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_kamus, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvkata.setText(list.get(position).getKata());
        holder.tvTranslate.setText(list.get(position).getTranslate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.ITEM_KATA, list.get(position).getKata());
                intent.putExtra(DetailActivity.ITEM_TRANSLATE, list.get(position).getTranslate());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView tvkata, tvTranslate;
        SearchViewHolder(View itemView) {
            super(itemView);
            tvkata = itemView.findViewById(R.id.tv_kata);
            tvTranslate = itemView.findViewById(R.id.tv_translate);
        }
    }
}
