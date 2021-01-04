package org.demo.mapper;

import org.demo.dto.NafisRawExampleDto;
import org.demo.model.NafisRawExample;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface NafisRawExampleMapper {

    NafisRawExampleDto toResource(NafisRawExample nafisRawExample);
    NafisRawExample fromResource(NafisRawExampleDto nafisRawExampleDto);

}
