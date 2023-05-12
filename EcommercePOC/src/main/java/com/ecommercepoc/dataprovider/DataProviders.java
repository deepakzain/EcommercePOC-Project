/**
 * 
 */
package com.ecommercepoc.dataprovider;

import org.testng.annotations.DataProvider;

import com.ecommercepoc.utility.NewExcelLibrary;

/**
 * @author deepak.j
 *
 */
public class DataProviders {
	NewExcelLibrary obj=new NewExcelLibrary();
	
	@DataProvider(name="credentials")
	public Object[][] getCredentials(){
		int rows=obj.getRowCount("Credentials");
		int columns=obj.getColumnCount("Credentials");
		int actRow=rows-1;
		
		Object[][] data=new Object[actRow][columns];
		
		for(int i=0;i<actRow;i++) {
			for(int j=0;j<columns;j++) {
				data[i][j]=obj.getCellData("Credentials", j, i+2);
			}
		}
		return data;
	}
}
