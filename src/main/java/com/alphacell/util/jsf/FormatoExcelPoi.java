package com.alphacell.util.jsf;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Created by luis.cevallos on 27/4/2016.
 */
public class FormatoExcelPoi {

	public static BigDecimal obtenerValor(String strVal) {

		strVal = strVal.replaceAll("\\s+", "");
		strVal = strVal.replaceAll("(?<=\\d),(?=\\d)|\\$", "");

		Locale locale = Locale.getDefault();
		String lang = locale.getDisplayLanguage();
		String country = locale.getDisplayCountry();

		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');
		String pattern = "#,##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);

		// parse the string
		BigDecimal bigDecimal = null;
		try {
			bigDecimal = (BigDecimal) decimalFormat.parse(strVal);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return bigDecimal;

	}

	public static void formatearArchivoExcel(Object document, HashSet<Integer> columnasNumero,Integer rowJump) {

		HSSFWorkbook book = (HSSFWorkbook) document;
		HSSFSheet sheet = book.getSheetAt(0);

		HSSFCellStyle intStyle = book.createCellStyle();
		intStyle.setDataFormat((short) 1);

		HSSFCellStyle decStyle = book.createCellStyle();
		decStyle.setDataFormat((short) 2);

		HSSFCellStyle dollarStyle = book.createCellStyle();
		dollarStyle.setDataFormat((short) 5);

		for (Row row : sheet) {
			if (row.getRowNum() <= rowJump) {
				continue; // Aqui me encargo de que no coja la fila de los
							// nombres
			}

			for (Cell cell : row) {
				int numeroColumna = cell.getColumnIndex();
				if (columnasNumero.contains(numeroColumna))
					continue;

				if ((cell.getCellType()== Cell.CELL_TYPE_FORMULA))
					continue;

				String strVal = cell.getStringCellValue();
				String strVal2 = cell.getStringCellValue();

				if (strVal.isEmpty() || strVal == null )
					continue;

				if(strVal.contains("org.primefaces.component.commandbutton.CommandButton"))
				{
					row.removeCell(cell);
					continue;
				}



				cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
				cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);

				try {
					strVal = strVal.replaceAll("\\s+", "");
					strVal = strVal.replaceAll("(?<=\\d),(?=\\d)|\\$", "");
					
					
					  if(InetAddress.getLocalHost().getHostName().equals("WSCEVALLOS"))
                       {
                           //TODO: ESTO FUNCIONA SOLO EN MI MAQUINA
                           strVal = strVal.replace(".", "");
                           strVal = strVal.replace(",", ".");
                       }
                       else{
                           //TODO: PARA EL SERVIDOR YA QUE EL SERVIDOR 1,089,544.6 coloca al reves las cosas
                           strVal = strVal.replace(",", "");
                       }

					
					

					if (strVal.indexOf('.') == -1) {
						// integer
						// numStyle.setDataFormat((short)1);

						int intVal = Integer.valueOf(strVal);

						cell.setCellStyle(intStyle);
						cell.setCellValue(intVal);
					} else {
						// double
						if (strVal.startsWith("$") || strVal.startsWith("-$")) {
							strVal = strVal.replace("$", StringUtils.EMPTY);
							// numStyle.setDataFormat((short)5);
							cell.setCellStyle(dollarStyle);
						}

						else {
							// numStyle.setDataFormat((short)2);
							cell.setCellStyle(decStyle);
						}

						// double dblVal = Double.parseDouble(strVal);
						// cell.setCellValue(dblVal);

						cell.setCellValue(obtenerValor(strVal2).doubleValue());
					}

				} catch (Exception e) {
					e.printStackTrace();

				}

			}
		}



	}
}
