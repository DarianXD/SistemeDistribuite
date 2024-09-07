package Helpers;

import beans.StudentBean;

public class StudentSerializer {
    public String Serialize(StudentBean student)
    {
        if (student == null) {
            return "<p> No student data available. </p>";
        }

        StringBuilder serialized = new StringBuilder();
        serialized.append("<ul type=\"bullet\">\n")
                .append("    <li>Nume: ").append(safeHtml(student.getNume())).append("</li>\n")
                .append("    <li>Prenume: ").append(safeHtml(student.getPrenume())).append("</li>\n")
                .append("    <li>Varsta: ").append(student.getVarsta()).append("</li>\n").append("</ul>");

        return serialized.toString();
    }

    private String safeHtml(String input)
    {
        if (input == null)
        {
            return "N/A";
        }
        return input;
    }
}
