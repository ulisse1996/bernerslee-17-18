package it.uniba.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;

public class Zip {


	@SuppressWarnings("deprecation")
	public String setUsersFile(final String input) {
		String json = new String();
		try {
			ZipFile zip = new ZipFile(input);
			InputStream source = zip.getInputStream(zip.getEntry("users.json"));
			json = IOUtils.toString(source);
			zip.close();
		} catch (IOException e) {
			System.out.println("File not found or invalid !");
		}
		return json;
	}

	@SuppressWarnings("deprecation")
	public String setChannelFile(final String input) {
		String json = new String();
		try {
			ZipFile zip = new ZipFile(input);
			InputStream source = zip.getInputStream(zip.getEntry("channels.json"));
			json = IOUtils.toString(source);
			zip.close();
		} catch (IOException e) {
			System.out.println("File not found or invalid !");
		}
		return json;
	}
	
	public List<String> setConversationsFile(final String input) {
		List<String> conversations = new ArrayList<String>();
		try {
			ZipFile zip = new ZipFile(input);
			Enumeration<? extends ZipEntry> entries = zip.entries();
			while (entries.hasMoreElements()) {
				ZipEntry control = entries.nextElement();
				if (!control.isDirectory()) {
					if (!control.getName().equals("users.json") && !control.getName().equals("channels.json") && !control.getName().equals("integration_logs.json")) {
						conversations.add(new String(control.getName()));
					}
				}
			}
			zip.close();
		} catch (IOException e) {
			System.out.println("File not found or invalid !");
		}
		return conversations;
	}

	public List<String> setConversationsFileChannel(String channel, String path) {
		List<String> conversations = new ArrayList<String>();
		try {
			ZipFile zip = new ZipFile(path);
			Enumeration<? extends ZipEntry> entries = zip.entries();
			while (entries.hasMoreElements()) {
				ZipEntry control = entries.nextElement();
				if (!control.isDirectory()) {
					if (control.getName().contains(channel)) {
						conversations.add(new String(control.getName()));
					}
				}
			}
			zip.close();
		} catch (IOException e) {
			System.out.println("File not found or invalid !");
		}
		return conversations;
	}
	
	@SuppressWarnings("deprecation")
	public String getJsonFromFile(String path,String input) {
		String json = new String();
		try {
			ZipFile zip = new ZipFile(path);
			InputStream source = zip.getInputStream(zip.getEntry(input));
			json = IOUtils.toString(source);
			zip.close();
		} catch (IOException e) {
			System.out.println("File not Found or invalid !");
		}
		return json;
	}
}