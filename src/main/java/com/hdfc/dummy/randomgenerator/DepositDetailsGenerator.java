package com.hdfc.dummy.randomgenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DepositDetailsGenerator {

	public static DepositDetail generator() {

		Random random = new Random();
		DepositDetail depositDetail = new DepositDetail();

		int days = random.nextInt(30) + 1;
		int months = random.nextInt(12) + 1;
		int amount = (random.nextInt(200) + 1) * 500;
		float intrest= ((float)(amount)*4/36500)*((float)days+(30*(float)months));

		LocalDate currentDate = LocalDate.now();
		int randomDays = random.nextInt(2 * 365) + 1;
		LocalDate datAcctOpen = currentDate.minusDays(randomDays);
		LocalDate datTDMaturity = datAcctOpen.plusMonths(months).plusDays(days);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		String formattedDatAcctOpen = datAcctOpen.format(formatter);
		String formattedDatTDMaturity = datTDMaturity.format(formatter);
		
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMdd");
		String formattedDatAcctOpen2 = datAcctOpen.format(formatter2);
		String formattedDatTDMaturity2 = datTDMaturity.format(formatter2);

		depositDetail.setDepositTermDays(days + "");
		depositDetail.setDepositTermMnths(months + "");
		depositDetail.setDepositAmount(amount + "");
		depositDetail.setDatAcctOpen(formattedDatAcctOpen);
		depositDetail.setDatTDMaturity(formattedDatTDMaturity);
		depositDetail.setIntNxtAmt(intrest+"");
		depositDetail.setMaturityDate(formattedDatTDMaturity2);
		depositDetail.setDepositOpeningValueDate(formattedDatAcctOpen2);
		

		return depositDetail;

	}

}
