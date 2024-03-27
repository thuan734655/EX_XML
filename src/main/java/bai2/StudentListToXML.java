package bai2;

import java.io.FileWriter;
import java.io.IOException;
import javax.xml.stream.*;

public class StudentListToXML {
    public static void main(String[] args) {

        Student[] students = {
                new Student("hoang", 20, 3.8),
                new Student("hoa", 21, 3.9),
                new Student("hong", 22, 3.6)
        };


        try {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileWriter("student_list.xml"));
            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement("StudentList");
            for (Student student : students) {
                xmlStreamWriter.writeStartElement("Student");
                xmlStreamWriter.writeAttribute("Name", student.getName());
                xmlStreamWriter.writeAttribute("Age", Integer.toString(student.getAge()));
                xmlStreamWriter.writeAttribute("GPA", Double.toString(student.getGpa()));
                xmlStreamWriter.writeEndElement();
            }
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.close();
            System.out.println("XML file created successfully.");
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }
}


