package com.hackumass.med.medapp;

import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
        import java.util.List;
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.DetailsVH> {
    List<History> days;
    RVAdapter(List<History> days) {
        this.days = days;
    }
    @Override
    public DetailsVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        DetailsVH pvh = new DetailsVH(v);
        return pvh;
    }
    @Override
    public void onBindViewHolder(DetailsVH personViewHolder, int i) {
        History history = days.get(i);
        personViewHolder.dateText.setText(history.getDate());

        int pain = history.getPainLevel();
        String emojiString="";

        if (pain==0) {
            emojiString = "Super Happy";
            personViewHolder.emoji.setImageResource(R.drawable.superhappy);
        }
        else if(pain==1){
            emojiString="Happy";
            personViewHolder.emoji.setImageResource(R.drawable.happy);
        }
        else if (pain==2){
            emojiString="Moderate";
            personViewHolder.emoji.setImageResource(R.drawable.moderate);
        }
        else if (pain==3){
            emojiString="Sad";
            personViewHolder.emoji.setImageResource(R.drawable.kindasad);
        }
        else if (pain==4){
            emojiString="Super Sad";
            personViewHolder.emoji.setImageResource(R.drawable.sad);
        }

        personViewHolder.mood.setText(emojiString);

        String symptoms = "Symptoms: ";
        if(history.getSymptoms().size()>0) {
            for(String s:history.getSymptoms())
                symptoms+=s+", ";
            symptoms.substring(0, symptoms.length()-2);
        }
        else
            symptoms="There were no symptoms on this day";
        String medications = "Medications: ";
        if(history.getMedications().size()>0) {
            for(Medication m:history.getMedications())
                medications+=m.getBrandName()+", ";
            medications.substring(0, medications.length()-2);
        }
        else
            medications="There were no medications on this day";
        personViewHolder.symptomsText.setText(symptoms);
        personViewHolder.medicationsText.setText(medications);
    }
    @Override
    public int getItemCount() {
        return days.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public static class DetailsVH extends RecyclerView.ViewHolder {
        TextView dateText;
        ImageView emoji;
        TextView symptomsText;
        TextView mood;
        TextView medicationsText;
        DetailsVH(View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.date);
            emoji = itemView.findViewById(R.id.emoji);
            symptomsText = itemView.findViewById(R.id.day_symptoms);
            medicationsText = itemView.findViewById(R.id.day_medications);
            mood = itemView.findViewById(R.id.mood);
        }
    }
}
