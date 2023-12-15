package me.zhengjie.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.domain.SysLog;
import me.zhengjie.service.dto.SysLogSmallDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-15T14:32:06+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class LogSmallMapperImpl implements LogSmallMapper {

    @Override
    public SysLog toEntity(SysLogSmallDto dto) {
        if ( dto == null ) {
            return null;
        }

        SysLog sysLog = new SysLog();

        sysLog.setDescription( dto.getDescription() );
        sysLog.setRequestIp( dto.getRequestIp() );
        sysLog.setAddress( dto.getAddress() );
        sysLog.setBrowser( dto.getBrowser() );
        sysLog.setTime( dto.getTime() );
        sysLog.setCreateTime( dto.getCreateTime() );

        return sysLog;
    }

    @Override
    public SysLogSmallDto toDto(SysLog entity) {
        if ( entity == null ) {
            return null;
        }

        SysLogSmallDto sysLogSmallDto = new SysLogSmallDto();

        sysLogSmallDto.setDescription( entity.getDescription() );
        sysLogSmallDto.setRequestIp( entity.getRequestIp() );
        sysLogSmallDto.setTime( entity.getTime() );
        sysLogSmallDto.setAddress( entity.getAddress() );
        sysLogSmallDto.setBrowser( entity.getBrowser() );
        sysLogSmallDto.setCreateTime( entity.getCreateTime() );

        return sysLogSmallDto;
    }

    @Override
    public List<SysLog> toEntity(List<SysLogSmallDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SysLog> list = new ArrayList<SysLog>( dtoList.size() );
        for ( SysLogSmallDto sysLogSmallDto : dtoList ) {
            list.add( toEntity( sysLogSmallDto ) );
        }

        return list;
    }

    @Override
    public List<SysLogSmallDto> toDto(List<SysLog> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SysLogSmallDto> list = new ArrayList<SysLogSmallDto>( entityList.size() );
        for ( SysLog sysLog : entityList ) {
            list.add( toDto( sysLog ) );
        }

        return list;
    }
}
