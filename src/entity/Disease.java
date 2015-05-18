package entity;

import java.util.ArrayList;
import java.util.List;

public class Disease {
	
	private String name;
	private String type;
	private List<DiseaseYear> diseaseByYear;
	
	public Disease() {
		diseaseByYear = new ArrayList<DiseaseYear>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<DiseaseYear> getDiseaseByYear() {
		return diseaseByYear;
	}

	public void setDiseaseByYear(List<DiseaseYear> diseaseByYear) {
		this.diseaseByYear = diseaseByYear;
	}

	@Override
	public String toString() {
		return "Disease [name=" + name + ", type=" + type + ", diseaseByYear="
				+ diseaseByYear + "]";
	}
	
}
