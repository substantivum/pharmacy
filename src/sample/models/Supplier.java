package sample.models;

public class Supplier {
    private String fname;
    private String lname;
    private String company;

    public Supplier(String fname, String lname, String company) {
        this.fname = fname;
        this.lname = lname;
        this.company = company;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
