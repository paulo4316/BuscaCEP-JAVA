package org.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ConsultaCEP {
    // Método para consultar o CEP
    public static void consultarCEP(String cep) {
        String urlString = "https://viacep.com.br/ws/" + cep + "/json/";

        try {
            // Cria a URL para a solicitação HTTP
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configura o método da requisição HTTP
            connection.setRequestMethod("GET");

            // Verifica se a resposta da API foi bem-sucedida (código 200)
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();

                // Lê a resposta da API
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                // Fecha as conexões
                in.close();
                connection.disconnect();

                // Converte a resposta para JSON e exibe as informações
                JSONObject json = new JSONObject(content.toString());
                System.out.println("CEP: " + json.getString("cep"));
                System.out.println("Logradouro: " + json.getString("logradouro"));
                System.out.println("Complemento: " + json.getString("complemento"));
                System.out.println("Bairro: " + json.getString("bairro"));
                System.out.println("Localidade: " + json.getString("localidade"));
                System.out.println("UF: " + json.getString("uf"));
                System.out.println("IBGE: " + json.getString("ibge"));
                System.out.println("GIA: " + json.getString("gia"));
                System.out.println("DDD: " + json.getString("ddd"));
                System.out.println("SIAFI: " + json.getString("siafi"));
            } else {
                System.out.println("Erro na consulta do CEP. Código de resposta: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita que o usuário insira o CEP
        System.out.print("Digite o CEP: ");
        String cep = scanner.nextLine();

        // Consulta o CEP inserido pelo usuário
        consultarCEP(cep);

        // Fecha o scanner
        scanner.close();
    }
}