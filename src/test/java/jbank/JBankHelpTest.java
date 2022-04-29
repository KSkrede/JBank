package jbank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jbank.logic.JBankHelp;

public class JBankHelpTest {

    @Test
	public void isAllDigitTest() {
		assertEquals(false, JBankHelp.isAllDigit("abc1"));
        assertEquals(false, JBankHelp.isAllDigit(""));
        assertEquals(false, JBankHelp.isAllDigit(" "));
        assertEquals(true, JBankHelp.isAllDigit("123"));	
    }

    @Test
    public void isAllLettersTest() {
		assertEquals(false, JBankHelp.isAllLetters("abc1"));
        assertEquals(false, JBankHelp.isAllLetters(""));
        assertEquals(false, JBankHelp.isAllLetters(" "));
        assertEquals(true, JBankHelp.isAllLetters("abc"));	
    }

    @Test
    public void isAllLettersOrBlank() {
		assertEquals(false, JBankHelp.isAllLettersOrBlank(" "));
        assertEquals(false, JBankHelp.isAllLettersOrBlank("abc1"));
        assertEquals(false, JBankHelp.isAllLettersOrBlank("123"));
        assertEquals(true, JBankHelp.isAllLettersOrBlank("Kim Andre"));	
    }
    
}
