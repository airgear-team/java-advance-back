package org.example.mapper;

import org.example.dto.TopicDTO;
import org.example.dto.TopicCreateDTO;
import org.example.model.Topic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    TopicDTO toDTO(Topic topic);
    Topic toEntity(TopicCreateDTO dto);
}
