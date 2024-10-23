import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Prescription {
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private String[] remarkTypes = {"Client", "Optometrist"};
    private ArrayList<String> potRemarks = new ArrayList<>();

    // Setter methods
    public void setPrescID(int prescID) {
        this.prescID = prescID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSphere(float sphere) {
        this.sphere = sphere;
    }

    public void setAxis(float axis) {
        this.axis = axis;
    }

    public void setCylinder(float cylinder) {
        this.cylinder = cylinder;
    }

    public void setExaminationDate(Date examinationDate) {
        this.examinationDate = examinationDate;
    }

    public void setOptometrist(String optometrist) {
        this.optometrist = optometrist;
    }

    public boolean addPrescription() {
        // Validate name length
        if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15){
            return false;
        }

        // Validate address length
        if (address.length() < 20) {
            return false;
        }

        // Validate sphere, cylinder, and axis ranges
        if (sphere < -20.00 || sphere > 20.00) {
            return false;
        }
        if (cylinder < -4.00 || cylinder > 4.00) {
            return false;
        }
        if (axis < 0 || axis > 180) {
            return false;
        }

        // Validate examination date (assuming Date is already set correctly)
        if (examinationDate == null) {
            return false; // Ensure examination date is set
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        String dateString = dateFormat.format(examinationDate);

        // Validate optometrist name length
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            return false;
        }

        // Write the prescription to a file
        try (FileWriter writer = new FileWriter("presc.txt", true)) {
            writer.write("Prescription ID: " + prescID + "\n");
            writer.write("First Name: " + firstName + "\n");
            writer.write("Last Name: " + lastName + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Sphere: " + sphere + "\n");
            writer.write("Cylinder: " + cylinder + "\n");
            writer.write("Axis: " + axis + "\n");
            writer.write("Examination Date: " + dateString + "\n");
            writer.write("Optometrist: " + optometrist + "\n");
            writer.write("-----------------------------\n");
        } catch (IOException e) {
            e.printStackTrace(); // Log error
            return false;
        }

        return true;
    }

    public boolean addRemark(String remark, String remarkType) {
        // Condition 1: Validate remark type (it must be either "Client" or "Optometrist")
        if (!remarkType.equalsIgnoreCase("client") && !remarkType.equalsIgnoreCase("optometrist")) {
            return false;
        }

        // Condition 2: Ensure no more than 2 remarks for a prescription
        if (potRemarks.size() >= 2) {
            return false;
        }

        // Condition 3: Validate remark word count (min 6 words, max 20 words)
        String[] words = remark.split("\\s+");
        if (words.length < 6 || words.length > 20) {
            return false;
        }

        // Condition 4: The first character of the first word should be uppercase
        if (!Character.isUpperCase(words[0].charAt(0))) {
            return false;
        }

        // If all conditions are satisfied, write the remark to a file
        try (FileWriter writer = new FileWriter("review.txt", true)) {
            writer.write("Prescription ID: " + prescID + "\n");
            writer.write("Remark Type: " + remarkType + "\n");
            writer.write("Remark: " + remark + "\n");
            writer.write("-----------------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Add the remark to the list of potential remarks
        potRemarks.add(remark);

        return true;
    }
}
