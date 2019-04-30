package in.co.jmkssj.www.jmkssj.Model;

public class Volunteers_Details_C {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFormfillingdate() {
        return formfillingdate;
    }

    public void setFormfillingdate(String formfillingdate) {
        this.formfillingdate = formfillingdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    String  name,
            email,
            phone,
            address,
            formfillingdate,
            gender,
            dateOfBirth;

    public Volunteers_Details_C(String name, String email, String phone, String address, String formfillingdate, String gender, String dateOfBirth) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.formfillingdate = formfillingdate;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public Volunteers_Details_C() {
    }
}
