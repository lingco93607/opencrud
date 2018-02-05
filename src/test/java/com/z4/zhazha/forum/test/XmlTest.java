package com.z4.zhazha.forum.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.z4.zhazha.forum.util.PushUtil;

public class XmlTest {
	private Document document;
	Element root;
	Scanner sc = new Scanner(System.in);
	String[] atmp = null;
	String tmp = null;
	
	public void init() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			this.document = builder.newDocument();
			root = this.document.createElement("Mission");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void createXml (String fileName) {
		
		this.document.appendChild(root);
		
		System.out.println("现在开始创建任务。\n");
		
		setMissionName();
		
		setMissionDes();
		
		setMissionTools();
		
		setMissionNPCs();		
		
		setMissionSteps();
		
		setMissionAward();
		
		sc.close();
		System.out.println("您已完成任务设置！");
		
		Element employee = this.document.createElement("employee");
		Element name = this.document.createElement("name");
		name.appendChild(this.document.createTextNode("xintt"));
		employee.appendChild(name);
		
		Element sex = this.document.createElement("sex");
		sex.appendChild(this.document.createTextNode("f"));
		employee.appendChild(sex);
		
		Element age = this.document.createElement("age");
		age.appendChild(this.document.createTextNode("27"));
		employee.appendChild(age);
		
		root.appendChild(employee);
		TransformerFactory tf = TransformerFactory.newInstance();
		try {
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(document);
			transformer.setOutputProperty(OutputKeys.ENCODING, "gb2312");
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");         //设置换行
			PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
			System.out.println("生成XML文件成功");
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {			
			e.printStackTrace();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
		
	}
	
	private void setMissionAward() {
		// TODO Auto-generated method stub
		
	}

	private void setMissionSteps() {
		// TODO Auto-generated method stub
		
	}

	private void setMissionNPCs() {
		// TODO Auto-generated method stub
		
	}

	private void setMissionTools() {
		Element tools = this.document.createElement("tools");
		System.out.println("请选择任务所需的道具：1，自行车；2，登山鞋；");
		atmp = sc.nextLine().split(" ") ;
		for(int i=0; i<atmp.length; i++) {
			Element tt;
			switch (atmp[i]) {
			case "1":
				tt = this.document.createElement("tool" + Integer.toString(i+1));
				tt.appendChild(this.document.createTextNode("自行车"));
				tools.appendChild(tt);
				root.appendChild(tools);
				break;
			case "2":
				tt = this.document.createElement("tool" + Integer.toString(i+1));
				tt.appendChild(this.document.createTextNode("登山鞋"));
				tools.appendChild(tt);
				root.appendChild(tools);
				break;
			}
		}
		
	}

	public void parserXml(String fileName) {
		
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			try {
				db = dbf.newDocumentBuilder();
			
			Document document = db.parse(fileName);
			//Document document = this.document;
			Element element = (Element) document.getDocumentElement();
			System.out.println(element.getNodeName());
			printNode(element);
			
			NodeList employees = document.getChildNodes();
			for (int i=0; i<employees.getLength(); i++) {
				printNode((Element) employees.item(i));
				/*Node node = employees.item(i);
				NodeList employeeMeta = node.getChildNodes();
				for (int k=0; k<employeeMeta.getLength(); k++) {
					if( employeeMeta instanceof Element) {
						System.out.println(employeeMeta.item(k).getNodeName() + ":" + employeeMeta.item(k).getTextContent());    //employeeMeta.item(k).getNodeValue()
					}*/
				
				/*Node employee = employees.item(i);
				NodeList employeeInfo = employee.getChildNodes();
				for (int j=0; j<employeeInfo.getLength(); j++) {
					Node node = employeeInfo.item(j);
					NodeList employeeMeta = node.getChildNodes();
					for (int k=0; k<employeeMeta.getLength(); k++) {
						if( employeeMeta instanceof Element) {
							System.out.println(employeeMeta.item(k).getNodeName() + ":" + employeeMeta.item(k).getTextContent());
						}
						
					}
				}*/
			}
			System.out.println("解析完毕");
	}
			catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}/*catch (ParserConfigurationException e) {			
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */ catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	private void setMissionName() {
		System.out.println("请设置任务名称:");
		Element name = this.document.createElement("Name");
		name.appendChild(this.document.createTextNode(sc.nextLine()));
		root.appendChild(name);
	}
	
	private void setMissionDes() {
		System.out.println("请进行任务描述：");
		Element name = this.document.createElement("Description");
		name.appendChild(this.document.createTextNode(sc.nextLine()));
		root.appendChild(name);
	}
	
	public void printNode(Node element) {
		NodeList childNodes =  element.getChildNodes();
		if (childNodes == null || childNodes.getLength() == 0) {
			System.out.println(element.getNodeName() + ":" +element.getNodeValue());
		} else {
			for (int i=0; i<childNodes.getLength(); i++) {
				if(childNodes.item(i) instanceof Element) {
					Node child = childNodes.item(i);
					System.out.println(child.getNodeName() + ":" + child.getTextContent());
					printNode(child);
				}
				
			}
		}
	}
	
	@Test
	public  void test() {
		String fname = "/home/hadoop/Documents/xml/test0926.xml";
		init();
		createXml(fname);
		parserXml(fname);
	}
	
	//@Test
	public void testPush() {
    	PushUtil.pushToUser("test1@server109", "您好，这是一条推送消息");
    }

}
