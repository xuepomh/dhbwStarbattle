package com.starbattle.client.testinterface.exceptions;

public class GUIElementNotFoundException extends Exception {

	public GUIElementNotFoundException(String elementName)
	{
		super("Failed to find GUI Element '"+elementName+"'");
	}
	
}
