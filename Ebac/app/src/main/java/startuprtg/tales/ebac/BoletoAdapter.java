package startuprtg.tales.ebac;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Raphael on 16/08/2018.
 */

public class BoletoAdapter extends RecyclerView.Adapter<BoletoAdapter.BoletoViewHolder> {
    public static class BoletoViewHolder extends RecyclerView.ViewHolder {
        Button boletoNome;

        BoletoViewHolder(View itemView){
            super(itemView);
            boletoNome = (Button) itemView.findViewById(R.id.boleto_nome);
        }
    }

    List<Boleto> boletos;

    BoletoAdapter(List<Boleto> boletos){
        this.boletos = boletos;
    }

    @Override
    public int getItemCount() {
//        return boletos.size();
        return 3;
    }

    @Override
    public BoletoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        BoletoViewHolder bvh = new BoletoViewHolder(v);
        return bvh;
    }

    @Override
    public void onBindViewHolder(BoletoViewHolder holder, int position) {
        holder.boletoNome.setText("Boleto");
    }

}
