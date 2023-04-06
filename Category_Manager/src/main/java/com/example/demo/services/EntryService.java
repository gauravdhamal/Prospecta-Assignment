package com.example.demo.services;

import java.util.List;

import com.example.demo.exceptions.NoRecordFoundException;
import com.example.demo.models.DisplayDTO;
import com.example.demo.models.Entry;

public interface EntryService {

	public List<DisplayDTO> getTitleAndDescription(String category) throws NoRecordFoundException;

	public String saveEntry(Entry entry);

}
