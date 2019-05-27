package aula1.unicamp.br.crud_alunos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaTudoAdapter extends ArrayAdapter<Aluno>
{
    private Context contexto;
    private ArrayList<Aluno> alunos = null;

    public ListaTudoAdapter(Context c, ArrayList<Aluno> lista)
    {
        super(c, 0, lista);
        this.contexto = c;
        this.alunos = lista;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        Aluno aluno = alunos.get(position);

        if(view == null)
            view = LayoutInflater.from(contexto).inflate(R.layout.lista_todos, null);

        TextView tvRA = view.findViewById(R.id.txt_ra);
        tvRA.setText(aluno.getRa());

        TextView tvNome = view.findViewById(R.id.txt_nome);
        tvNome.setText(aluno.getNome());

        TextView tvEmail = view.findViewById(R.id.txt_email);
        tvEmail.setText(aluno.getEmail());

        return view;
    }
}
