package com.example.demo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;

import com.example.demo.vo.BojDto;
import com.example.demo.vo.BojVo;

public class DtoUtil {
	
	
	public static List<BojDto> makeDtoList(List<BojVo> list){
		
			List<BojDto> result = new ArrayList<BojDto>();
			
			for(BojVo vo: list) {
//				BojProbType 
				
				
			}
		
		
			return result;
		}
	
	public static JSONObject readJsonFile(String fullPath) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		System.out.println("in ReadJsonFile");
//		String selectTier = tier.toLowerCase();
		ClassPathResource resource = new ClassPathResource(fullPath);
		System.out.println(resource.getURI());
		 Path path = Paths.get(resource.getURI());
		 
//		String source = "/home/chlgprms/crawling/JsonAPI/build/resources/main/python/tier_" + selectTier + ".json";
		File file = new File(path.toUri());
		return (JSONObject) parser.parse(new FileReader(file));
	}
	
	public static JSONArray objectToArr(JSONObject object) throws ParseException {
		JSONParser parser = new JSONParser();
		return (JSONArray)parser.parse(object.toJSONString());
	}
	
	public static JSONArray getJsonArray(String fullPath) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
//		JSONObject object = DtoUtil.readJsonFile("/python/rearrange2.json");
		JSONObject object = DtoUtil.readJsonFile("/home/testgprms2/JsonAPI/src/main/resources/python/rearrange2.json");
		JSONObject tmp = (JSONObject)parser.parse(object.get("data").toString());
		JSONArray arr = (JSONArray)parser.parse(tmp.get("0").toString());
		return arr;
	}
}
