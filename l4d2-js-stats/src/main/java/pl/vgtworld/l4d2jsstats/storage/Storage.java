package pl.vgtworld.l4d2jsstats.storage;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class Storage {
	
	private static final String STORAGE_ROOT_FOLDER_PROPERTY = "pl.vgtworld.l4d2jsstats.uploads.rootfolder";
	
	private static final String MATCH_SCREENSHOT_SUBFOLDER = "match-screenshots";
	
	private File matchScreenshotsDirectory;
	
	@PostConstruct
	private void init() {
		String rootPath = System.getProperty(STORAGE_ROOT_FOLDER_PROPERTY);
		if (rootPath == null) {
			throw new IllegalStateException("Missing root folder property: " + STORAGE_ROOT_FOLDER_PROPERTY);
		}
		File rootFolder = new File(rootPath);
		if (!rootFolder.exists() || !rootFolder.isDirectory()) {
			throw new IllegalStateException("Root folder doesn't exist.");
		}
		setupMatchScreenshotsDirectory(rootFolder);
	}

	private void setupMatchScreenshotsDirectory(File rootFolder) {
		matchScreenshotsDirectory = new File(rootFolder, MATCH_SCREENSHOT_SUBFOLDER);
		if (!matchScreenshotsDirectory.exists()) {
			matchScreenshotsDirectory.mkdir();
		}
		if (!matchScreenshotsDirectory.exists()) {
			String message = String.format(
				"Subfolder %s doesn't exist and can't create it.",
				MATCH_SCREENSHOT_SUBFOLDER
				);
			throw new IllegalStateException(message);
		}
	}
	
}
