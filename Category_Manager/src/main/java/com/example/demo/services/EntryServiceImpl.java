package com.example.demo.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.exceptions.NoRecordFoundException;
import com.example.demo.models.APIDataDTO;
import com.example.demo.models.DisplayDTO;
import com.example.demo.models.Entry;
import com.example.demo.repositories.EntryRepository;

@Service
public class EntryServiceImpl implements EntryService {

	@Autowired
	private EntryRepository entryRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<DisplayDTO> getTitleAndDescription(String category) throws NoRecordFoundException {
		APIDataDTO apiDataDTO = restTemplate.getForObject("https://api.publicapis.org/entries", APIDataDTO.class);
		List<Entry> entries = apiDataDTO.getEntries();
		List<DisplayDTO> displayDTOs = entries.stream().filter(entry -> entry.getCategory().equals(category))
				.map(entry -> new DisplayDTO(entry.getApi(), entry.getDescription())).toList();
		if (displayDTOs.isEmpty())
			throw new NoRecordFoundException("No any data found under category : " + category);
		return displayDTOs;
	}

	@Override
	public String saveEntry(Entry entry) {
		List<Entry> oldEntry = entryRepository.findAll();
		if (oldEntry.isEmpty()) {
			APIDataDTO apiDataDTO = restTemplate.getForObject("https://api.publicapis.org/entries", APIDataDTO.class);
			List<Entry> entries = apiDataDTO.getEntries();
			for (Entry entry2 : entries) {
				entry2 = modelMapper.map(entry2, Entry.class);
				entryRepository.save(entry2);
			}
		}
		entryRepository.save(entry);
		return "Entry saved to database.";
	}

}
