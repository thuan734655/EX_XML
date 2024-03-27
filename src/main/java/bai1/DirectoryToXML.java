package bai1;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import javax.xml.stream.*;
import java.io.*;

public class DirectoryToXML {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter directory path: ");
            String directoryPath = reader.readLine();
            Path path = Paths.get(directoryPath);

            if (Files.isDirectory(path)) {
                try {
                    XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
                    XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileWriter("directory_tree.xml"));
                    xmlStreamWriter.writeStartDocument();
                    xmlStreamWriter.writeStartElement(path.getFileName().toString());
                    listDirectory(path, xmlStreamWriter);
                    xmlStreamWriter.writeEndElement();
                    xmlStreamWriter.writeEndDocument();
                    xmlStreamWriter.close();
                    System.out.println("XML file created successfully.");
                } catch (XMLStreamException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid directory path.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listDirectory(Path directoryPath, XMLStreamWriter xmlStreamWriter) throws IOException, XMLStreamException {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath)) {
            for (Path path : directoryStream) {
                if (Files.isDirectory(path)) {
                    xmlStreamWriter.writeStartElement(path.getFileName().toString());
                    listDirectory(path, xmlStreamWriter);
                    xmlStreamWriter.writeEndElement();
                } else if (Files.isRegularFile(path)) {
                    xmlStreamWriter.writeStartElement("file");
                    xmlStreamWriter.writeCharacters(path.getFileName().toString());
                    xmlStreamWriter.writeEndElement();
                }
            }
        }
    }
}
