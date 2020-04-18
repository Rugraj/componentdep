package com.main.java;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * Class for List command
 * @author Rugraj
 *
 */
public class ListCommand implements MyCommand {

	
	 public Map<String, Object> executeCommand(List<String> commandList) {
		Map<String, Object> resultList = new LinkedHashMap<>();
        Component.getInstalledComponent().forEach(comp -> resultList.put(comp.getComponentName(),""));
        return resultList;
	}
}
