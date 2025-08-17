package org.example.mapper;

import org.example.dto.QuestionDTO;
import org.example.dto.QuestionCreateDTO;
import org.example.model.Question;
import org.example.model.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(source = "topic.name", target = "topicName")
    QuestionDTO toDTO(Question question);

    @Mapping(source = "topicId", target = "topic")
    Question toEntity(QuestionCreateDTO dto);

    default Topic map(Long topicId) {
        if (topicId == null) return null;
        Topic topic = new Topic();
        topic.setId(topicId);
        return topic;
    }
}
