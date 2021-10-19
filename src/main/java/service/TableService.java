package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.TableDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TableService {

    private static final String TABLE_URL = "http://localhost:8080/table/";

    public void addTable(String command) throws Exception {
        String fields = command.substring(command.indexOf("(") + 1, command.lastIndexOf(")"));
        String firstPart = command.substring(0, command.indexOf('('));
        String databaseName = firstPart.split(" ")[2].split("\\.")[0];
        String tableName = firstPart.split(" ")[2].split("\\.")[1];

        TableDTO tableDTO = new TableDTO(databaseName, tableName, fields);

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonTable = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(tableDTO);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(TABLE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonTable))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }


}
