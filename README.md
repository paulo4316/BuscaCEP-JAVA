# Consulta de CEP

Este é um projeto Java simples para consultar informações de endereço a partir de um CEP (Código de Endereçamento Postal) usando a API ViaCEP. O projeto utiliza Maven para gerenciar dependências.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação utilizada para desenvolver a aplicação.
- **Maven**: Ferramenta de automação de compilação e gerenciamento de dependências.
- **API ViaCEP**: API pública que fornece informações de endereço a partir de um CEP.
- **Biblioteca `org.json`**: Para manipulação de dados JSON.

## Pré-requisitos

- JDK 8 ou superior
- Maven instalado
- IntelliJ IDEA ou outra IDE Java

## Configuração do Projeto

### Passo a Passo

1. **Clone o repositório**:
    ```sh
    git clone https://github.com/paulo4316/BuscaCEP-JAVA.git
    ```

2. **Abra o projeto no IntelliJ IDEA**:
   - Vá em `File` > `Open` e selecione o diretório do projeto.

3. **Adicionar dependência `org.json`**:
   - Abra o arquivo `pom.xml` e adicione a seguinte dependência dentro da tag `<dependencies>`:
     ```xml
     <dependencies>
         <dependency>
             <groupId>org.json</groupId>
             <artifactId>json</artifactId>
             <version>20210307</version>
         </dependency>
     </dependencies>
     ```

4. **Atualize o projeto Maven**:
   - Clique no ícone de sincronização no canto superior direito do editor ou clique com o botão direito no arquivo `pom.xml` e selecione `Maven` > `Reload Project`.

## Como Executar

1. **Execute a classe principal**:
   - Abra a classe `ConsultaCEP` em `src/main/java/com/exemplo/ConsultaCEP.java`.

2. **Execute o método `main`**:
   - Execute a classe `ConsultaCEP` e insira o CEP quando solicitado.

## Código de Exemplo

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class ConsultaCEP {

    public static void consultarCEP(String cep) {
        String urlString = "https://viacep.com.br/ws/" + cep + "/json/";
        
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                
                in.close();
                connection.disconnect();
                
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
        System.out.print("Digite o CEP: ");
        String cep = scanner.nextLine();
        consultarCEP(cep);
        scanner.close();
    }
}
