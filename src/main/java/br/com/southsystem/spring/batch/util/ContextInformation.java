package br.com.southsystem.spring.batch.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class ContextInformation {

	
	public static String[] args;

	public static String[] getArgs() {
		return args;
	}

	public static void setArgs(String[] args) {
		ContextInformation.args = args;
	}
	
}
