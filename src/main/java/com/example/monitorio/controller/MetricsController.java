package com.example.monitorio.controller;

import com.example.monitorio.entity.Metrics;
import com.example.monitorio.service.MetricsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


//@RestController()
@Controller
@Slf4j
public class MetricsController {
    private MetricsService oMetricsService;
    @Autowired
    public MetricsController(MetricsService oMetricsService) {
        this.oMetricsService = oMetricsService;
    }


    @GetMapping("/start")
    private ResponseEntity<String> getMetricsPorId() {
        try {
            // Create an HTTP client
            HttpClient httpClient = HttpClient.newHttpClient();
            Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
            Type tipoLista = new TypeToken<List<Metrics>>() {}.getType();


            // Prepare the request with headers
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://my.api.mockaroo.com/metrics.json"))
                    .header("X-API-Key",  "3cb9d0e0")
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Check the response status code
            if (response.statusCode() == 200) {
                JSONArray jsonArray = new JSONArray(response.body());
                List<Metrics> lista = gson.fromJson(jsonArray.toString(), tipoLista);
                Integer numero =0;
                System.out.println("==================================================");
                System.out.println("==================== METRICAS ====================");
                System.out.println("==================================================");

                for(Metrics n: lista){
                    System.out.println(" "+ numero  );
                    System.out.println("Timestamp:: " + n.getTimestamp().toString());
                    System.out.println("Cpu_usage:: " + n.getCpu_usage().toString());
                    System.out.println("Memory_usage:: "  +n.getMemory_usage().toString());
                    System.out.println("Response_time:: " + n.getResponse_time().toString());
                    System.out.println("Request_count:: " + n.getRequest_count().toString());
                    System.out.println("==================================================");
                    numero++;
                }
                System.out.println( numero + " Registros Insertados Satisfactoriamente...");
            } else {
                System.err.println("Error: HTTP status code " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return ResponseEntity.ok(  " Registros Insertados Satisfactoriamente...");
        }
    }
}
