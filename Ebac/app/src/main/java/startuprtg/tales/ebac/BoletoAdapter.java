package startuprtg.tales.ebac;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Raphael on 16/08/2018.
 */

public class BoletoAdapter extends RecyclerView.Adapter<BoletoAdapter.BoletoViewHolder> {
    public static class BoletoViewHolder extends RecyclerView.ViewHolder {
        TextView boletoName;
        TextView boletoExpire;
        TextView boletoValue;

        BoletoViewHolder(View itemView){
            super(itemView);
            boletoName = (TextView) itemView.findViewById(R.id.info_text1);
            boletoExpire = (TextView) itemView.findViewById(R.id.info_text2);
            boletoValue = (TextView) itemView.findViewById(R.id.info_text3);
        }
    }

    List<Boleto> boletos;

    BoletoAdapter(List<Boleto> boletos){
        this.boletos = boletos;
    }

    @Override
    public int getItemCount() {
//        return boletos.size();
        return 10;
    }

    @Override
    public BoletoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        BoletoViewHolder bvh = new BoletoViewHolder(v);
        return bvh;
    }

    @Override
    public void onBindViewHolder(BoletoViewHolder holder, int position) {
//        if(boletos != null){
//            holder.boletoName.setText(boletos.get(position).getNome());
//            holder.boletoExpire.setText(boletos.get(position).getData());
//            holder.boletoValue.setText(boletos.get(position).getValor());
//        }
    }
}
