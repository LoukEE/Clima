package weatherapp;
//Back-end que vai puxar os dados do clima direto da API

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WeatherApp {
    //esté metodo vai baixar os dados do clima no formato JSON 
    public static JSONObject getWeatherData(String locationName){
        JSONArray locationData = getLocationData(locationName);
        
        JSONObject location = (JSONObject) locationData.get(0);
        double latitude =  (double) location.get("latitude");
        double longitude = (double) location.get("longitude");
        //url da API do clima;
        String urlString = "https://api.open-meteo.com/v1/forecast?latitude=" +latitude+ "&longitude=" +longitude+ "&hourly=temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m&timezone=America%2FSao_Paulo"; 

        //chamando a API acima 
        try {
            HttpURLConnection conn = fetchApiResponse(urlString);
            
            if(conn.getResponseCode() != 200){
                System.out.println("Erro: Não foi possivel conectar-se a API");
                return null;
            }else{
                StringBuilder resultJson = new StringBuilder(); 
                Scanner scanner = new Scanner(conn.getInputStream());
                while(scanner.hasNext()){
                    resultJson.append(scanner.nextLine());
                }

                scanner.close();
                conn.disconnect();

                JSONParser parser = new JSONParser(); 
                JSONObject resulJsonObject = (JSONObject ) parser.parse(String.valueOf(resultJson));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Chamando a API e pegando as coordenadas da localizaçao dada
    public static JSONArray getLocationData(String locationName){
        //troca todos os espaços em branco pelo simbolo +, para que a API reconheca
        //Sao Paulo == Sao+Paulo
        locationName = locationName.replaceAll(" ", "+");
        //construindo url da API de localização
        //trocando o nome recebido na URL pelo nome da variavel de localização
        //https://geocoding-api.open-meteo.com/v1/search?name=Brazil&count=10&language=en&format=json
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name="+locationName+"&count=10&language=en&format=json";
        try{
    
            //conexão HTTP com a API  estabelecida com a URL fornecida e a resposta é armazenada no objeto conn
            HttpURLConnection conn = fetchApiResponse(urlString);

            //checa o status de responsta
            //HTTP Status: 200 -> Conexão bem sucedida
            if(conn.getResponseCode() != 200){
                System.out.println("Erro: Não foi possivel estabelecer a conexão com a API");
                return null;
            }else{
                //guarda os resultados da API em formato de StringBuilder
                //StringBuilder: objeto string que pode ser modificado a qualquer tempo sem necessitar criar outro objeto
                StringBuilder resultJson = new StringBuilder();
                //Ler a resposta da API
                Scanner scanner = new Scanner(conn.getInputStream());
                //Este loop while lê cada linha da resposta da API e a guarda no StringBuilder
                while(scanner.hasNext()){
                    //cada linha da resposta da API é adicionado no StringBuilder resultJson
                    resultJson.append(scanner.nextLine());
                }

                scanner.close();
                conn.disconnect();

                //Leitor de String JSON
                JSONParser parser = new JSONParser();
                // ler o JSON e transforma o resultado JSON em um Objeto JSON 
                //JSONObject: estrutura de dados que armazena pares de chave-valor, semelhante a um HashMap em Java
                JSONObject resultJsonObject = (JSONObject) parser.parse(String.valueOf(resultJson));
                //converte o JSON Object em um JSON Array que entra com uma tag JSON "results"
                JSONArray locationData = (JSONArray) resultJsonObject.get("results");
                return locationData;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //Fazendo a conexao com a API
    private static HttpURLConnection fetchApiResponse(String urlString){
        try{
            //convertendo uri para url
            URI uri = new URI(urlString);
            URL url = uri.toURL();
            //Convertendo o objeto URL para HttpURLConnection,
            //HttpURLConnection é uma classe em Java que, entre outras coisas, pode ser usada para enviar solicitações HTTP GET e POST.
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //colocando metodo de requisicao GET
            conn.setRequestMethod("GET");
            //O método connect() é chamado no objeto HttpURLConnection. Isso estabelece a conexão de rede real.
            conn.connect();
            return conn;
            //Operador OR Bitwise retorna binario(1 caso qualquer uma das condiçoes der 1) e nao booleano.
        }catch(IOException | URISyntaxException e){

            e.printStackTrace();
        }
        return null; //caso nao o try nao seja true
    }
}
