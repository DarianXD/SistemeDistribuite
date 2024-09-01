package beans;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private List<StudentBean> studentList = new ArrayList<>();

    public List<StudentBean> getStudentList()
    {
        return studentList;
    }
    private int mama = 0;
    public void setStudentList(List<StudentBean> studentList)
    {
        this.studentList = studentList;
    }
}
