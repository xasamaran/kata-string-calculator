package main.test.java;


import main.java.exaltIT.stringCaluclator.StringCalculator;
import main.java.exaltIT.stringCaluclator.exception.NegativeNumbersException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class StringCalculatorTest {

    StringCalculator st;

    @Before
    public void setup(){
        st = new StringCalculator();
    }

    @Test
    public void Add_Ok_empty_isZero(){
        assertEquals(st.add(""),0);
    }

    @Test
    public void Add_Ok_Number_returnItSelf(){
        assertEquals(st.add("1"),1);
    }

    @Test
    public void Add_Ok_twoNumber_Added(){
        assertEquals(st.add("1,3"),4);
    }

    @Test
    public void Add_Ok_multipleNumber_Added(){
        assertEquals(st.add("1,3,5,7"),16);
    }

    @Test
    public void Add_Ok_lineBreak_CountAsSeparator(){
        assertEquals(st.add("1,3,5\n7"),16);
    }

    @Test
    public void Add_Ok_withCustomSeparator(){
        assertEquals(st.add("//:\n1:3:5:7"),16);

    }

    @Test
    public void Add_Ok_withCustomSeparatorAndLineBreak(){
        assertEquals(st.add("//:\n1:3\n5:7"),16);
    }

    @Test
    public void Add_Ko_negativeNumber(){
        Throwable exception = assertThrows(NegativeNumbersException.class, () -> st.add("-5"));
        assertEquals(exception.getMessage(),"negatives not allowed : -5");
    }

    @Test
    public void Add_Ko_multipleNegativeNumber(){
        Throwable exception = assertThrows(NegativeNumbersException.class, () -> st.add("-5,4,-8"));
        assertEquals(exception.getMessage(),"negatives not allowed : -5, -8");
    }

    @Test
    public void Add_Ko_multipleNegativeNumber_customSeparator(){
        Throwable exception = assertThrows(NegativeNumbersException.class, () -> st.add("//:\n-5:4:-8"));
        assertEquals(exception.getMessage(),"negatives not allowed : -5, -8");
    }
}
