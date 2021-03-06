package aula1.unicamp.br.crud_alunos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity
{
    Cliente clienteRest = new Cliente();
    ListView lvAlunos;
    ListaTudoAdapter alunosAdapter;
    TextView tvAlunos;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnConsultar = findViewById(R.id.btnConsultar);
        Button btnConsultarPorRa = findViewById(R.id.btnConsultarRA);
        Button btnConsultarPorNome = findViewById(R.id.btnConsultarPorNome);
        Button btnInserir = findViewById(R.id.btnInserir);
        Button btnAlterar = findViewById(R.id.btnAlterar);
        Button btnExcluir = findViewById(R.id.btnExcluir);
        lvAlunos = findViewById(R.id.lista_tudo);
        tvAlunos = findViewById(R.id.tvAlunos);

        tvAlunos.setVisibility(View.INVISIBLE);
        lvAlunos.setVisibility(View.INVISIBLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            lvAlunos.setNestedScrollingEnabled(true);
        }
        alunosAdapter = new ListaTudoAdapter(this, new ArrayList<Aluno>());
        lvAlunos.setAdapter(alunosAdapter);

        btnConsultar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    buscarDados("http://10.0.2.2:8080/EscolaApp/webresources/generic/consultarTodos", "ConsultarTodos", null, null, null);

                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        btnConsultarPorRa.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    lvAlunos.setVisibility(View.INVISIBLE);
                    tvAlunos.setVisibility(View.INVISIBLE);

                    Intent i = new Intent(MainActivity.this, InsereRa.class);

                    startActivityForResult(i, 1);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        btnConsultarPorNome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    lvAlunos.setVisibility(View.INVISIBLE);
                    tvAlunos.setVisibility(View.INVISIBLE);

                    Intent i = new Intent(MainActivity.this, insere_nome.class);
                    startActivityForResult(i, 2);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        btnInserir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    lvAlunos.setVisibility(View.INVISIBLE);
                    tvAlunos.setVisibility(View.INVISIBLE);

                    Intent i = new Intent(MainActivity.this, insereTudo.class);
                    startActivityForResult(i, 3);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        btnAlterar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    lvAlunos.setVisibility(View.INVISIBLE);
                    tvAlunos.setVisibility(View.INVISIBLE);

                   // Intent i = new Intent(MainActivity.this, inserir_alterar.class);
                    Intent i = new Intent(MainActivity.this, InsereRa.class);
                    startActivityForResult(i, 4);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    lvAlunos.setVisibility(View.INVISIBLE);
                    tvAlunos.setVisibility(View.INVISIBLE);

                    Intent i = new Intent(MainActivity.this, Excluir.class);
                    startActivityForResult(i, 5);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == 1)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                String ra = data.getStringExtra("ra");
                buscarDados("http://10.0.2.2:8080/EscolaApp/webresources/generic/consultaPeloRA/" + ra, "ConsultarPorRA", null, null, null);
            }
        }
        else
        if(requestCode == 2)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                String nome = data.getStringExtra("nome").replace(" ", "%20");;
                buscarDados("http://10.0.2.2:8080/EscolaApp/webresources/generic/consultaPeloNome/" + nome, "ConsultarPorNome", null, null, null);
            }
        }
        else
        if(requestCode == 3)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                String ra = data.getStringExtra("ra");
                String nome = data.getStringExtra("nome");
                String email = data.getStringExtra("email");
                buscarDados("http://10.0.2.2:8080/EscolaApp/webresources/generic/incluirAluno/", "Incluir", ra, nome, email);
            }
        }
        else
        if(requestCode == 4)
        {
            try {
                if (resultCode == Activity.RESULT_OK) {
                    String ra = data.getStringExtra("ra");

                    Intent i = new Intent(MainActivity.this, inserir_alterar.class);
                    Bundle b = new Bundle();
                    b.putString("ra", ra);

                    i.putExtras(b);
                    startActivityForResult(i, 6);
                }
            }
            catch(Exception e)
            {}
        }
        else
        if(requestCode == 5)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                String ra = data.getStringExtra("ra");
                buscarDados("http://10.0.2.2:8080/EscolaApp/webresources/generic/excluirAluno/" + ra, "Excluir", ra, null, null);
            }
        }
        else
        if(requestCode == 6)
        {
            if(resultCode == Activity.RESULT_OK)
            {

                String ra = data.getStringExtra("ra");
                String nome = data.getStringExtra("nome");
                String email = data.getStringExtra("email");
                buscarDados("http://10.0.2.2:8080/EscolaApp/webresources/generic/alterarAluno/", "Alterar", ra, nome, email);
            }
        }
    }//onActivityResult

    private void buscarDados(String url, String modo, String ra, String nome, String email)
    {
        MinhaAsyncTask minhaAsyncTask = new MinhaAsyncTask();

        minhaAsyncTask.execute(url, modo, ra, nome, email);
    }

    private void atualizarTela(final ArrayList<Aluno> alunos)
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {

                try {
                    alunosAdapter.clear();

                    for (Aluno a : alunos)
                        alunosAdapter.add(a);

                    lvAlunos.setAdapter(alunosAdapter);
                    lvAlunos.setVisibility(View.VISIBLE);
                    tvAlunos.setVisibility(View.VISIBLE);
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Nenhum resultado encontrado...", Toast.LENGTH_SHORT).show();
                    tvAlunos.setVisibility(View.INVISIBLE);
                    lvAlunos.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void commit(final String s)
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class MinhaAsyncTask extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... parametros)
        {
            try {
                switch (parametros[1])
                {
                    case "ConsultarTodos": atualizarTela(clienteRest.consulta(parametros[0]));break;
                    case "ConsultarPorRA": atualizarTela(clienteRest.consulta(parametros[0]));break;
                    case "ConsultarPorNome": atualizarTela(clienteRest.consulta(parametros[0]));break;
                    case "Incluir": commit(clienteRest.inclui(parametros[0], new Aluno(parametros[2], parametros[3], parametros[4])));break;
                    case "Alterar": commit(clienteRest.altera(parametros[0], new Aluno(parametros[2], parametros[3], parametros[4])));break;
                    case "Excluir": commit(clienteRest.exclui(parametros[0]));break;
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return "e";
            }

            return "a";
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            if(s.equals("e"))
                Toast.makeText(getApplicationContext(), "Dados inseridos inválidos...", Toast.LENGTH_SHORT).show();
        }
    }
}