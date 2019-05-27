package aula1.unicamp.br.crud_alunos;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Cliente
{
    public String perguntasRA()
    {
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
        for(;;)
        {
            System.out.println("");
            System.out.print("Digite o RA desejado: ");

            try
            {
                String ret = leitor.readLine();
                Integer.parseInt(ret);

                if(ret.length() != 5)
                    throw new Exception();

                return ret.trim();
            }
            catch(Exception e)
            {
                System.err.println("RA inválido! Redigite...");
            }
        }
    }

    public String perguntasNome()
    {
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("");
        System.out.print("Digite o nome desejado: ");

        try
        {
            return leitor.readLine().replace(" ", "%20");
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public String perguntasEmail()
    {
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("");
        System.out.print("Digite o email desejado: ");

        try
        {
            return leitor.readLine().trim();
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public ArrayList<Aluno> consulta(String url) throws MalformedURLException, IOException
    {
        try
        {
            URL objURL = new URL(url);
            HttpURLConnection conexao = (HttpURLConnection) objURL.openConnection();

            conexao.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

            String inputLine;
            String response = new String();

            while((inputLine = br.readLine()) != null)
                response += inputLine; //vai listando os alunos um a um

            ArrayList<Aluno> alunos = new ArrayList<Aluno>();

            try
            {
                JSONArray json = new JSONArray(response);
                int i=0;

                while(!(json.isNull(i)))
                {
                    Aluno a = new Gson().fromJson(json.get(i).toString(), Aluno.class);
                    alunos.add(a);
                    i++;
                }
            }
            catch(Exception e)
            {
                JSONObject json = new JSONObject(response);
                Aluno a = new Gson().fromJson(json.toString(), Aluno.class);
                alunos.add(a);
            }

            br.close();
            conexao.disconnect();

            return alunos;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public String inclui(String url, Aluno a) throws MalformedURLException, IOException
    {
        URL objURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection)objURL.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.connect();
        aula1.unicamp.br.crud_alunos.Cliente rest = new aula1.unicamp.br.crud_alunos.Cliente();

        try
        {
            String saida = new Gson().toJson(a);

            OutputStream os = con.getOutputStream();
            os.write(saida.getBytes());
            os.flush();

            int responseCode = con.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;

            while((inputLine = br.readLine())!=null)
                response.append(inputLine);

            os.close();
            br.close();
            con.disconnect();
            return response.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public String altera(String url, Aluno a) throws MalformedURLException, IOException
    {
        URL objURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection)objURL.openConnection();

        con.setDoOutput(true);

        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");

        aula1.unicamp.br.crud_alunos.Cliente rest = new aula1.unicamp.br.crud_alunos.Cliente();

        //Formata em json o item da lista a ser inserido com POST
        JSONObject jsonObj = new JSONObject();
        try
        {
            String saida = new Gson().toJson(a);

            //Pega a conexão aberta em con (getOutputStream()) e faz OutputStream, ouseja, faz o fluxo de dados do cliente para o servidor
            OutputStream os = con.getOutputStream();

            //Escreve o output tranformado em Bytes
            os.write(saida.getBytes());

            int responseCode = con.getResponseCode();
            //Se retornar 200 significa que deu certo

            //Armazena o retorno do método POST do servidor
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;

            while((inputLine = br.readLine())!=null)
                response.append(inputLine);

            os.close();
            br.close();
            con.disconnect();
            return response.toString();
        }
        catch(Exception e)
        {}

        return null;
    }

    public String exclui(String url) throws MalformedURLException, IOException
    {
        URL objURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection)objURL.openConnection();
        con.setDoInput(true);
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.connect();

        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuilder response = new StringBuilder();

            while((inputLine = br.readLine()) != null)
            {
                response.append(inputLine);
            }

            br.close();
            con.disconnect();
            return response.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
