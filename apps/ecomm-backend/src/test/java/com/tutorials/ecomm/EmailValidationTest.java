package com.tutorials.ecomm;


import org.junit.Test;

import java.util.regex.Pattern;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class EmailValidationTest {


  @Test
  public void shouldRejectInvalidEmail() {
    Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    assertFalse(pattern.matcher("sdsadsad").matches());
  }

  @Test
  public void shouldAcceptValidEmail() {
    Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    assertTrue(pattern.matcher("alice@example.com").matches());
  }

}
