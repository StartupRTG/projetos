package startuprtg.tales.ebac;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Raphael on 16/08/2018.
 */

public class BoletoAdapter extends RecyclerView.Adapter<BoletoAdapter.BoletoViewHolder> {
    public static class BoletoViewHolder extends RecyclerView.ViewHolder {
        TextView boletoName;
        TextView boletoExpire;
        TextView boletoValue;
        ImageButton cpBtn;
        ImageButton dwnBtn;

        BoletoViewHolder(final View itemView){
            super(itemView);
            boletoName = (TextView) itemView.findViewById(R.id.info_text1);
            boletoExpire = (TextView) itemView.findViewById(R.id.info_text2);
            boletoValue = (TextView) itemView.findViewById(R.id.info_text3);
            cpBtn = (ImageButton) itemView.findViewById(R.id.cp_btn);
            dwnBtn = (ImageButton) itemView.findViewById(R.id.dwn_btn);

            cpBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = "0987654321";
                    Toast.makeText(view.getContext(), "Código de barras copiado para área de transferência.", Toast.LENGTH_LONG).show();
                    int sdk = android.os.Build.VERSION.SDK_INT;
                    if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                        android.text.ClipboardManager clipboard = (android.text.ClipboardManager) view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                        clipboard.setText(text);
                    } else {
                        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                        android.content.ClipData clip = android.content.ClipData.newPlainText("Label", text);
                        clipboard.setPrimaryClip(clip);
                    }

                    Toast.makeText(view.getContext(), "Código de barras copiado para área de transferência.", Toast.LENGTH_LONG).show();
                }
            });

            dwnBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    
                }
            });
        }
    }

    List<Boleto> boletos;

    BoletoAdapter(List<Boleto> boletos){
        this.boletos = boletos;
    }

    @Override
    public int getItemCount() {
//        return boletos.size();
        return 2;
    }

    @Override
    public BoletoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        BoletoViewHolder bvh = new BoletoViewHolder(v);
        return bvh;
    }

    @Override
    public void onBindViewHolder(BoletoViewHolder holder, int position) {
    }
}
