package org.example.service;

import org.example.dto.TopicDTO;
import org.example.dto.TopicCreateDTO;
import org.example.mapper.TopicMapper;
import org.example.model.Topic;
import org.example.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public TopicService(TopicRepository topicRepository, TopicMapper topicMapper) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
    }

    public TopicDTO createTopic(TopicCreateDTO dto) {
        Topic topic;
        if (dto.getId() != null) {
            // Якщо передано id, встановлюємо вручну
            topic = new Topic(dto.getId(), dto.getName());
        } else {
            topic = topicMapper.toEntity(dto);
        }
        return topicMapper.toDTO(topicRepository.save(topic));
    }

    public List<TopicDTO> getAllTopics() {
        return topicRepository.findAll().stream()
                .map(topicMapper::toDTO)
                .toList();
    }

    public Optional<TopicDTO> getTopicById(Long id) {
        return topicRepository.findById(id).map(topicMapper::toDTO);
    }
}