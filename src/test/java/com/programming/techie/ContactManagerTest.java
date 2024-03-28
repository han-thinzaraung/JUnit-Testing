package com.programming.techie;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ContactManagerTest {
    ContactManager contactManager;

    @BeforeAll
    public static void setupAll(){
        System.out.println("Should Print Before All Tests");
    }

    @BeforeEach
    public void setup(){
       contactManager = new ContactManager();

    }
        
    @Test
    public void shouldCreateContact() {
       contactManager.addContact("John", "Doe", "0988888888");
       Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
       Assertions.assertEquals(1,contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should not create contact when first name is null")
    public void shouldThrowRunTimeExceptionWhenFirstNameIsNull(){

        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null,"Doe","0988888888");
        });
    }
    @Test
    @DisplayName("Should not create contact when last name is null")
    public void shouldThrowRunTimeExceptionWhenLastNameIsNull(){
  
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("John",null,"0988888888");
        });
    }
    @Test
    @DisplayName("Should not create contact when phone number is null")
    public void shouldThrowRunTimeExceptionWhenPhoneNumberIsNull(){
 
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("John","Doe",null);
        });
    }

    @Test
    @DisplayName("Assert Contact Creation on Developer Machine")
    public void shouldTestContactCreationOnDev() {
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
       contactManager.addContact("John", "Doe", "0988888888");
       Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
       Assertions.assertEquals(1,contactManager.getAllContacts().size());
    }
    @DisplayName("Repeat Contact Creation Test 5 times")
    @RepeatedTest(value = 5, 
        name = "Repeating Contact Creation Test {currentRepetition} of {totalRepetitions}")
    public void shouldTestContactCreationRepeatedBy() {
       contactManager.addContact("John", "Doe", "0988888888");
       Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
       Assertions.assertEquals(1,contactManager.getAllContacts().size());
    }
    @DisplayName("Repeat Contact Creation Test 5 times")
    @ParameterizedTest
    @ValueSource(strings = {"0123456789","0123456789","0123456789"})
    public void shouldTestContactCreationUsingValueSource(String phoneNumber) {
       contactManager.addContact("John", "Doe", phoneNumber);
       Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
       Assertions.assertEquals(1,contactManager.getAllContacts().size());
    }

    @DisplayName("Repeat Contact Creation Test 5 times")
    @ParameterizedTest
    @MethodSource("phoneNumberList")
    public void shouldTestContactCreationUsingMethodSource(String phoneNumber) {
       contactManager.addContact("John", "Doe", phoneNumber);
       Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
       Assertions.assertEquals(1,contactManager.getAllContacts().size());
    }
    private static List<String> phoneNumberList(){
        return Arrays.asList("0123456789" , "0012345678" , "0001234567");
        
    }

    
    @DisplayName("Repeat Contact Creation Test 5 times")
    @ParameterizedTest
    // @CsvSource("0123456789","0123456789","0123456789")
    public void shouldTestPhoneNumberFormatUsingCSVSource(String phoneNumber) {
       contactManager.addContact("John", "Doe", phoneNumber);
       Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
       Assertions.assertEquals(1,contactManager.getAllContacts().size());
    }


    @AfterEach
    public void tearDown(){
        System.out.println("Should Execute After Each Test");
    }
    @AfterAll
    public static void tearDownAll(){
        System.out.println("Should be executed at the end of the test");
    }

}
    