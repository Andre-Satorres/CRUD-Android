package aula1.unicamp.br.crud_alunos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Excluir extends Activity
{
    Button btnExcluir;
    TextView tv_ra;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_excluir);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height= dm.heightPixels;

        getWindow().setLayout((int)(width *0.8), (int)(height*.6));

        btnExcluir = findViewById(R.id.btnEnvRa);
        tv_ra = findViewById(R.id.tv_ra);

        btnExcluir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("ra", tv_ra.getText().toString().trim());
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}
