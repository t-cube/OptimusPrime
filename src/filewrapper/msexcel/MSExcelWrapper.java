/**
 * Package contains wrapper for various file formats. In this case MS Excel.
 */
package filewrapper.msexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet; 

import filewrapper.FileWrapper;
import util.DebugSystem;

/**
 * Simple wrapper for the excel file format.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-09
 */
public class MSExcelWrapper implements FileWrapper<Integer, String> {
	FileInputStream file;
	Workbook workbook;
	Sheet sheet;
	Iterator<Row> rowIterator;
	Row row;

	
//- Constructor --------------------------------------------------------------//
	
	/**
	 * Simplest constructor. Opening the excel file by it's path, selecting the
	 * first sheet and first row.
	 * @param filePath File path to the MS Excel file,  which should be opened.
	 */
	public MSExcelWrapper(String filePath){
		createInstance(filePath);
		selectSheet(0);
	}
	
	/**
	 * Constructor opening the excel file by it's path, selecting the sheet by
	 * it's given name.
	 * @param filePath File path to the MS Excel file,  which should be opened.
	 * @param sheetName Name of the sheet, which should be selected.
	 */
	public MSExcelWrapper(String filePath, String sheetName){
		createInstance(filePath);
		selectSheet(sheetName);
	}
	
	/**
	 * Constructor opening the excel file by it's path, selecting the sheet by
	 * it's given index.
	 * @param filePath File path to the MS Excel file,  which should be opened.
	 * @param sheetIndex Index of the sheet, which should be selected.
	 */
	public MSExcelWrapper(String filePath, int sheetIndex){
		createInstance(filePath);
		selectSheet(sheetIndex);
	}
	
	/**
	 * Function to fill this object instance by creating the file stream and 
	 * opening the workbook.
	 * @param filePath File path to the MS Excel file, which should be opened.
	 */
	private void createInstance(String filePath){
		try {
			this.file = new FileInputStream(new File(filePath));
			this.workbook = WorkbookFactory.create(this.file);
		} catch (FileNotFoundException e) {
			DebugSystem.logError(e);
		} catch (IOException e) {
			DebugSystem.logError(e);
		} catch (InvalidFormatException e) {
			DebugSystem.logError(e);
		}
	}
	
	
//- Private Methods ----------------------------------------------------------//
	
	/**
	 * Sets the row object, by refreshing the row iterator and using it's next
	 * row element.  
	 */
	private void setRow(){
		this.row = null;
		
		if (this.sheet != null){
			this.rowIterator = this.sheet.rowIterator();
			
			if (this.rowIterator.hasNext()){
				this.row = this.rowIterator.next();
			}
		}		
	}
	
	/**
	 * @param row Row number of the cell returned.
	 * @param column Column number of the cell returned.
	 * @return Cell object at the given row and column (0-based).
	 */
	private Cell getCell(int row, int column){
		Row r = this.sheet.getRow(row);		
		return r.getCell(column);
	}
	
	/**
	 * @param row Row number of the cell returned.
	 * @param column Column number of the cell returned.
	 * @return String representation of the value inside the selected cell.
	 */
	private String getCellValue(int row, int column){
		return this.getCell(row, column).getStringCellValue();
	}
	
	
//- Public Methods -----------------------------------------------------------//
	
	@Override
	public boolean hasNext() {
		return this.rowIterator.hasNext();
	}


	@Override
	public String getData(Integer key) {
		// TODO Auto-generated method stub
		return this.getCellValue(this.row.getRowNum(), 
								 key.intValue());
	}

	@Override
	public void next() {
		if (this.hasNext()){
			this.row = this.rowIterator.next();
		}else{
			this.row = null;
		}			
	}
	
		
//- Getters ------------------------------------------------------------------//

	/**
	 * @return Returns the last row number of the active sheet.
	 */
	public int getLastRow(){
		return this.sheet.getLastRowNum();
	}
	
	/**
	 * @return The number of available sheets in the workbook.
	 */
	public int getSheetCount(){
		return this.workbook.getNumberOfSheets();
	}
	
	/**
	 * @return Name of the current selected sheet.
	 */
	public String getActiveSheetName(){
		return this.sheet.getSheetName();
	}


//- Setters ------------------------------------------------------------------//
	
	/**
	 * Select a sheet as the active sheet by using it's index.
	 * @param i Index of the sheet to select.
	 */
	public void selectSheet(int i){
		this.sheet = this.workbook.getSheetAt(i);
		setRow();
	}
	
	/**
	 * Select a sheet as the active sheet by it's sheet name.
	 * @param sheetName Name of the sheet to select.
	 */
	public void selectSheet(String sheetName){
		this.sheet = this.workbook.getSheet(sheetName);
		setRow();
	}
	
}
