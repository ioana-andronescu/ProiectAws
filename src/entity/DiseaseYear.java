package entity;

import java.util.ArrayList;
import java.util.List;

public class DiseaseYear {
	
	private Integer year;
	private List<DiseaseCounty> diseaseByCounty;
	
	public DiseaseYear() {
		diseaseByCounty = new ArrayList<DiseaseCounty>();
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<DiseaseCounty> getDiseaseByCounty() {
		return diseaseByCounty;
	}

	public void setDiseaseByCounty(List<DiseaseCounty> diseaseByCounty) {
		this.diseaseByCounty = diseaseByCounty;
	}

	@Override
	public String toString() {
		return "DiseaseYear [year=" + year + ", diseaseByCounty="
				+ diseaseByCounty + "]";
	}

}
