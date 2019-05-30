package aula1.unicamp.br.crud_alunos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class insere_nome extends AppCompatActivity
{
    Button btnConsultar;
    TextView tv_nome;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_nome);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height= dm.heightPixels;

        getWindow().setLayout((int)(width *0.8), (int)(height*.45));

        btnConsultar = findViewById(R.id.btnEnvRa);
        tv_nome = findViewById(R.id.tv_nome);

        btnConsultar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("nome", tv_nome.getText().toString().trim());
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}
