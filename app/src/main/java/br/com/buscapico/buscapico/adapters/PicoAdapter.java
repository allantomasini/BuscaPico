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
import br.com.buscapico.buscapico.models.Pico;

/**
 * Created by Allan on 23/05/2017.
 */

public class PicoAdapter extends RecyclerView.Adapter<PicoAdapter.ViewHolder>
        implements View.OnClickListener {
    private List<Pico> picos;
    private View.OnClickListener listener;
    private Context context;

    public PicoAdapter(List<Pico> pico, Context context) {
        this.picos = pico;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public PicoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pico_layout, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String url = picos.get(position).getUrlFoto() == null ? "" : picos.get(position).getUrlFoto();
        String local = picos.get(position).getEndereco().getCidade() + ", " +
                picos.get(position).getEndereco().getEstado();

        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.default_park)
                .into(holder.iviFoto);

        holder.tviNome.setText(picos.get(position).getNome());
        holder.tviTipo.setText(picos.get(position).getTipo());
        holder.tviLocal.setText(local);
        holder.tviDistancia.setText(String.format("%.2f", picos.get(position).getEndereco().getHaversine()) + "km");
    }

    @Override
    public int getItemCount() {
        return picos.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iviFoto;
        TextView tviNome;
        TextView tviTipo;
        TextView tviLocal;
        TextView tviDistancia;

        ViewHolder(View v) {
            super(v);
            iviFoto = (ImageView) v.findViewById(R.id.ivi_foto);
            tviNome = (TextView) v.findViewById(R.id.tvi_nome);
            tviTipo = (TextView) v.findViewById(R.id.tvi_tipo);
            tviLocal = (TextView) v.findViewById(R.id.tvi_local);
            tviDistancia = (TextView) v.findViewById(R.id.tvi_distancia);
        }
    }
}
