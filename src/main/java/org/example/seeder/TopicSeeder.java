package org.example.seeder;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.example.seeder.SeedConstants.SEED_URL;

public class TopicSeeder {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        // LinkedHashMap щоб зберегти порядок
        Map<Long, String> topics = new LinkedHashMap<>();
        topics.put(1L, "Core-1");
        topics.put(2L, "Core-2");
        topics.put(3L, "SQL");
        topics.put(4L, "Hibernate");
        topics.put(5L, "Spring");
        topics.put(6L, "Багатопотоковість");
        topics.put(7L, "Шаблони проектування");
        topics.put(8L, "Алгоритми");

        for (Map.Entry<Long, String> entry : topics.entrySet()) {
            Long id = entry.getKey();
            String name = entry.getValue();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Якщо сервер підтримує id у DTO
            String body = String.format("{\"id\": %d, \"name\": \"%s\"}", id, name);
            HttpEntity<String> request = new HttpEntity<>(body, headers);

            try {
                ResponseEntity<String> response = restTemplate.postForEntity(SEED_URL, request, String.class);
                System.out.println("Відправлено: " + name + " | id=" + id + " | Статус: " + response.getStatusCode());
            } catch (Exception e) {
                System.err.println("Помилка при відправці " + name + ": " + e.getMessage());
            }
        }
    }
}
