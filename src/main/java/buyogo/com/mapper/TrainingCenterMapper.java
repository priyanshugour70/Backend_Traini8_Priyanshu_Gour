package buyogo.com.mapper;

import buyogo.com.dto.TrainingCenterCreateDTO;
import buyogo.com.dto.TrainingCenterDTO;
import buyogo.com.dto.TrainingCenterUpdateDTO;
import buyogo.com.entity.TrainingCenter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrainingCenterMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "centerCode", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "active", constant = "true")
    TrainingCenter toEntity(TrainingCenterCreateDTO dto);

    TrainingCenterDTO toDto(TrainingCenter entity);

    List<TrainingCenterDTO> toDtoList(List<TrainingCenter> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "centerCode", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "active", ignore = true)
    void updateEntityFromDto(TrainingCenterUpdateDTO dto, @MappingTarget TrainingCenter entity);
}
