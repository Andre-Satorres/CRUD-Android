package aula1.unicamp.br.crud_alunos;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
{
    private Context mContext;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        final Cliente clienteRest = new Cliente();

        Button btnConsultar = findViewById(R.id.btnConsultar);
        Button btnConsultarPorRa = findViewById(R.id.btnConsultarRA);
        Button btnInserir = findViewById(R.id.btnInserir);
        Button btnAlterar = findViewById(R.id.btnAlterar);
        Button btnExcluir = findViewById(R.id.btnExcluir);

        btnConsultar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try {
                    clienteRest.consulta("http://localhost:8080/EscolaApp/webresources/generic/consultarTodos");
                } catch (IOException e) {
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
                    // Initialize a new instance of LayoutInflater service
                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                    // Inflate the custom layout/view
                    View customView = inflater.inflate(R.layout.insere_ra,null);

                    // Initialize a new instance of popup window
                    mPopupWindow = new PopupWindow(
                            customView,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );

                    // Set an elevation value for popup window
                    // Call requires API level 21
                    if(Build.VERSION.SDK_INT>=21){
                        mPopupWindow.setElevation(5.0f);
                    }

                    // Get a reference for the custom view close button
                    Button closeButton = customView.findViewById(R.id.btnEnvRa);

                    // Set a click listener for the popup window close button
                    closeButton.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View view) {
                                                           // Dismiss the popup window
                                                           mPopupWindow.dismiss();
                                                       }
                                                   });

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
                try {
                    clienteRest.inclui("http://localhost:8080/EscolaApp/webresources/generic/incluirAluno");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnAlterar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try {
                    clienteRest.altera("http://localhost:8080/EscolaApp/webresources/generic/alterarAluno");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try {
                    clienteRest.exclui("http://localhost:8080/EscolaApp/webresources/generic/excluirAluno/" + clienteRest.perguntasRA());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
