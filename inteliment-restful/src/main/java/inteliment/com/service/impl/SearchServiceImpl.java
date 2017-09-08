package inteliment.com.service.impl;

import inteliment.com.request.SearchText;
import inteliment.com.response.CountEntity;
import inteliment.com.response.SearchCountsResponse;
import inteliment.com.service.SearchService;
import inteliment.com.utils.Utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * 
 * @author Mitesh Chavda
 * @version 1.0
 *
 */
public class SearchServiceImpl implements SearchService {

    private static String fileName = "para.txt";

    public String countSearchText(String searchRequest)
	    throws JsonMappingException, JsonGenerationException, IOException {

	// Get word list
	List<String> wordList = readParagraphAndSplit(fileName);

	// Generate Map for text vs count
	Map<String, Integer> textCountMap = wordList.stream().collect(
		Collectors.toConcurrentMap(w -> w, w -> 1, Integer::sum));

	// Get request search text
	SearchText searchText = (SearchText) Utility.getInstanceFromJson(
		searchRequest, SearchText.class);
	List<String> searchTextList = searchText.getSearchText();

	// Get count of each text from Map
	List<Map<String, Integer>> counts = new ArrayList<Map<String, Integer>>();
	for (String string : searchTextList) {
	    Map<String, Integer> map = new HashMap<String, Integer>();
	    int textCount = (textCountMap.get(string.toLowerCase()) != null) ? textCountMap
		    .get(string.toLowerCase()) : 0;
	    map.put(string, textCount);
	    counts.add(map);
	}

	// Set data to response entity
	SearchCountsResponse searchCount = new SearchCountsResponse();
	searchCount.setCounts(counts);

	// Generate String from Response
	String response = Utility.getJsonStringFromObject(searchCount);

	return response;
    }

    public String getTopTextCount(int count) throws IOException {

	// Read paragraph and generate word list
	List<String> dataList = readParagraphAndSplit(fileName);

	// Generate word vs count map
	Map<String, Integer> countMap = dataList.stream().collect(
		Collectors.toConcurrentMap(w -> w, w -> 1, Integer::sum));

	// Update order and generate new map with request count
	Map<String, Integer> sortedMap = countMap
		.entrySet()
		.stream()
		.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		.limit(count)
		.collect(
			Collectors.toMap(Map.Entry::getKey,
				Map.Entry::getValue,
				(oldValue, newValue) -> oldValue,
				LinkedHashMap::new));

	// Set entity header for CSV
	CsvMapper csvMapper = new CsvMapper();
	CsvSchema csvSchema = csvMapper.schemaFor(CountEntity.class)
		.withHeader();

	// Generate count entity list
	List<CountEntity> ceList = new ArrayList<CountEntity>();
	for (String text : sortedMap.keySet()) {
	    CountEntity ce = new CountEntity();
	    ce.setCount(sortedMap.get(text.toLowerCase()));
	    ce.setText(text);

	    ceList.add(ce);
	}

	String output = csvMapper.writer(csvSchema).writeValueAsString(ceList);

	return output;
    }

    private List<String> readParagraphAndSplit(String fileName)
	    throws IOException {

	// Read paragraph
	File file = ResourceUtils.getFile("classpath:" + fileName);
	String content = new String(Files.readAllBytes(file.toPath()));

	// Convert paragraph to List and remove special character
	List<String> wordList = Arrays.asList(content.toLowerCase()
		.replace(".", "").replace(",", "").replace("\n", " ")
		.split(" "));

	return wordList;

    }
}
