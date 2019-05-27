package aula1.unicamp.br.crud_alunos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class insereTudo extends AppCompatActivity
{
    Button btnInserir;
    TextView tv_nome, tv_ra, tv_email;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_tudo);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height= dm.heightPixels;

        getWindow().setLayout((int)(width *0.8), (int)(height*.8));

        btnInserir = findViewById(R.id.btnInserir);
        tv_ra = findViewById(R.id.tv_raIns);
        tv_nome = findViewById(R.id.tv_nomeIns);
        tv_email = findViewById(R.id.tv_emailIns);

        btnInserir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("ra", tv_ra.getText().toString().trim());
                returnIntent.putExtra("nome", tv_nome.getText().toString().trim());
                returnIntent.putExtra("email", tv_email.getText().toString().trim());
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}
