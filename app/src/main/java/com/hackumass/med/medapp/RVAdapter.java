package com.hackumass.med.medapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hackumass.med.medapp.Database.User;
import com.hackumass.med.medapp.R;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ToDoViewHolder> implements Filterable {

    public Context context;
    public List<User> toDos;
    public List<User> searches;
    public ToDoClickListener listener;
    public static final String TITLE_KEY = "title";
    public static final String ID_KEY = "id";
    public static final String DESCRIPTION_KEY = "description";
    public static final String DATE_KEY = "date";
    public static final String TIME_KEY = "time";
    public static final String LOCATION_KEY = "location";

    public RVAdapter(Context context, List<User> toDos, ToDoClickListener listener){
        this.context = context;
        this.toDos = toDos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.cardview,parent,false);
        return new ToDoViewHolder(itemView,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, final int position) {
        User user = toDos.get(position);

        holder.day_medications.setText(user.medications);
        holder.day_symptoms.setText(user.symptoms);
        holder.date.setText(user.date);
        int pain = user.pain;

        if(pain == 0){
            holder.emoji.setImageResource(R.drawable.superhappy);
        }
        else if(pain == 1){
            holder.emoji.setImageResource(R.drawable.happy);
        }
        else if(pain == 2){
            holder.emoji.setImageResource(R.drawable.moderate);
        }
        else if(pain == 3){
            holder.emoji.setImageResource(R.drawable.kindasad);
        }
        else if(pain == 4){
            holder.emoji.setImageResource(R.drawable.sad);
        }

    }

    public void removeItem(int position){
        toDos.remove(position);
        notifyItemRemoved(position);

    }
    public void restoreItem(User toDo, int position){
        toDos.add(position,toDo);
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount() {
        return toDos.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString();

                List<User> filtered = new ArrayList<>();

                if (query.isEmpty()) {
                    filtered = searches;
                } else {
                    for (User movie : searches) {
//                        if (movie.title.toLowerCase().contains(query.toLowerCase())) {
//                            filtered.add(movie);
//                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.count = filtered.size();
                results.values = filtered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                searches = (List<User>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ToDoClickListener {
        void onToDoClick(View view, int position);
        void onToDoLongClick(View view,int position);
        void buttonClick(View view,int position);
    }

    public static class ToDoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        TextView day_medications;
        TextView day_symptoms;
        TextView date;
        ImageView emoji;
        CheckBox itemCheckbox;
        TextView descriptionTextView;
        ToDoClickListener toDoClickListener;
        LinearLayout viewForeground;

        public ToDoViewHolder(View itemView, ToDoClickListener listener) {
            super(itemView);
            toDoClickListener = listener;

            day_medications = itemView.findViewById(R.id.day_medications);
            day_symptoms = itemView.findViewById(R.id.day_symptoms);
            date = itemView.findViewById(R.id.date);
            emoji = itemView.findViewById(R.id.emoji);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            toDoClickListener.onToDoClick(view,getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            toDoClickListener.onToDoLongClick(v,getAdapterPosition());
            return true;
        }
    }



}
