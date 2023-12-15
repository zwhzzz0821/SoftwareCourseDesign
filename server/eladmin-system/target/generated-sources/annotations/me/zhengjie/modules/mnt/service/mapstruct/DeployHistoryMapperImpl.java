package me.zhengjie.modules.mnt.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.modules.mnt.domain.DeployHistory;
import me.zhengjie.modules.mnt.service.dto.DeployHistoryDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-15T14:32:13+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class DeployHistoryMapperImpl implements DeployHistoryMapper {

    @Override
    public DeployHistory toEntity(DeployHistoryDto dto) {
        if ( dto == null ) {
            return null;
        }

        DeployHistory deployHistory = new DeployHistory();

        deployHistory.setId( dto.getId() );
        deployHistory.setAppName( dto.getAppName() );
        deployHistory.setIp( dto.getIp() );
        deployHistory.setDeployDate( dto.getDeployDate() );
        deployHistory.setDeployUser( dto.getDeployUser() );
        deployHistory.setDeployId( dto.getDeployId() );

        return deployHistory;
    }

    @Override
    public DeployHistoryDto toDto(DeployHistory entity) {
        if ( entity == null ) {
            return null;
        }

        DeployHistoryDto deployHistoryDto = new DeployHistoryDto();

        deployHistoryDto.setId( entity.getId() );
        deployHistoryDto.setAppName( entity.getAppName() );
        deployHistoryDto.setIp( entity.getIp() );
        deployHistoryDto.setDeployDate( entity.getDeployDate() );
        deployHistoryDto.setDeployUser( entity.getDeployUser() );
        deployHistoryDto.setDeployId( entity.getDeployId() );

        return deployHistoryDto;
    }

    @Override
    public List<DeployHistory> toEntity(List<DeployHistoryDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DeployHistory> list = new ArrayList<DeployHistory>( dtoList.size() );
        for ( DeployHistoryDto deployHistoryDto : dtoList ) {
            list.add( toEntity( deployHistoryDto ) );
        }

        return list;
    }

    @Override
    public List<DeployHistoryDto> toDto(List<DeployHistory> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DeployHistoryDto> list = new ArrayList<DeployHistoryDto>( entityList.size() );
        for ( DeployHistory deployHistory : entityList ) {
            list.add( toDto( deployHistory ) );
        }

        return list;
    }
}
