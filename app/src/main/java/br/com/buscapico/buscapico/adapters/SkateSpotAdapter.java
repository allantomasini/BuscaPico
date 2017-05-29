package br.com.buscapico.buscapico.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.buscapico.buscapico.R;
import br.com.buscapico.buscapico.models.SkateSpot;

/**
 * Created by Allan on 23/05/2017.
 */

public class SkateSpotAdapter extends RecyclerView.Adapter<SkateSpotAdapter.ViewHolder>
        implements View.OnClickListener {
    private List<SkateSpot> skateSpots;
    private View.OnClickListener listener;
    private Context context;

    public SkateSpotAdapter(List<SkateSpot> skateSpots, Context context){
        this.skateSpots = skateSpots;
        this.context = context;
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iviFoto;
        TextView tviNome;
        TextView tviTipo;
        TextView tviLocal;

        ViewHolder(View v) {
            super(v);
            iviFoto = (ImageView) v.findViewById(R.id.ivi_foto);
            tviNome = (TextView) v.findViewById(R.id.tvi_nome);
            tviTipo = (TextView) v.findViewById(R.id.tvi_tipo);
            tviLocal = (TextView) v.findViewById(R.id.tvi_local);
        }
    }@Override
    public SkateSpotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skate_spot_layout, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String url = skateSpots.get(position).getUrl() == null ?
                "" : skateSpots.get(position).getUrl();
        String local = skateSpots.get(position).getEndereco().getCidade()+ ", " +
                skateSpots.get(position).getEndereco().getEstado();


        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.default_park)
                .error(R.drawable.default_park)
                .into(holder.iviFoto);

        holder.tviNome.setText(skateSpots.get(position).getNome());
        holder.tviTipo.setText(skateSpots.get(position).getTipo());
        holder.tviLocal.setText(local);

    }

    @Override
    public int getItemCount() {
        return skateSpots.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }
}
