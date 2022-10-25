package kr.or.ddit.basic;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * XML DOM을 이용한 XML문서 파싱 예제(레시피정보API)
 * @author PC-06
 *
 */
public class T03DOMParsingExam2 {
	
	public void parse() throws Exception {
		
		String serviceKey = "CPjUVtNUhoQa%2BDJfENEs3ANxQ0HLahrdOvGAE40dZ5F%2BeIDOm%2Bw9l%2Blim2G6EnspsTApZ8O0%2FFyEu2dcmlCOVQ%3D%3D"; // 개인별 발급.
		int pageNo = 1;  	// 레시피 재료 시작 순번
  		int numOfRows = 3;		// 레시피 재료 종료 순번
  		String type = "xml";	// 래시피가 궁금한 음식 ID
  		String essntlItemName = "요오드화칼륨 정제";	// 래시피가 궁금한 음식 ID
  		int totalCount = 5;

  		URL url = new URL("https://apis.data.go.kr/1471000/MdcEssntlItemInfoService03/getMdcEssntlItemList03?serviceKey=CPjUVtNUhoQa%2BDJfENEs3ANxQ0HLahrdOvGAE40dZ5F%2BeIDOm%2Bw9l%2Blim2G6EnspsTApZ8O0%2FFyEu2dcmlCOVQ%3D%3D&pageNo=5&numOfRows=3&type=xml");
  		
		// System.out.println(url.toString());
		// XML 문서를 생성하기 위한 DocumentBuilder객체 생성하기
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();

		// Document 객체 생성
		Document document = builder.parse(url.toString());
		
		// ROOT 엘리먼트
		Element root = document.getDocumentElement();
		System.out.println("루트엘리먼트 : " + root.getTagName());
		
		// 전체 레시피 수를 가져오기

		
		url = new URL("https://apis.data.go.kr/1471000/MdcEssntlItemInfoService03/getMdcEssntlItemList03?serviceKey=CPjUVtNUhoQa%2BDJfENEs3ANxQ0HLahrdOvGAE40dZ5F%2BeIDOm%2Bw9l%2Blim2G6EnspsTApZ8O0%2FFyEu2dcmlCOVQ%3D%3D&pageNo=5&numOfRows=3&type=xml");
		
		document = builder.parse(url.toString());
		
		root = document.getDocumentElement();
		
		String code = root.getElementsByTagName("resultCode").item(0).getTextContent();
				
		if(code.equals("00")) {
			
			NodeList rowNodeList = root.getElementsByTagName("item");
			
			for(int i=0;i<rowNodeList.getLength(); i++) {
				
				Element element = (Element) rowNodeList.item(i);
				
				String rowNum = element.getElementsByTagName("ESSNTL_ITEM_NAME").item(0).getTextContent();
				String irdntNm = element.getElementsByTagName("MED_EFFICACY").item(0).getTextContent();
				String irdntCpcty = element.getElementsByTagName("APPOINT_DATE").item(0).getTextContent();
				
				System.out.printf("%3s %8s %10s %10s %8s\n", rowNum,irdntNm,irdntCpcty );
				System.out.println("--------------------------------------------------------------------");
				
				
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		new T03DOMParsingExam2().parse();
	}
}
