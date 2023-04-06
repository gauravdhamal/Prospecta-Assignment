package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.NoRecordFoundException;
import com.example.demo.models.DisplayDTO;
import com.example.demo.models.Entry;
import com.example.demo.services.EntryService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@RequestMapping("entries")
@OpenAPIDefinition(info = @Info(title = "Open API Category Manager", version = "1.0"))
public class EntryController {

	@Autowired
	private EntryService entryService;

	@GetMapping("/{category}")
	public ResponseEntity<List<DisplayDTO>> getAllEntries(@PathVariable("category") String category)
			throws NoRecordFoundException {
		List<DisplayDTO> displayDTOs = entryService.getTitleAndDescription(category);
		return new ResponseEntity<List<DisplayDTO>>(displayDTOs, HttpStatus.ACCEPTED);
	}

	@PostMapping("/create")
	public ResponseEntity<String> saveEntry(@RequestBody Entry entry) {
		String result = entryService.saveEntry(entry);
		return new ResponseEntity<String>(result, HttpStatus.CREATED);
	}

}
