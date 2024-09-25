package com.hdfc.dummy.randomgenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositDetail {
	
	private String depositAmount;
	private String depositTermDays;
	private String depositTermMnths;
	
	private String datAcctOpen;
	private String datTDMaturity;
	private String intNxtAmt;

	private String maturityDate;
	private String depositOpeningValueDate;
}
