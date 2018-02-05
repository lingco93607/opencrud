package com.z4.zhazha.forum.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

import org.junit.Test;

import com.z4.jl.pojo.Classroom;
import com.z4.jl.pojo.Mission;
import com.z4.jl.pojo.Student;

public class JaxbTest {
	
	String fileName =  "/home/hadoop/Documents/test.xml";
	
	//@Test
	public void beanToXML() {
		Classroom classroom = new Classroom(1, "软件工程",4);
		Student student = new Student(101,"张三", 22, classroom);
		
		try {
			JAXBContext context = JAXBContext.newInstance(Student.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(student, System.out);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void XMLToBean() {
		/*String str = null;
		StringBuffer sb = new StringBuffer();
		InputStreamReader reader;
		try {
			reader = new InputStreamReader(new FileInputStream(fileName), "GBK");
			BufferedReader br = new BufferedReader(reader);
			int i = 0;
			while ((str = br.readLine()) != null) {
				if (str.length() > 1 && i >= 0) {
					sb.append(str);
					sb.append("\n");				
				}
				i++;
			}
			br.close();
		} catch ( IOException e) {
			e.printStackTrace();
		}
		String xmlString = sb.toString();
		
		try {
			JAXBContext context = JAXBContext.newInstance(Mission.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Mission mission = (Mission) unmarshaller.unmarshal(new StringReader(xmlString));
			
			System.out.println(mission.getName());
			//System.out.println(mission.getMissionSteps().get(0).toString());
		} catch (JAXBException e) {
			e.printStackTrace();
		}*/
		
		//String xmlStr = "<?xml version="1.0" encoding="UTF-8" standalone="yes"?><student><age>22</age><classroom><grade>4</grade><id>1</id><name>软件工程</name></classroom><id>101</id><name>张三</name></student>"; 
		 String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><student><age>22</age><classroom><grade>4</grade><id>1</id><name>软件工程</name></classroom><id>101</id><name>张三</name></student>";  
		//String xmlStr = xmlToString("/home/hadoop/Documents/test4.xml");
		try {  
            JAXBContext context = JAXBContext.newInstance(Student.class);  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            Student student = (Student)unmarshaller.unmarshal(new StringReader(xmlStr));  
            System.out.println(student.getAge());  
            System.out.println(student.getClassroom().getName());  
        } catch (JAXBException e) {  
            e.printStackTrace();  
        }  
		
		
	}

	public String xmlToString(String fileName) {
		String str = null;
		StringBuffer sb = new StringBuffer();
		InputStreamReader reader;
		try {
			reader = new InputStreamReader(new FileInputStream(fileName), "GBK");
			BufferedReader br = new BufferedReader(reader);
			int i = 0;
			while ((str = br.readLine()) != null) {
				if (str.length() > 1 && i >= 0) {
					sb.append(str + "\n");		
				}
				i++;
			}
			br.close();
		} catch ( IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
