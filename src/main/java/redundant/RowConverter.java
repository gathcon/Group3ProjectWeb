package redundant;

import java.math.BigInteger;

import model.EntityType;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;

public class RowConverter {

	public RowConverter() {
	}

	public ConvertedRow convert(HSSFRow row, EntityType e) {

		switch (e) {
		case BASEDATA:

			double date = row.getCell(0).getNumericCellValue();
			double event = row.getCell(1).getNumericCellValue();
			double failure = row.getCell(2).getNumericCellValue();
			double ue = row.getCell(3).getNumericCellValue();
			double market = row.getCell(4).getNumericCellValue();
			double operator = row.getCell(5).getNumericCellValue();
			double cell = row.getCell(6).getNumericCellValue();
			double duration = row.getCell(7).getNumericCellValue();
			double cause = row.getCell(8).getNumericCellValue();
			String ne = row.getCell(9).getStringCellValue();
			BigInteger imsi = BigInteger.valueOf((long) row.getCell(10)
					.getNumericCellValue());
			BigInteger h1 = BigInteger.valueOf((long) row.getCell(11)
					.getNumericCellValue());
			BigInteger h2 = BigInteger.valueOf((long) row.getCell(12)
					.getNumericCellValue());
			BigInteger h3 = BigInteger.valueOf((long) row.getCell(13)
					.getNumericCellValue());

			ConvertedRowBaseData baseDataRow = new ConvertedRowBaseData(
					(int) cell, (int) duration, h1, h2, h3, imsi,
					HSSFDateUtil.getJavaDate(date), ne, (int) failure,
					(int) event, (int) cause, (int) ue, (int) market,
					(int) operator);

			return baseDataRow;

		case EVENTCAUSE:

			double causeCode = row.getCell(0).getNumericCellValue();
			double eventId = row.getCell(1).getNumericCellValue();
			String des = row.getCell(2).getStringCellValue();

			ConvertedRowEventCause eventCauseRow = new ConvertedRowEventCause(
					(int) causeCode, (int) eventId, des);
			return eventCauseRow;

		case FAILURE:
			double failureClass = row.getCell(0).getNumericCellValue();
			String description = row.getCell(1).getStringCellValue();
			ConvertedRowFailure failureRow = new ConvertedRowFailure(
					(int) failureClass, description);
			return failureRow;

		case USEREQUIPMENT:
			double tac = row.getCell(0).getNumericCellValue();
			String marketName = row.getCell(1).getStringCellValue();
			String manufacturer = row.getCell(2).getStringCellValue();
			String access = row.getCell(3).getStringCellValue();
			String model = row.getCell(4).getStringCellValue();
			String vendor = row.getCell(5).getStringCellValue();
			String uetype = row.getCell(6).getStringCellValue();
			String os = row.getCell(7).getStringCellValue();
			String input = row.getCell(8).getStringCellValue();

			ConvertedRowUserEquipment userEquipmentRow = new ConvertedRowUserEquipment(
					(int) tac, marketName, manufacturer, access, model, vendor,
					uetype, os, input);
			return userEquipmentRow;

		case OPERATOR:
			double mcc = row.getCell(0).getNumericCellValue();
			double mnc = row.getCell(1).getNumericCellValue();
			String country = row.getCell(2).getStringCellValue();
			String operatorO = row.getCell(3).getStringCellValue();

			ConvertedRowOperator operatorRow = new ConvertedRowOperator(
					(int) mcc, (int) mnc, country, operatorO);
			return operatorRow;
		case USER:
			break;
		default:
			break;
		}
		return null;
	}
}
