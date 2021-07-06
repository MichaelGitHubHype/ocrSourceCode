package model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TextParser {
	private Map<String, String> langMappings;

	public TextParser() {
		langMappings = new HashMap<>();
		langMappings.put("Russian", "rus");
		langMappings.put("English", "eng");
		langMappings.put("French", "fra");
		langMappings.put("Arabic", "ara");
		langMappings.put("Persian", "fas");
		langMappings.put("Urdu", "urd");
		langMappings.put("Hindi", "hin");
		langMappings.put("Gujarati", "guj");
		langMappings.put("Tajik", "tgk");

	}

	public String decipher(File file, String selLanguage) {
		Tesseract tesseract = new Tesseract();
		File currentDirectory = new File(new File(".").getAbsolutePath());
		System.out.println(currentDirectory.getAbsolutePath() + "/src/tessdata");
		tesseract.setDatapath(currentDirectory.getAbsolutePath() + "/src/tessdata");
		System.out.println();
		tesseract.setLanguage(langMappings.get(selLanguage));

		String fullText;
		try {
			fullText = tesseract.doOCR(file);
//			fullText = tesseract.doOCR(new File(inputFilePath));
			return fullText;
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
