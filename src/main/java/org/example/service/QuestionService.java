package org.example.service;

import org.example.dto.QuestionDTO;
import org.example.dto.QuestionCreateDTO;
import org.example.dto.QuestionShortDTO;
import org.example.dto.QuestionsByTopicDTO;
import org.example.mapper.QuestionMapper;
import org.example.model.Question;
import org.example.model.Topic;
import org.example.repository.QuestionRepository;
import org.example.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TopicRepository topicRepository;
    private final QuestionMapper questionMapper;
    private final Random random = new Random();

    public QuestionService(QuestionRepository questionRepository,
                           TopicRepository topicRepository,
                           QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.topicRepository = topicRepository;
        this.questionMapper = questionMapper;
    }

    public QuestionDTO createQuestion(QuestionCreateDTO dto) {
        // Перевірка, чи існує Topic
        topicRepository.findById(dto.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        Question question = questionMapper.toEntity(dto);
        return questionMapper.toDTO(questionRepository.save(question));
    }

    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAll()
                .stream()
                .map(questionMapper::toDTO)
                .toList();
    }

    public Optional<QuestionDTO> getQuestionById(Long id) {
        return questionRepository.findById(id).map(questionMapper::toDTO);
    }

    public QuestionDTO updateQuestion(Long id, QuestionCreateDTO dto) {
        Topic topic = topicRepository.findById(dto.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        return questionRepository.findById(id)
                .map(existing -> {
                    existing.setQuestionText(dto.getQuestionText());
                    existing.setAnswer(dto.getAnswer());
                    existing.setTopic(topic);
                    return questionMapper.toDTO(questionRepository.save(existing));
                })
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public List<QuestionsByTopicDTO> getQuestionsGroupedByTopic() {
        List<Question> questions = questionRepository.findAll();

        Map<String, List<QuestionShortDTO>> grouped = questions.stream()
                .collect(Collectors.groupingBy(
                        q -> q.getTopic().getName(),
                        Collectors.mapping(
                                q -> new QuestionShortDTO(q.getId(), q.getQuestionText()),
                                Collectors.toList()
                        )
                ));

        return grouped.entrySet().stream()
                .map(e -> new QuestionsByTopicDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    public List<QuestionDTO> getQuestionsByTopicId(Long topicId) {
        topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        return questionRepository.findByTopicId(topicId).stream()
                .map(questionMapper::toDTO)
                .toList();
    }


    public Optional<QuestionDTO> getRandomQuestionByTopicId(Long topicId) {
        return questionRepository.findRandomByTopicId(topicId)
                .map(questionMapper::toDTO);
    }

}