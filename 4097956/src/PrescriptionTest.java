import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.*;

public class PrescriptionTest {
    private Prescription prescription;

    @Before
    public void setUp() {
        prescription = new Prescription();
    }

    @Test
    public void testAddPrescriptionValid() {
        // Test with valid input data
        prescription.setFirstName("Alice");
        prescription.setLastName("Johnson");
        prescription.setAddress("1234 Long Street, Suburb, 12345, Country");
        prescription.setSphere(1.50f);
        prescription.setCylinder(-2.00f);
        prescription.setAxis(90);
        prescription.setExaminationDate(new Date());
        prescription.setOptometrist("Dr. Richard");

        assertTrue(prescription.addPrescription());  // Expecting true for valid data
    }

    @Test
    public void testAddPrescriptionInvalidName() {
        // Test with an invalid first name and last name
        prescription.setFirstName("Al");  // Too short
        prescription.setLastName("Jo");   // Too short
        prescription.setAddress("1234 Long Street, Suburb, 12345, Country");
        prescription.setSphere(1.50f);
        prescription.setCylinder(-2.00f);
        prescription.setAxis(90);
        prescription.setExaminationDate(new Date());
        prescription.setOptometrist("Dr. Richard");

        assertFalse(prescription.addPrescription());  // Expecting false due to invalid name
    }

    @Test
    public void testAddPrescriptionInvalidAddress() {
        // Test with an invalid address
        prescription.setFirstName("Alice");
        prescription.setLastName("Johnson");
        prescription.setAddress("Short Addr");  // Too short
        prescription.setSphere(1.50f);
        prescription.setCylinder(-2.00f);
        prescription.setAxis(90);
        prescription.setExaminationDate(new Date());
        prescription.setOptometrist("Dr. Richard");

        assertFalse(prescription.addPrescription());  // Expecting false due to short address
    }

    @Test
    public void testAddPrescriptionInvalidSphere() {
        // Test with an invalid sphere value
        prescription.setFirstName("Alice");
        prescription.setLastName("Johnson");
        prescription.setAddress("1234 Long Street, Suburb, 12345, Country");
        prescription.setSphere(25.00f);  // Out of range
        prescription.setCylinder(-2.00f);
        prescription.setAxis(90);
        prescription.setExaminationDate(new Date());
        prescription.setOptometrist("Dr. Richard");

        assertFalse(prescription.addPrescription());  // Expecting false due to invalid sphere
    }

    @Test
    public void testAddPrescriptionInvalidCylinder() {
        // Test with an invalid cylinder value
        prescription.setFirstName("Alice");
        prescription.setLastName("Johnson");
        prescription.setAddress("1234 Long Street, Suburb, 12345, Country");
        prescription.setSphere(1.50f);
        prescription.setCylinder(-5.00f);  // Out of range
        prescription.setAxis(90);
        prescription.setExaminationDate(new Date());
        prescription.setOptometrist("Dr. Richard");

        assertFalse(prescription.addPrescription());  // Expecting false due to invalid cylinder
    }

    @Test
    public void testAddPrescriptionInvalidAxis() {
        // Test with an invalid axis value
        prescription.setFirstName("Alice");
        prescription.setLastName("Johnson");
        prescription.setAddress("1234 Long Street, Suburb, 12345, Country");
        prescription.setSphere(1.50f);
        prescription.setCylinder(-2.00f);
        prescription.setAxis(200);  // Out of range
        prescription.setExaminationDate(new Date());
        prescription.setOptometrist("Dr. Richard");

        assertFalse(prescription.addPrescription());  // Expecting false due to invalid axis
    }

    @Test
    public void testAddRemarkValid() {
        // Valid remark with a valid type
        String remark = "Client has shown great improvement over time.";
        String remarkType = "Client";

        assertTrue(prescription.addRemark(remark, remarkType));  // Expecting true for valid remark
    }

    @Test
    public void testAddRemarkInvalidWordCount() {
        // Remark with less than 6 words
        String remark = "Improvement over time.";
        String remarkType = "Client";

        assertFalse(prescription.addRemark(remark, remarkType));  // Expecting false due to invalid word count
    }

    @Test
    public void testAddRemarkInvalidFirstLetter() {
        // Remark with first word not starting with an uppercase letter
        String remark = "client has shown great improvement over time.";
        String remarkType = "Client";

        assertFalse(prescription.addRemark(remark, remarkType));  // Expecting false due to invalid first letter
    }

    @Test
    public void testAddRemarkInvalidRemarkType() {
        // Invalid remark type
        String remark = "Client has shown great improvement over time.";
        String remarkType = "InvalidType";

        assertFalse(prescription.addRemark(remark, remarkType));  // Expecting false due to invalid type
    }

    @Test
    public void testAddRemarkExceedRemarksLimit() {
        // Adding more than 2 remarks
        prescription.addRemark("Client has shown great improvement over time.", "Client");
        prescription.addRemark("Optometrist suggests further follow-up.", "Optometrist");

        // Attempting to add a 3rd remark
        String thirdRemark = "Another follow-up is scheduled.";
        assertFalse(prescription.addRemark(thirdRemark, "Client"));  // Expecting false due to exceeding limit
    }
}
