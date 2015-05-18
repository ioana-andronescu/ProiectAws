package entity;

public class DiseaseCounty {

	private String		county;
	private Integer		noOfCases;
	private Double 		ratio;
	
	public DiseaseCounty() {
		this.noOfCases	= 0;
		this.ratio		= 0.0;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Integer getNoOfCases() {
		return noOfCases;
	}

	public void setNoOfCases(Integer noOfCases) {
		this.noOfCases = noOfCases;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	@Override
	public String toString() {
		return "DiseaseCounty [county=" + county + ", noOfCases=" + noOfCases
				+ ", ratio=" + ratio + "]";
	}

}
