package entity;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.vocabulary.VCARD;

import constants.DiseasesRDFConstants;

public class DiseaseRDF {
	
	private Disease disease;
	private String prefix;
	
	public DiseaseRDF(Disease disease, String prefix) {
		this.disease	= disease;
		this.prefix		= prefix;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public Model transform() {
		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("opendata", prefix);
		model.setNsPrefix("geo","http://www.w3.org/2003/01/geo/wgs84_pos#");
		model.setNsPrefix("vcard", "http://www.w3.org/2001/vcard-rdf/3.0#");
		
		Property tipProperty = model.createProperty(DiseasesRDFConstants.PROPERTY_ROOT + "tip_boala");
		Resource diseaseDbpedia = model.createResource("http://dbpedia.org/ontology/Disease");
		
		Resource resDisease = model.createResource(prefix + disease.getName())
				.addProperty(tipProperty, disease.getType())
				.addProperty(RDF.type, diseaseDbpedia)
				.addProperty(RDFS.label, disease.getName());
		
		return model;
	}

}
