
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import constants.DiseasesRDFConstants;
import parser.DiseasesFromJSON;
import parser.DiseasesFromXLS;
import entity.Disease;


public class Main {
	
	public static void main (String[] args) {
		DiseasesFromXLS diseasesXls = new DiseasesFromXLS();
		File folder = new File("docs/diseases");
		List<Disease> diseasesAll = new ArrayList<Disease>();
		for (File file : folder.listFiles()) {
			diseasesAll.addAll(diseasesXls.parseXlsFile(file.getPath()));
		}
		diseasesXls.writeJSONFile("docs/diseases.json", diseasesAll);
		
		DiseasesFromJSON diseasesJson = new DiseasesFromJSON();
		diseasesJson.parseJSONFile("docs/diseases.json");
		diseasesJson.writeRDFFile("docs/diseases.n3", DiseasesRDFConstants.RESOURCE_ROOT);
	}

}
