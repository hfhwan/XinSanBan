package com.hfh.xinsanban.task;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.TimerTask;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.hfh.xinsanban.Constant;
import com.hfh.xinsanban.dao.CorpDao;
import com.hfh.xinsanban.pojo.Corp;

public class TaskImportCorp extends TimerTask{
	CorpDao corpDao = new CorpDao();

	@Override
	public void run() {
		try {
			readFromExcels();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readFromExcels() throws InvalidFormatException, IOException {
		// //1.得到Excel常用对象
		// POIFSFileSystem fs = new POIFSFileSystem(new
		// FileInputStream("d:/FTP/new1.xls"));
		// //2.得到Excel工作簿对象
		// HSSFWorkbook wb = new HSSFWorkbook(fs);
		System.out.println(Constant.getDate() +"-TaskImportCorp begin");
		InputStream ins = null;
		Workbook wb = null;
		ins = new FileInputStream(Constant.NeeqFilePath);
		// ins=
		// ExcelService.class.getClassLoader().getResourceAsStream(filePath);
		wb = WorkbookFactory.create(ins);
		ins.close();

		// 3.得到Excel工作表对象
		Sheet sheet = wb.getSheetAt(1);
		// 总行数
		int trLength = sheet.getLastRowNum();
		// 4.得到Excel工作表的行
		Row row = sheet.getRow(5);
		// 总列数
		int tdLength = row.getLastCellNum();

//		System.out.println("trlen" + trLength + "tdlen:" + tdLength);
		// 5.得到Excel工作表指定行的单元格
		// Cell cell = row.getCell((short) 1);
		// 6.得到单元格样式
		// CellStyle cellStyle = cell.getCellStyle();

		for (int i = 5; i < trLength; i++) {
			Corp corp = new Corp();
			// 得到Excel工作表的行
			Row row1 = sheet.getRow(i);

			Cell cell = row1.getCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String no = cell.getStringCellValue();
			corp.setNo(no);

			cell = row1.getCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String name = cell.getStringCellValue();
			corp.setName(name);

			cell = row1.getCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String cateNo1 = cell.getStringCellValue();
			corp.setCateNo1(cateNo1);

			cell = row1.getCell(3);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String cateName1 = cell.getStringCellValue();
			corp.setCateName1(cateName1);

			cell = row1.getCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String cateNo2 = cell.getStringCellValue();
			corp.setCateNo2(cateNo2);

			cell = row1.getCell(5);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String cateName2 = cell.getStringCellValue();
			corp.setCateName2(cateName2);

			cell = row1.getCell(6);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String cateNo3 = cell.getStringCellValue();
			corp.setCateNo3(cateNo3);

			cell = row1.getCell(7);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String cateName3 = cell.getStringCellValue();
			corp.setCateName3(cateName3);

			cell = row1.getCell(8);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String cateNo4 = cell.getStringCellValue();
			corp.setCateNo4(cateNo4);

			cell = row1.getCell(9);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String cateName4 = cell.getStringCellValue();
			corp.setCateName4(cateName4);

			// System.out.println(corp);

			corpDao.save(corp);
		}
		System.out.println(Constant.getDate() +"-TaskImportCorp complete, number:"+trLength);
	}
}
