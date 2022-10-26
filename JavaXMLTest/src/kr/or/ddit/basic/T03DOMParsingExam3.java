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
public class T03DOMParsingExam3 {
   
   public void parse() throws Exception {
      
      String serviceKey = "CPjUVtNUhoQa%2BDJfENEs3ANxQ0HLahrdOvGAE40dZ5F%2BeIDOm%2Bw9l%2Blim2G6EnspsTApZ8O0%2FFyEu2dcmlCOVQ%3D%3D";
      String numOfRows = "5";
      String pageNo ="1";
      String from_time = "0000";
      String to_time = "2400";
      String lang = "K";
      String type="xml";
      //String remark = URLEncoder.encode(remark);
      

      URL url = new URL("http://apis.data.go.kr/B551177/StatusOfPassengerWorldWeatherInfo/getPassengerArrivalsWorldWeather?serviceKey="
            + serviceKey + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo 
            + "&from_time=" + from_time + "&to_time=" + to_time + "&lang=" + lang 
            + "&type=" + type);
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
      if(resultCode.equals("00")) {

         NodeList rowNodeList = root.getElementsByTagName("item");
         for(int i=0;i<rowNodeList.getLength(); i++) {
            Element element = (Element) rowNodeList.item(i);
            
            String airline = element.getElementsByTagName("airline").item(0).getTextContent();
            String airport = element.getElementsByTagName("airport").item(0).getTextContent();
            String airportCode = element.getElementsByTagName("airportCode").item(0).getTextContent();
            String carousel = element.getElementsByTagName("carousel").item(0).getTextContent();
            String estimatedDateTime = element.getElementsByTagName("estimatedDateTime").item(0).getTextContent();
            String exitnumber = element.getElementsByTagName("exitnumber").item(0).getTextContent();
            String flightId = element.getElementsByTagName("flightId").item(0).getTextContent();
            String gatenumber = element.getElementsByTagName("gatenumber").item(0).getTextContent();
            //remark = element.getElementsByTagName("remark").item(0).getTextContent();
            String scheduleDateTime = element.getElementsByTagName("scheduleDateTime").item(0).getTextContent();
            String terminalid = element.getElementsByTagName("terminalid").item(0).getTextContent();
            String wimage = element.getElementsByTagName("wimage").item(0).getTextContent();
            String wind = element.getElementsByTagName("wind").item(0).getTextContent();
            String yoil = element.getElementsByTagName("yoil").item(0).getTextContent();
            
            
            System.out.printf("%3s %8s %8s %8s %8s %8s %8s %8s %8s %8s %8s\n", airline, airport, airportCode, carousel, 
                  estimatedDateTime, exitnumber, flightId, gatenumber, scheduleDateTime, terminalid, wind);
            //System.out.println(remark);
            System.out.println(wimage);
            System.out.println(yoil);
            System.out.println("--------------------------------------------------------------------");
            
         }
      }
   }
   
   public static void main(String[] args) throws Exception {
      new T03DOMParsingExam3().parse();
   }
}