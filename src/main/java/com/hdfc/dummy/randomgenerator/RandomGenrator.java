package com.hdfc.dummy.randomgenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.hdfc.dummy.entity.RevGeoCode;
import com.hdfc.dummy.service.IRevGeoCodeService;
import com.hdfc.dummy.service.RevGeoCodeService;

@Component
public class RandomGenrator {

	private final IRevGeoCodeService revGeoCodeService;

	@Autowired
	public RandomGenrator(IRevGeoCodeService revGeoCodeService) {
		this.revGeoCodeService = revGeoCodeService;
	}

	public static int noOfRecords = 10;

	public static FileOutputStream fileOut;

	private static Workbook workbook;
	private static Sheet sheet;
	private static int currentColumn = 0;

	public void randomValueGenerator() {

		try {

			File excelFile = new File("InputFields.xlsx"); // Replace with your Excel
																							// file path
			FileInputStream fis = new FileInputStream(excelFile);

			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet

			ExcelWriter("output.xlsx", "Sheet1");

			Iterator<Row> rowIterator = sheet.iterator();

			currentColumn = 0;

			Row row = rowIterator.next();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				Cell fieldCell = row.getCell(0);
				Cell classCell = row.getCell(1);
				Cell limitCell = row.getCell(2);
				Cell minCell = row.getCell(3);
				Cell maxCell = row.getCell(4);
				Cell expCell = row.getCell(5);

				if (fieldCell != null && classCell != null) {
					String fieldName = fieldCell.getStringCellValue();
					String className = classCell.getStringCellValue();

					if (limitCell != null && minCell != null && maxCell != null && expCell != null) {

						Integer limit = (int) limitCell.getNumericCellValue();
						Integer min = (int) minCell.getNumericCellValue();
						Integer max = (int) maxCell.getNumericCellValue();
						String exp = expCell.getStringCellValue();

						generateFields(className, fieldName, limit, min, max, exp);
					} else {

						generateFields(className, fieldName, 0, 0, 0, null);
					}
				}

			}

			saveAndCloseExcel("output.xlsx");

			workbook.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	///////////////////////////////////////////////////////////////////////////////////

	private void generateFields(String className, String fieldName, int limit, int min, int max, String exp) {

		System.out.println(fieldName + " " + className);

		switch (className) {

		case "RegexGenerator":

			ArrayList<String> regexArrayList = new ArrayList<>();
			for (int i = 0; i < noOfRecords; i++) {
				regexArrayList.add(RegexGenerator.generator(exp,min,max,limit));
			}
			System.out.println(regexArrayList.toString());
			System.out.println();

			String[] regexArray = new String[regexArrayList.size()];
			regexArray = regexArrayList.toArray(regexArray);
			writeFieldsToExcel(regexArray, fieldName);

			break;

		case "AadharNumberGenerartor":
			Set<String> generatedAadharNumbers = new HashSet<>();
			while (generatedAadharNumbers.size() < noOfRecords) {
				generatedAadharNumbers.add(AadharGenerator.generator());

			}

			String[] AadharNumbersArray = generatedAadharNumbers.toArray(new String[0]);
			String[] PanArray = new String[AadharNumbersArray.length];

			for (int i = 0; i < AadharNumbersArray.length; i++) {

				PanArray[i] = "607152" + AadharNumbersArray[i];

			}

			writeFieldsToExcel(AadharNumbersArray, fieldName);
			writeFieldsToExcel(PanArray, "Pan (607152+AadharNo)");
			System.out.println(generatedAadharNumbers);
			System.out.println();
			break;

		case "FirstNameGenerator":

			ArrayList<String> firstNamesArrayList = new ArrayList<>();
			for (int i = 0; i < noOfRecords; i++) {
				firstNamesArrayList.add(FirstNameGenerator.generator());
			}
			System.out.println(firstNamesArrayList.toString());
			System.out.println();

			String[] firstNamesArray = new String[firstNamesArrayList.size()];
			firstNamesArray = firstNamesArrayList.toArray(firstNamesArray);
			writeFieldsToExcel(firstNamesArray, fieldName);
			break;

		case "LastNameGenerator":

			ArrayList<String> lastNamesArrayList = new ArrayList<>();
			for (int i = 0; i < noOfRecords; i++) {
				lastNamesArrayList.add(LastNameGenerator.generator());
			}
			System.out.println(lastNamesArrayList.toString());
			System.out.println();

			String[] lastNamesArray = new String[lastNamesArrayList.size()];
			lastNamesArray = lastNamesArrayList.toArray(lastNamesArray);
			writeFieldsToExcel(lastNamesArray, fieldName);
			break;

		case "FullNameGenerator":

			System.out.println("FullNameGenerator");

			String[] prfixArray = new String[noOfRecords];
			String[] firstNamesArray2 = new String[noOfRecords];
			String[] lastNamesArray2 = new String[noOfRecords];
			String[] fullNamesArray = new String[noOfRecords];
			String[] emailIDArray = new String[noOfRecords];
			String[] genderArray = new String[noOfRecords];
			String[] fatherNameArray = new String[noOfRecords];
			String[] motherNameArray = new String[noOfRecords];
			String[] relationArray = new String[noOfRecords];
			String[] guardianArray = new String[noOfRecords];
			String[] sexArray = new String[noOfRecords];

			for (int i = 0; i < noOfRecords; i++) {
				PersonalDetails personalDetails = FullNameGenerator.generator();

				prfixArray[i] = personalDetails.getPrefix();
				firstNamesArray2[i] = personalDetails.getFirstName();
				lastNamesArray2[i] = personalDetails.getLastName();
				fullNamesArray[i] = personalDetails.getFullName();
				emailIDArray[i] = personalDetails.getEmailID();
				genderArray[i] = personalDetails.getGender();
				fatherNameArray[i] = personalDetails.getFatherName();
				motherNameArray[i] = personalDetails.getMotherName();
				relationArray[i] = personalDetails.getRelation();
				guardianArray[i] = personalDetails.getGuardian();
				sexArray[i] = personalDetails.getSex();

			}

			writeFieldsToExcel(prfixArray, "Prefix");
			writeFieldsToExcel(firstNamesArray2, "First_Name");
			writeFieldsToExcel(lastNamesArray2, "Last_Name");
			writeFieldsToExcel(fullNamesArray, "Full_Name");
			writeFieldsToExcel(emailIDArray, "Email_ID");
			writeFieldsToExcel(genderArray, "Gender");
			writeFieldsToExcel(fatherNameArray, "Father_Name");
			writeFieldsToExcel(motherNameArray, "Mother_Name");
			writeFieldsToExcel(guardianArray, "Guardian_Name");
			writeFieldsToExcel(relationArray, "Nominee_Relationship");
			writeFieldsToExcel(sexArray, "Sex");

			break;

		case "PanGenerator":
			Set<String> generatedPanNumbers = new HashSet<>();
			while (generatedPanNumbers.size() < noOfRecords) {
				generatedPanNumbers.add(PanGenerator.generator());

			}
			String[] panNumbersArray = generatedPanNumbers.toArray(new String[0]);
			writeFieldsToExcel(panNumbersArray, fieldName);
			System.out.println(generatedPanNumbers);
			System.out.println();
			break;

		case "DOBGenerator":
			ArrayList<String> dobArrayList = new ArrayList<>();
			String[] dobArrayListArray2 = new String[noOfRecords];
			String[] dobArrayListArray3 = new String[noOfRecords];
			for (int i = 0; i < noOfRecords; i++) {

				String dob = DOBGenerator.generator();
				String dd = dob.substring(0, 2);
				String mm = dob.substring(2, 4);
				String yyyy = dob.substring(4, 8);
				dobArrayList.add(dob);
				dobArrayListArray2[i] = yyyy + mm + dd;
				dobArrayListArray3[i] = dd + "-" + mm + "-" + yyyy;

			}
			System.out.println(dobArrayList.toString());
			System.out.println();

			String[] dobArrayListArray = new String[dobArrayList.size()];
			dobArrayListArray = dobArrayList.toArray(dobArrayListArray);
			writeFieldsToExcel(dobArrayListArray, fieldName);
			writeFieldsToExcel(dobArrayListArray3, fieldName);
			writeFieldsToExcel(dobArrayListArray2, "datBirthCust");
			break;

		case "MobileNumberGenerator":

			Set<String> generatedMobileNumbers = new HashSet<>();
			while (generatedMobileNumbers.size() < noOfRecords) {
				generatedMobileNumbers.add(MobileNumberGenerator.generator());

			}

			String[] mobileNumbersArray = generatedMobileNumbers.toArray(new String[0]);

			String[] mobileNumbersArray2 = new String[generatedMobileNumbers.size()];
			String[] mobileNumbersArray3 = new String[generatedMobileNumbers.size()];

			for (int i = 0; i < mobileNumbersArray.length; i++) {
				mobileNumbersArray2[i] = "000" + mobileNumbersArray[i] + "0006";
				mobileNumbersArray3[i] = mobileNumbersArray[i].substring(2, 12);
			}

			writeFieldsToExcel(mobileNumbersArray, fieldName);
			writeFieldsToExcel(mobileNumbersArray2, "linkData");
			writeFieldsToExcel(mobileNumbersArray3, "MobileNumber");
			System.out.println(generatedMobileNumbers);
			System.out.println();
			break;

		case "NdigitNumberGenerator":
			ArrayList<String> nDigitNumberArrayList = new ArrayList<>();
			for (int i = 0; i < noOfRecords; i++) {
				nDigitNumberArrayList.add(NDigitNumberGenerator.generator(limit));
			}
			System.out.println(nDigitNumberArrayList.toString());
			System.out.println();

			String[] nDigitNumberArray = new String[nDigitNumberArrayList.size()];
			nDigitNumberArray = nDigitNumberArrayList.toArray(nDigitNumberArray);
			writeFieldsToExcel(nDigitNumberArray, fieldName);
			break;

		case "NdigitUniqueNumberGenerator":
			Set<String> generatedUniqueNumbers = new HashSet<>();
			while (generatedUniqueNumbers.size() < noOfRecords) {
				generatedUniqueNumbers.add(NDigitNumberGenerator.generator(limit));

			}
			System.out.println(generatedUniqueNumbers.toString());
			System.out.println();

			String[] generatedUniqueNumbersArray = generatedUniqueNumbers.toArray(new String[0]);
			writeFieldsToExcel(generatedUniqueNumbersArray, fieldName);
			break;

		case "AlphaNumericGenerator":

			Set<String> alphaNumericArrayList = new HashSet<>();
			while (alphaNumericArrayList.size() < noOfRecords) {
				alphaNumericArrayList.add(AlphaNumericGenerator.generator(limit));
			}
			System.out.println(alphaNumericArrayList.toString());
			System.out.println();

			String[] alphaNumericArrayListArray = alphaNumericArrayList.toArray(new String[0]);
			writeFieldsToExcel(alphaNumericArrayListArray, fieldName);
			break;

		case "YYYYMMDDhhmmssGenerator":

			ArrayList<String> YYYYMMDDhhmmssArrayList = new ArrayList<>();
			String[] MMDDhhmmssArray = new String[noOfRecords];
			String[] hhmmssArray = new String[noOfRecords];
			String[] MMDDArray = new String[noOfRecords];
			String[] ddmmyyyyhhmmssArray = new String[noOfRecords];
			String[] localDateArray = new String[noOfRecords];

			for (int i = 0; i < noOfRecords; i++) {

				String date = YYYYMMDDhhmmssGenerator.generator();
				YYYYMMDDhhmmssArrayList.add(date);
				MMDDhhmmssArray[i] = date.substring(4, 14);
				hhmmssArray[i] = date.substring(8, 14);
				MMDDArray[i] = date.substring(4, 8);
				ddmmyyyyhhmmssArray[i] = date.substring(6, 8) + date.substring(4, 6) + date.substring(0, 5)
						+ hhmmssArray[i];
				localDateArray[i] = date.substring(6, 8) + date.substring(4, 6) + date.substring(2, 4);

			}
			System.out.println(YYYYMMDDhhmmssArrayList.toString());
			System.out.println();

			String[] YYYYMMDDhhmmssArray = new String[YYYYMMDDhhmmssArrayList.size()];
			YYYYMMDDhhmmssArray = YYYYMMDDhhmmssArrayList.toArray(YYYYMMDDhhmmssArray);
			writeFieldsToExcel(YYYYMMDDhhmmssArray, fieldName);
			writeFieldsToExcel(MMDDhhmmssArray, "MMDDhhmmss");
			writeFieldsToExcel(hhmmssArray, "hhmmss");
			writeFieldsToExcel(MMDDArray, "MMDD");
			writeFieldsToExcel(ddmmyyyyhhmmssArray, "ddmmyyyyhhmmss");
			writeFieldsToExcel(localDateArray, "localDate");
			break;

		case "YYYYMMDDThhmmssGenerator":

			ArrayList<String> YYYYMMDDThhmmssArrayList1 = new ArrayList<>();
			ArrayList<String> YYYYMMDDThhmmssArrayList2 = new ArrayList<>();
			ArrayList<String> YYYYMMDDThhmmssArrayList3 = new ArrayList<>();
			for (int i = 0; i < noOfRecords; i++) {

				YYYYMMDDThhmmssArrayList3.add(YYYYMMDDThhmmssGenerator.generator());
				YYYYMMDDThhmmssArrayList1.add(YYYYMMDDThhmmssArrayList3.get(i).substring(0, 15));
				YYYYMMDDThhmmssArrayList2.add(YYYYMMDDThhmmssArrayList3.get(i).substring(15));
			}

			System.out.println(YYYYMMDDThhmmssArrayList1.toString());
			System.out.println();

			String[] YYYYMMDDThhmmssArray1 = new String[YYYYMMDDThhmmssArrayList1.size()];
			YYYYMMDDThhmmssArray1 = YYYYMMDDThhmmssArrayList1.toArray(YYYYMMDDThhmmssArray1);
			writeFieldsToExcel(YYYYMMDDThhmmssArray1, fieldName);

			String[] YYYYMMDDThhmmssArray2 = new String[YYYYMMDDThhmmssArrayList2.size()];
			YYYYMMDDThhmmssArray2 = YYYYMMDDThhmmssArrayList2.toArray(YYYYMMDDThhmmssArray2);
			writeFieldsToExcel(YYYYMMDDThhmmssArray2, "submitdate");
			break;

		case "AddressGenerator":

			ArrayList<String> addressLine1ArrayList = new ArrayList<>();
			ArrayList<String> addressLine2ArrayList = new ArrayList<>();
			ArrayList<String> addressLine3ArrayList = new ArrayList<>();
			ArrayList<String> cityArrayList = new ArrayList<>();
			ArrayList<String> stateArrayList = new ArrayList<>();
			ArrayList<String> countryArrayList = new ArrayList<>();
			ArrayList<String> zipArrayList = new ArrayList<>();

			for (int i = 0; i < noOfRecords; i++) {
				Address address = AddressGenerator.generator();

				addressLine1ArrayList.add(address.getAddressLine1());
				addressLine2ArrayList.add(address.getAddressLine2());
				addressLine3ArrayList.add(address.getAddressLine3());
				cityArrayList.add(address.getCity());
				stateArrayList.add(address.getState());
				countryArrayList.add(address.getCountry());
				zipArrayList.add(address.getZipCode());

			}

			String[] addressLine1Array = new String[addressLine1ArrayList.size()];
			String[] addressLine2Array = new String[addressLine2ArrayList.size()];
			String[] addressLine3Array = new String[addressLine3ArrayList.size()];
			String[] cityArray = new String[cityArrayList.size()];
			String[] stateArray = new String[stateArrayList.size()];
			String[] countryArray = new String[countryArrayList.size()];
			String[] zipArray = new String[zipArrayList.size()];

			addressLine1Array = addressLine1ArrayList.toArray(addressLine1Array);
			addressLine2Array = addressLine2ArrayList.toArray(addressLine2Array);
			addressLine3Array = addressLine3ArrayList.toArray(addressLine3Array);
			cityArray = cityArrayList.toArray(cityArray);
			stateArray = stateArrayList.toArray(stateArray);
			countryArray = countryArrayList.toArray(countryArray);
			zipArray = zipArrayList.toArray(zipArray);

			writeFieldsToExcel(addressLine1Array, "Address_Line_1");
			writeFieldsToExcel(addressLine2Array, "Address_Line_2");
			writeFieldsToExcel(addressLine3Array, "Address_Line_3");
			writeFieldsToExcel(cityArray, "City");
			writeFieldsToExcel(stateArray, "State");
			writeFieldsToExcel(countryArray, "Country");
			writeFieldsToExcel(zipArray, "Zip_Code");

			break;

		case "UdcGenerator":

			Set<String> generatedUdc = new HashSet<>();
			while (generatedUdc.size() < noOfRecords) {
				generatedUdc.add(UdcGenerator.generator());

			}

			String[] UdcArray = generatedUdc.toArray(new String[0]);

			writeFieldsToExcel(UdcArray, fieldName);
			System.out.println(UdcArray);
			System.out.println();
			break;

		case "DateTimeZoneGenerator":

			ArrayList<String> dateTimeZoneArrayList = new ArrayList<>();
			for (int i = 0; i < noOfRecords; i++) {
				dateTimeZoneArrayList.add(DateTimeZoneGenerator.generator());
			}
			System.out.println(dateTimeZoneArrayList.toString());
			System.out.println();

			String[] dateTimeZoneArray = new String[dateTimeZoneArrayList.size()];
			dateTimeZoneArray = dateTimeZoneArrayList.toArray(dateTimeZoneArray);
			writeFieldsToExcel(dateTimeZoneArray, fieldName);
			break;

		case "AccountNumberGenerator":

			Set<String> generatedAccountNumbers = new HashSet<>();
			while (generatedAccountNumbers.size() < noOfRecords) {
				generatedAccountNumbers.add(AccountNumberGenerator.generator());

			}

			String[] accountNumbersArray = generatedAccountNumbers.toArray(new String[0]);

			writeFieldsToExcel(accountNumbersArray, fieldName);
			System.out.println(generatedAccountNumbers);
			System.out.println();
			break;

		case "CustomerIdGenerator":

			Set<String> generatedCustomerId = new HashSet<>();
			while (generatedCustomerId.size() < noOfRecords) {
				generatedCustomerId.add(CustomerIdGenerator.generator());

			}

			String[] customerIdArray = generatedCustomerId.toArray(new String[0]);
			writeFieldsToExcel(customerIdArray, fieldName);
			System.out.println(generatedCustomerId);
			System.out.println();
			break;

		case "DDMMYYYYGenerator":

			ArrayList<String> ddmmyyyyArrayList = new ArrayList<>();
			for (int i = 0; i < noOfRecords; i++) {
				ddmmyyyyArrayList.add(DDMMYYYYGenerator.generator());
			}
			System.out.println(ddmmyyyyArrayList.toString());
			System.out.println();

			String[] ddmmyyyyArray = new String[ddmmyyyyArrayList.size()];
			ddmmyyyyArray = ddmmyyyyArrayList.toArray(ddmmyyyyArray);
			writeFieldsToExcel(ddmmyyyyArray, fieldName);
			writeFieldsToExcel(ddmmyyyyArray, "datLastMnt");

			break;

		case "DocNumberGenerator":

			Set<String> generatedDocNumbers = new HashSet<>();
			while (generatedDocNumbers.size() < noOfRecords) {
				generatedDocNumbers.add(NDigitNumberGenerator.generator(12));

			}

			String[] docNumbersArray = generatedDocNumbers.toArray(new String[0]);

			writeFieldsToExcel(docNumbersArray, fieldName);
			writeFieldsToExcel(docNumbersArray, "proofOfAddrDocNumber");
			System.out.println(generatedDocNumbers);
			System.out.println();
			break;

		case "BalanceGenrator":

			ArrayList<String> amountHoldList = new ArrayList<>();
			ArrayList<String> amountUnclearList = new ArrayList<>();
			ArrayList<String> currentAccountBalanceList = new ArrayList<>();
			ArrayList<String> currentLimitAmountList = new ArrayList<>();
			ArrayList<String> netBalanceList = new ArrayList<>();

			for (int i = 0; i < noOfRecords; i++) {

				float amountHold = Float.parseFloat(NDigitNumberGenerator.generator(7)) / 100;
				float currentAccountBalance = Float.parseFloat(NDigitNumberGenerator.generator(6)) / 100;

				amountHoldList.add(amountHold + "");
				amountUnclearList.add("0.00");
				currentAccountBalanceList.add(currentAccountBalance + "");
				currentLimitAmountList.add("0.00");
				netBalanceList.add((currentAccountBalance - amountHold) + "");

			}

			String[] amountHoldArray = new String[amountHoldList.size()];
			String[] amountUnclearArray = new String[amountUnclearList.size()];
			String[] currentAccountBalanceArray = new String[currentAccountBalanceList.size()];
			String[] currentLimitAmountArray = new String[currentLimitAmountList.size()];
			String[] netBalanceArray = new String[netBalanceList.size()];

			amountHoldArray = amountHoldList.toArray(amountHoldArray);
			amountUnclearArray = amountUnclearList.toArray(amountUnclearArray);
			currentAccountBalanceArray = currentAccountBalanceList.toArray(currentAccountBalanceArray);
			currentLimitAmountArray = currentLimitAmountList.toArray(currentLimitAmountArray);
			netBalanceArray = netBalanceList.toArray(netBalanceArray);

			writeFieldsToExcel(amountHoldArray, "amountHold");
			writeFieldsToExcel(amountUnclearArray, "amountUnclear");
			writeFieldsToExcel(currentAccountBalanceArray, "currentAccountBalance");
			writeFieldsToExcel(currentLimitAmountArray, "currentLimitAmount");
			writeFieldsToExcel(netBalanceArray, "netBalance");

			break;

		case "DepositDetailsGenerator":

			ArrayList<String> depositAmountList = new ArrayList<>();
			ArrayList<String> depositTermDaysList = new ArrayList<>();
			ArrayList<String> depositTermMnthsList = new ArrayList<>();
			ArrayList<String> datAcctOpenList = new ArrayList<>();
			ArrayList<String> datTDMaturityList = new ArrayList<>();
			ArrayList<String> intNxtAmtList = new ArrayList<>();
			ArrayList<String> datAcctOpenList2 = new ArrayList<>();
			ArrayList<String> datTDMaturityList2 = new ArrayList<>();

			for (int i = 0; i < noOfRecords; i++) {

				DepositDetail depositDetail = DepositDetailsGenerator.generator();

				depositAmountList.add(depositDetail.getDepositAmount());
				depositTermDaysList.add(depositDetail.getDepositTermDays());
				depositTermMnthsList.add(depositDetail.getDepositTermMnths());
				datAcctOpenList.add(depositDetail.getDatAcctOpen());
				datTDMaturityList.add(depositDetail.getDatTDMaturity());
				intNxtAmtList.add(depositDetail.getIntNxtAmt());
				datAcctOpenList2.add(depositDetail.getDepositOpeningValueDate());
				datTDMaturityList2.add(depositDetail.getMaturityDate());

			}

			String[] depositAmountArray = new String[depositAmountList.size()];
			String[] depositTermDaysArray = new String[depositTermDaysList.size()];
			String[] depositTermMnthsArray = new String[depositTermMnthsList.size()];
			String[] datAcctOpenArray = new String[datAcctOpenList.size()];
			String[] datTDMaturityArray = new String[datTDMaturityList.size()];
			String[] intNxtAmtArray = new String[intNxtAmtList.size()];

			String[] datAcctOpenArray2 = new String[datAcctOpenList2.size()];
			String[] datTDMaturityArray2 = new String[datTDMaturityList2.size()];

			depositAmountArray = depositAmountList.toArray(depositAmountArray);
			depositTermDaysArray = depositTermDaysList.toArray(depositTermDaysArray);
			depositTermMnthsArray = depositTermMnthsList.toArray(depositTermMnthsArray);
			datAcctOpenArray = datAcctOpenList.toArray(datAcctOpenArray);
			datTDMaturityArray = datTDMaturityList.toArray(datTDMaturityArray);
			intNxtAmtArray = intNxtAmtList.toArray(intNxtAmtArray);

			datAcctOpenArray2 = datAcctOpenList2.toArray(datAcctOpenArray2);
			datTDMaturityArray2 = datTDMaturityList2.toArray(datTDMaturityArray2);

			writeFieldsToExcel(depositAmountArray, "depositAmt");
			writeFieldsToExcel(depositTermDaysArray, "depositTermDays");
			writeFieldsToExcel(depositTermMnthsArray, "depositTermMnths");
			writeFieldsToExcel(depositAmountArray, "amtTdMAturity");
			writeFieldsToExcel(depositAmountArray, "balTdPrincipal");
			writeFieldsToExcel(datAcctOpenArray, "datAcctOpen");
			writeFieldsToExcel(datAcctOpenArray, "datDepDate");
			writeFieldsToExcel(datAcctOpenArray, "datRenewal");
			writeFieldsToExcel(datTDMaturityArray, "datTDMaturity");
			writeFieldsToExcel(datAcctOpenArray, "datValueDate");
			writeFieldsToExcel(intNxtAmtArray, "intNxtAmt");

			writeFieldsToExcel(datAcctOpenArray2, "depositOpeningValueDate");
			writeFieldsToExcel(datTDMaturityArray2, "maturityDate");

			break;

		case "TimeStampInMillisecondsGenrator":

			ArrayList<String> timeStampArrayList = new ArrayList<>();
			ArrayList<String> externalReferenceNumberList = new ArrayList<>();
			for (int i = 0; i < noOfRecords; i++) {
				String timeStamp = TimeStampInMillisecondsGenrator.generator();
				Long externalReferenceNumber = TimeStampInMillisecondsGenrator
						.roundToTenDigits(Long.parseLong(timeStamp)) / 1000;
				timeStampArrayList.add(timeStamp);
				externalReferenceNumberList.add(externalReferenceNumber.toString());
			}
			System.out.println(timeStampArrayList.toString());
			System.out.println();

			String[] timeStampArray = new String[timeStampArrayList.size()];
			timeStampArray = timeStampArrayList.toArray(timeStampArray);

			String[] externalReferenceNumberArray = new String[externalReferenceNumberList.size()];
			externalReferenceNumberArray = externalReferenceNumberList.toArray(externalReferenceNumberArray);

			writeFieldsToExcel(timeStampArray, fieldName);
			writeFieldsToExcel(externalReferenceNumberArray, "externalReferenceNo");

			break;

		case "IfscCodeGenerator":

			ArrayList<String> ifscCodeList = new ArrayList<>();
			for (int i = 0; i < noOfRecords; i++) {
				ifscCodeList.add(IfscCodeGenerator.generator());
			}
			System.out.println(ifscCodeList.toString());
			System.out.println();

			String[] ifscCodeArray = new String[ifscCodeList.size()];
			ifscCodeArray = ifscCodeList.toArray(ifscCodeArray);
			writeFieldsToExcel(ifscCodeArray, fieldName);

			break;

		case "UserIDGenerator":

			ArrayList<String> userIdList = new ArrayList<>();
			for (int i = 0; i < noOfRecords; i++) {
				userIdList.add(UserIDGenerator.generator());
			}
			System.out.println(userIdList.toString());
			System.out.println();

			String[] userIdArray = new String[userIdList.size()];
			userIdArray = userIdList.toArray(userIdArray);
			writeFieldsToExcel(userIdArray, fieldName);

			break;

		case "DDMMYYYYGenerator2":

			ArrayList<String> ddMMyyyyList = new ArrayList<>();
			for (int i = 0; i < noOfRecords; i++) {
				ddMMyyyyList.add(DDMMYYYYGenerator2.generator());
			}
			System.out.println(ddMMyyyyList.toString());
			System.out.println();

			String[] ddMMyyyyArray = new String[ddMMyyyyList.size()];
			ddMMyyyyArray = ddMMyyyyList.toArray(ddMMyyyyArray);
			writeFieldsToExcel(ddMMyyyyArray, fieldName);
			break;

		case "IncomeGenerator":

			ArrayList<String> incomeList = new ArrayList<>();
			for (int i = 0; i < noOfRecords; i++) {
				incomeList.add(IncomeGenerator.generator());
			}
			System.out.println(incomeList.toString());
			System.out.println();

			String[] incomeArray = new String[incomeList.size()];
			incomeArray = incomeList.toArray(incomeArray);
			writeFieldsToExcel(incomeArray, fieldName);
			break;

		case "IdDocNumberGenerator":

			ArrayList<String> idDocNumberList = new ArrayList<>();
			for (int i = 0; i < noOfRecords; i++) {
				idDocNumberList.add(IdDocNumberGenerator.generator());
			}
			System.out.println(idDocNumberList.toString());
			System.out.println();

			String[] idDocNumberArray = new String[idDocNumberList.size()];
			idDocNumberArray = idDocNumberList.toArray(idDocNumberArray);
			writeFieldsToExcel(idDocNumberArray, fieldName);
			break;

		case "DateIdGenerator":

			ArrayList<String> dateIdList = new ArrayList<>();
			for (int i = 0; i < noOfRecords; i++) {
				dateIdList.add(DateIdGenerator.generator());
			}
			System.out.println(dateIdList.toString());
			System.out.println();

			String[] dateIdArray = new String[dateIdList.size()];
			dateIdArray = dateIdList.toArray(dateIdArray);
			writeFieldsToExcel(dateIdArray, fieldName);
			break;

		case "IpAddressGenerator":

			String[] ipAddressArray = new String[noOfRecords];
			for (int i = 0; i < noOfRecords; i++) {
				ipAddressArray[i] = IpAddressGenerator.generator();
			}

			writeFieldsToExcel(ipAddressArray, fieldName);
			break;

		case "ObjectUniqueIdGenerator":

			Set<String> generatedObjectUniqueId = new HashSet<>();
			while (generatedObjectUniqueId.size() < noOfRecords) {
				generatedObjectUniqueId.add(ObjectUniqueIdGenerator.generator());

			}

			String[] objectUniqueIdArray = generatedObjectUniqueId.toArray(new String[0]);

			writeFieldsToExcel(objectUniqueIdArray, fieldName);
			System.out.println(generatedObjectUniqueId);
			System.out.println();
			break;

		case "LocationGenerator":

			System.out.println("Ser " + revGeoCodeService);

			String[] houseNumberArray = new String[noOfRecords];
			String[] houseNameArray = new String[noOfRecords];
			String[] poiArray = new String[noOfRecords];
			String[] poiDistArray = new String[noOfRecords];
			String[] streetArray = new String[noOfRecords];
			String[] streetDistArray = new String[noOfRecords];
			String[] subSubLocalityArray = new String[noOfRecords];
			String[] subLocalityArray = new String[noOfRecords];
			String[] localityArray = new String[noOfRecords];
			String[] villageArray = new String[noOfRecords];
			String[] districtArray = new String[noOfRecords];
			String[] subDistrictArray = new String[noOfRecords];
			String[] cityArray2 = new String[noOfRecords];
			String[] stateArray2 = new String[noOfRecords];
			String[] pincodeArray = new String[noOfRecords];
			String[] latArray = new String[noOfRecords];
			String[] lngArray = new String[noOfRecords];
			String[] areaArray = new String[noOfRecords];
			String[] formattedAddressArray = new String[noOfRecords];
			String[] responseString = new String[noOfRecords];

			String[] requestStringArray = new String[noOfRecords];

			for (int i = 0; i < noOfRecords; i++) {

				RevGeoCode revGeoCode = new RevGeoCode();
				revGeoCode = revGeoCodeService.generateRevGeoCode();

				houseNumberArray[i] = revGeoCode.getHouseNumber();
				houseNameArray[i] = revGeoCode.getHouseName();
				poiArray[i] = revGeoCode.getPoi();
				poiDistArray[i] = revGeoCode.getPoiDist();
				streetArray[i] = revGeoCode.getStreet();
				streetDistArray[i] = revGeoCode.getStreetDist();
				subSubLocalityArray[i] = revGeoCode.getSubSubLocality();
				subLocalityArray[i] = revGeoCode.getSubDistrict();
				localityArray[i] = revGeoCode.getLocality();
				villageArray[i] = revGeoCode.getVillage();
				districtArray[i] = revGeoCode.getDistrict();
				subDistrictArray[i] = revGeoCode.getSubDistrict();
				cityArray2[i] = revGeoCode.getCity();
				stateArray2[i] = revGeoCode.getState();
				pincodeArray[i] = revGeoCode.getPincode();
				latArray[i] = revGeoCode.getLat();
				lngArray[i] = revGeoCode.getLng();
				areaArray[i] = revGeoCode.getArea();
				formattedAddressArray[i] = revGeoCode.getFormattedAddress();

				responseString[i] = "{\\\"responseCode\\\":200,\\\"version\\\":\\\"2023.06\\\",\\\"results\\\":[{\\\"houseNumber\\\":\\\""
						+ houseNumberArray[i] + "\\\",\\\"houseName\\\":\\\"" + houseNameArray[i]
						+ "\\\",\\\"poi\\\":\\\"" + poiArray[i] + "\\\",\\\"poi_dist\\\":\\\"" + poiDistArray[i]
						+ "\\\",\\\"street\\\":\\\"" + streetArray[i] + "\\\",\\\"street_dist\\\":\\\""
						+ streetDistArray[i] + "\\\",\\\"subSubLocality\\\":\\\"" + subSubLocalityArray[i]
						+ "\\\",\\\"subLocality\\\":\\\"" + subLocalityArray[i] + "\\\",\\\"locality\\\":\\\""
						+ localityArray[i] + "\\\",\\\"village\\\":\\\"" + villageArray[i]
						+ "\\\",\\\"district\\\":\\\"" + districtArray[i] + "\\\",\\\"subDistrict\\\":\\\""
						+ subDistrictArray[i] + "\\\",\\\"city\\\":\\\"" + cityArray2[i] + "\\\",\\\"state\\\":\\\""
						+ stateArray2[i] + "\\\",\\\"pincode\\\":\\\"" + pincodeArray[i] + "\\\",\\\"lat\\\":\\\""
						+ latArray[i] + "\\\",\\\"lng\\\":\\\"" + lngArray[i] + "\\\",\\\"area\\\":\\\"" + areaArray[i]
						+ "\\\",\\\"formatted_address\\\":\\\"" + formattedAddressArray[i] + "\\\"}]}";

				requestStringArray[i] = "lat=" + revGeoCode.getLat() + "&lng=" + revGeoCode.getLng();

			}

			writeFieldsToExcel(requestStringArray, "requestString");
			writeFieldsToExcel(houseNumberArray, "houseNumber");
			writeFieldsToExcel(houseNameArray, "houseName");
			writeFieldsToExcel(poiArray, "poi");
			writeFieldsToExcel(poiDistArray, "poiDist");
			writeFieldsToExcel(streetArray, "street");
			writeFieldsToExcel(streetDistArray, "streetDist");
			writeFieldsToExcel(subSubLocalityArray, "subSubLocality");
			writeFieldsToExcel(subLocalityArray, "subLocality");
			writeFieldsToExcel(localityArray, "locality");
			writeFieldsToExcel(villageArray, "village");
			writeFieldsToExcel(districtArray, "district");
			writeFieldsToExcel(subDistrictArray, "subDistrict");
			writeFieldsToExcel(cityArray2, "city");
			writeFieldsToExcel(stateArray2, "state");
			writeFieldsToExcel(pincodeArray, "pinCode");
			writeFieldsToExcel(latArray, "lat");
			writeFieldsToExcel(lngArray, "lng");
			writeFieldsToExcel(areaArray, "area");
			writeFieldsToExcel(formattedAddressArray, "formattedAddress");

			writeFieldsToExcel(responseString, "responseString");

			break;

		}

	}

	///////////////////////////////////////////////////////////////////////////////////////////

	public static void ExcelWriter(String filePath, String sheetName) {
		try {
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeFieldsToExcel(String[] columnData, String fieldName) {
		// Write data to a single column
		int rowNum = 0;

		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum);
		}

		Cell cell = row.createCell(currentColumn);
		cell.setCellValue(fieldName);
		rowNum++;

		for (String cellData : columnData) {
			row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
			}

			cell = row.createCell(currentColumn);
			cell.setCellValue(cellData);
			rowNum++;
		}

		currentColumn++;
	}

	public static void saveAndCloseExcel(String filePath) {
		try (FileOutputStream fos = new FileOutputStream(filePath)) {
			workbook.write(fos);
			System.out.println("Data written to the Excel file successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
