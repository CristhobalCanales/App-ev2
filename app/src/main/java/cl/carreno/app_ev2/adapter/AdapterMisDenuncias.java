package cl.carreno.app_ev2.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.DialogInterface;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Objects;

import cl.carreno.app_ev2.R;
import cl.carreno.app_ev2.model.Denuncia;

public class AdapterMisDenuncias extends RecyclerView.Adapter<AdapterMisDenuncias.MisDenuciasHolder> {
    private int layout;
    private Activity activity;
    public List<Denuncia> list;
    FirebaseAuth auth;

    public AdapterMisDenuncias(Activity activity, int layout, List<Denuncia> list){
        this.layout = layout;
        this.list = list;
        this.activity = activity;
    }

    public class MisDenuciasHolder extends RecyclerView.ViewHolder {
        public TextView titulo , direccion , id;
        DatabaseReference reference;
        ImageView item_estado;
        Button eliminar;
        String idd, uid;

        public MisDenuciasHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.item_denuncia_id);
            eliminar = itemView.findViewById(R.id.eliminarDenuncia);
            titulo = itemView.findViewById(R.id.item_denuncia_title);
            direccion = itemView.findViewById(R.id.item_denuncia_direccion);
            item_estado = itemView.findViewById(R.id.item_estado);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    auth = FirebaseAuth.getInstance();
                    uid = auth.getCurrentUser().getUid();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference myRef = database.getReference("denuncias").child(uid);
                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(activity);
                    dialogo1.setTitle("ELIMINAR DENUNCIA");
                    dialogo1.setMessage("¿Estas seguro?");
                    dialogo1.setCancelable(false);
                    dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            myRef.child(idd).removeValue();
                            Toast.makeText(activity, "Denuncia Eliminada", Toast.LENGTH_SHORT).show();
                            activity.recreate();
                        }
                    });
                    dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            Toast.makeText(activity, "Error al eliminar Denuncia", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialogo1.show();
                }
            });

        }
    }

    @NonNull
    @Override
    public MisDenuciasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new MisDenuciasHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MisDenuciasHolder holder, int position) {
        Denuncia denuncias = list.get(position);
        holder.id.setText(denuncias.getId());
        holder.titulo.setText(denuncias.getTitulo());
        holder.direccion.setText(denuncias.getDireccion());
        holder.idd = denuncias.getId();

        int e = Integer.parseInt(denuncias.getEstado());
        if (e == 1){
            holder.item_estado.setImageResource(R.drawable.apagado);
        }
        if (e == 0){
            holder.item_estado.setImageResource(R.drawable.encendido);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
