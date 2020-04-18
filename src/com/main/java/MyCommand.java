package com.main.java;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * This is interface 
 * for all the command run
 * @author: Rugraj
 */
public interface MyCommand {
	
	Map<String,Object> executeCommand(List<String> commandList);
	
	
	

}
