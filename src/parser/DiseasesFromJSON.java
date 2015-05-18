package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import entity.Disease;
import entity.DiseaseRDF;

public class DiseasesFromJSON {
	
	private List<Disease> diseases;
	
	public DiseasesFromJSON() {
		this.diseases = new ArrayList<Disease>();
	}
	
	public List<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<Disease> diseases) {
		this.diseases = diseases;
	}

	public void parseJSONFile(String filePath) {
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(filePath));
			diseases = new Gson().fromJson(bReader, new TypeToken<List<Disease>>(){}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void writeRDFFile(String filePath, String prefix) {
		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("opendata", prefix);
		
		for (Disease disease : diseases) {
			DiseaseRDF rdf = new DiseaseRDF(disease, prefix);
			model.add(rdf.transform());
		}
		
		FileOutputStream fStream;
		try {
			fStream = new FileOutputStream(new File(filePath));
			//model.write(fStream,"RDF/XML");
			model.write(fStream,"N3");
		  	fStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
