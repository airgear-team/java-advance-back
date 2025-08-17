package org.example.seeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.QuestionCreateDTO;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.example.seeder.SeedConstants.SEED_URL;

public class QuestionSeeder {

    public static void main(String[] args) {
        try {
            // 1. Зчитуємо JSON-файл з ресурсів
            ObjectMapper mapper = new ObjectMapper();
            List<QuestionJson> questions = mapper.readValue(
                    new File("src/main/resources/questions.json"),
                    new TypeReference<List<QuestionJson>>() {}
            );

            // 2. Налаштовуємо REST-клієнт
            RestTemplate restTemplate = new RestTemplate();

            for (QuestionJson q : questions) {
                QuestionCreateDTO dto = new QuestionCreateDTO();
                dto.setQuestionText(q.getQuestionText());
                dto.setAnswer(q.getAnswer());
                // Тут можна зіставити topicName з topicId, якщо вже існують теми
                dto.setTopicId(mapTopicNameToId(q.getTopicName()));

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<QuestionCreateDTO> request = new HttpEntity<>(dto, headers);

                try {
                    ResponseEntity<String> response = restTemplate.postForEntity(SEED_URL, request, String.class);
                    System.out.println("Відправлено: " + q.getQuestionText() + " | Статус: " + response.getStatusCode());
                } catch (Exception e) {
                    System.err.println("Помилка при відправці: " + q.getQuestionText() + " | " + e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Простий клас для парсингу JSON
    static class QuestionJson {
        private Long id; // додаємо, щоб Jackson не ругався
        private String questionText;
        private String answer;
        private String topicName;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getQuestionText() { return questionText; }
        public void setQuestionText(String questionText) { this.questionText = questionText; }

        public String getAnswer() { return answer; }
        public void setAnswer(String answer) { this.answer = answer; }

        public String getTopicName() { return topicName; }
        public void setTopicName(String topicName) { this.topicName = topicName; }
    }

    // TODO: реалізувати реальне зіставлення topicName -> topicId
    private static Long mapTopicNameToId(String topicName) {
        switch (topicName) {
            case "Core-1": return 1L;
            case "Core-2": return 2L;
            case "SQL": return 3L;
            case "Hibernate": return 4L;
            case "Spring": return 5L;
            case "Багатопотоковість": return 6L;
            case "Шаблони проектування": return 7L;
            case "Алгоритми": return 8L;
            default: return 0L;
        }
    }
}
