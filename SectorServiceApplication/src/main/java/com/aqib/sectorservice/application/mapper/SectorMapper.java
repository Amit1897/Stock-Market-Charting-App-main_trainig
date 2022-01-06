package com.aqib.sectorservice.application.mapper;

import java.util.Arrays;
import java.util.List;

import com.aqib.sectorservice.application.model.Sector;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.aqib.sectorservice.application.dto.SectorDto;

@Component
public class SectorMapper 
{
	public SectorDto toSectorDto(Sector sector)
	{
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		SectorDto sectorDto = mapper.map(sector, SectorDto.class);
		return sectorDto;
	}
	
	public Sector toSector(SectorDto sectorDto)
	{
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Sector sector = mapper.map(sectorDto, Sector.class);
		return sector;
	}
	
	public List<SectorDto> toSectorDtos(List<Sector> sectors)
	{
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<SectorDto> sectorDtos = Arrays.asList(mapper.map(sectors, SectorDto[].class));
		return sectorDtos;
	}
}
