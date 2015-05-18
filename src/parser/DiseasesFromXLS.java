package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.google.gson.Gson;

import constants.DiseasesXLSConstants;
import entity.Disease;
import entity.DiseaseCounty;
import entity.DiseaseYear;

public class DiseasesFromXLS {
	
	public DiseasesFromXLS() {
		
	}
	
	@SuppressWarnings("resource")
	public List<Disease> parseXlsFile(String filePath) {
		List<Disease> diseases = new ArrayList<Disease>();
		try {
			FileInputStream file = new FileInputStream(new File(filePath));
			
			//Get the workbook instance for XLS file 
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			 
			//Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			//Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator = sheet.iterator();
			Integer rowIndex = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (rowIndex == DiseasesXLSConstants.ROW_HEADER_START) {
					Iterator<Cell> cellIterator = row.cellIterator();
					Integer cellIndex = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if (cellIndex >= DiseasesXLSConstants.CELL_HEADER_START && cellIndex < DiseasesXLSConstants.CELL_HEADER_END) {
							if (cellIndex % 2 == 0) {
								Disease disease = new Disease();
								disease.setName(cell.getStringCellValue().trim());
								disease.setType("Boli infectioase");
								List<DiseaseYear> diseasesByYear = new ArrayList<DiseaseYear>();
								for (int year = DiseasesXLSConstants.YEAR_START; year <= DiseasesXLSConstants.YEAR_END; year++) {
									DiseaseYear diseaseByYear = new DiseaseYear();
									diseaseByYear.setYear(year);
									diseasesByYear.add(diseaseByYear);
								}
								disease.setDiseaseByYear(diseasesByYear);
								diseases.add(disease);
							}
						}
						cellIndex++;
					}
					
				}
				
				if (rowIndex > DiseasesXLSConstants.ROW_START) {					
					String county = "";
					
					Iterator<Cell> cellIterator = row.cellIterator();
					Integer cellIndex = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						
						if (cellIndex == DiseasesXLSConstants.CELL_COUNTY) {
							county = WordUtils.capitalize(cell.getStringCellValue().toLowerCase(), ' ');
						}
						
						if (cellIndex >= DiseasesXLSConstants.CELL_START && cellIndex < DiseasesXLSConstants.CELL_END) {
							if (cellIndex < DiseasesXLSConstants.CELL_HALF_END) {
								int cellIdx = (cellIndex - DiseasesXLSConstants.CELL_START) / 2;
								DiseaseCounty diseaseByCounty = new DiseaseCounty();
								diseaseByCounty.setCounty(county);
								diseaseByCounty.setNoOfCases((int) cell.getNumericCellValue());
								diseases.get(cellIdx).getDiseaseByYear().get(cellIndex % 2).getDiseaseByCounty().add(diseaseByCounty);
							} else {
								int cellIdx = (cellIndex - DiseasesXLSConstants.CELL_HALF_END) / 2;
								int rowIdx = rowIndex - DiseasesXLSConstants.ROW_START - 1;
								Double ratio = new Double(String.format("%.2f", cell.getNumericCellValue()));
								diseases.get(cellIdx).getDiseaseByYear().get(cellIndex % 2).getDiseaseByCounty().get(rowIdx).setRatio(ratio);
							}
						}
						
						cellIndex++;
					}
				
				}
				
				rowIndex++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return diseases;
	}
	
	public void writeJSONFile(String fileName, List<Disease> diseases) {
		FileWriter fWriter;
		try {
			fWriter = new FileWriter(fileName);
			fWriter.write(new Gson().toJson(diseases));
			fWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
