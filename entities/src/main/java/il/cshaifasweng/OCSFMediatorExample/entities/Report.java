package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import javax.xml.namespace.QName;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Entity
@Table(name = "reports")
public class Report implements Serializable{
    private static final long serialVersionUID= -4594210857638113035L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private String start;
    private String end;
    private String Branch_Name;
    private String Branch_Manager;

    public Report(){

    }

    public String getBranch_Manager() {
        return Branch_Manager;
    }

    public void setBranch_Manager(String branch_Manager) {
        Branch_Manager = branch_Manager;
    }

    public String getBranch_Name() {
        return Branch_Name;
    }

    public void setBranch_Name(String branch_Name) {
        Branch_Name = branch_Name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public Report(String type, String start, String end,String Branch_Name,String Branch_Manager) {
        this.type = type;
        this.start = start;
        this.end = end;
        this.Branch_Manager=Branch_Manager;
        this.Branch_Name=Branch_Name;
    }

}
