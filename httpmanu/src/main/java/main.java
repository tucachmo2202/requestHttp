
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

//import org.apache.commons.io.IOUtils;
//import org.apache.http.Header;
//import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.fluent.Request;
//import org.apache.http.client.fluent.Response;

public class main {
    public static void main(String[] args) throws ClientProtocolException, IOException, Exception {
//        Response response = Request.Get("https://www.google.com.vn/").execute();
//        HttpResponse httpResponse = response.returnResponse();
//
//        System.out.println("Protocol: " + httpResponse.getProtocolVersion());
//        System.out.println("Status:" + httpResponse.getStatusLine().toString());
//        System.out.println("Content type:" + httpResponse.getEntity().getContentType());
//        System.out.println("Locale:" + httpResponse.getLocale());
//        System.out.println("Headers:");
//        for(Header header : httpResponse.getAllHeaders()) {
//            System.out.println("          " + header.getName()+": " + header.getValue());
//        }
//        System.out.println("Content:");
//        String content = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
////    String content = response.returnContent().asString();
//        System.out.println(content);

//        System.out.println("Test ham in ra Code HTTP response");
//        GetRes s = new GetRes();
//        String str = "https://www.google.com.vn/";
//        URL u = new URL(str);
//        int code = s.getResp(u);
//        System.out.println(code);


        Database r = new Database();
        GetRes g = new GetRes();
        ArrayList<String> list = r.readUrl();
        int i =0;
        for (i=0; i<list.size(); i++){
            String url = list.get(i);
            g.getResp(url);
        }

    }
}