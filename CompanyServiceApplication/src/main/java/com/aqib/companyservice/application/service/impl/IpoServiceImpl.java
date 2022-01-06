package com.aqib.companyservice.application.service.impl;

import java.util.List;
import java.util.Optional;

import com.aqib.companyservice.application.service.IpoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqib.companyservice.application.dao.IpoRepository;
import com.aqib.companyservice.application.dto.CompanyDto;
import com.aqib.companyservice.application.dto.IpoDto;
import com.aqib.companyservice.application.mapper.IpoMapper;
import com.aqib.companyservice.application.model.Ipo;
import com.aqib.companyservice.application.service.CompanyService;

@Service
public class IpoServiceImpl implements IpoService
{
	@Autowired
	private IpoRepository ipoRepository;
	
	@Autowired
	private IpoMapper ipoMapper;
	
	@Autowired
	private CompanyService companyService;
	
	@Override
	public List<IpoDto> findAll() {
		List<Ipo> ipos = ipoRepository.findAll();
		return ipoMapper.toIpoDtos(ipos);
	}
	
	@Override
	public IpoDto findById(String id) {
		Optional<Ipo> ipo = ipoRepository.findById(id);
		if(!ipo.isPresent())
			return null;
		return ipoMapper.toIpoDto(ipo.get());
	}
	
	@Override
	public IpoDto save(IpoDto ipoDto) {
		Ipo ipo = ipoMapper.toIpo(ipoDto);
		ipo = ipoRepository.save(ipo);
		ipoDto = ipoMapper.toIpoDto(ipo);
		CompanyDto companyDto = companyService.addIpoToCompany(ipo.getCompanyName(), ipoDto);
		if(companyDto == null)
			return null;
		return ipoDto;
	}
	
	@Override
	public IpoDto update(IpoDto ipoDto) {
		if(findById(ipoDto.getId()) == null)
			return null;
		Ipo ipo = ipoRepository.save(ipoMapper.toIpo(ipoDto));
		return ipoMapper.toIpoDto(ipo);
	}
	
	@Override
	public void deleteById(String id) {
		ipoRepository.deleteById(id);
	}
}
