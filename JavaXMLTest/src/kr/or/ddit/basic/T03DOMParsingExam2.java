package kr.or.ddit.basic;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

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

		String serviceKey = "CPjUVtNUhoQa%2BDJfENEs3ANxQ0HLahrdOvGAE40dZ5F%2BeIDOm%2Bw9l%2Blim2G6EnspsTApZ8O0%2FFyEu2dcmlCOVQ%3D%3D";
		String pageNo = "1";
		String numOfRows = "10";
		String type = "xml";
		String essntl_item_name = "라";
		essntl_item_name = URLEncoder.encode(essntl_item_name);
		System.out.println("==> " + essntl_item_name);
      
        URL url = new URL( "http://apis.data.go.kr/1471000/MdcEssntlItemInfoService03/getMdcEssntlItemList03?serviceKey="+ serviceKey
              + "&pageNo="+ pageNo + "&numOfRows="+numOfRows +"&type=" + type
              + "&essntl_item_name=" +  essntl_item_name );
      
//      System.out.println(url.toString());
      // XML 문서를 생성하기 위한 DocumentBuilder객체 생성하기
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = dbf.newDocumentBuilder();
      
      // Document 객체 생성
      Document document = builder.parse(url.toString());
      
      // ROOT 엘리먼트
      Element root = document.getDocumentElement();
      System.out.println("루트엘리먼트 : " + root.getTagName() );
      System.out.println("---------------------------시작 -----------------------------------------");
      
      String resultCode = root.getElementsByTagName("resultCode").item(0).getTextContent();
      if("00".equals(resultCode)) {

         NodeList rowNodeList = root.getElementsByTagName("item");
         for(int i=0;i<rowNodeList.getLength(); i++) {
            Element element = (Element) rowNodeList.item(i);
            
            String essntlItemName = element.getElementsByTagName("ESSNTL_ITEM_NAME").item(0).getTextContent();
            String medEfficacy = element.getElementsByTagName("MED_EFFICACY").item(0).getTextContent();
            String appointDate = element.getElementsByTagName("APPOINT_DATE").item(0).getTextContent();
            
            System.out.printf("%3s %8s %10s \n", essntlItemName, medEfficacy, appointDate);
            System.out.println("--------------------------------------------------------------------");
            
         }
         
      }
      

      
      
      // 전체 레시피 수를 가져오기
//      String totalCnt = root.getElementsByTagName("totalCnt").item(0).getTextContent();
//      endIdx = totalCnt; //endIdx에 토탈cnt로 만들어주기
//      
//      url = new URL("http://211.237.50.150:7080/openapi/"+ apiKey
//              + "/xml/"+ svcKey + "/"+startIdx +"/" + endIdx
//              +"?RECIPE_ID=" +  recipeId);
//      
//      document = builder.parse(url.toString());
//      
//      root = document.getDocumentElement();
//      
//      String code = root.getElementsByTagName("code").item(0).getTextContent();
//            
//      if(code.equals("INFO-000")) {
//         
//         NodeList rowNodeList = root.getElementsByTagName("row");
//         
//         for(int i=0;i<rowNodeList.getLength(); i++) {
//            
//            Element element = (Element) rowNodeList.item(i);
//            
//            String rowNum = element.getElementsByTagName("ROW_NUM").item(0).getTextContent();
//            String irdntNm = element.getElementsByTagName("IRDNT_NM").item(0).getTextContent();
//            String irdntCpcty = element.getElementsByTagName("IRDNT_CPCTY").item(0).getTextContent();
//            String irdntTyNm = element.getElementsByTagName("IRDNT_TY_NM").item(0).getTextContent();
//            
//            System.out.printf("%3s %8s %10s %10s %8s\n", rowNum, recipeId, irdntTyNm, irdntNm, irdntCpcty );
//            System.out.println("--------------------------------------------------------------------");
//            
//            
//         }
//      }
   }
   
   public static void main(String[] args) throws Exception {
      new T03DOMParsingExam2().parse();
   }
}