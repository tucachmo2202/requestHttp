import java.io.IOException;

import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

public class ResponseHttp {
    private Response response = null;
    private HttpResponse httpResponse = null;

    public void getResp (String url) throws ClientProtocolException, IOException, Exception {

        int start = (int) System.currentTimeMillis();
        response = Request.Get(url).execute();
        httpResponse = response.returnResponse();
        StatusLine code =  httpResponse.getStatusLine();
        String strcode = code.toString();
        int intCode = Integer.parseInt(strcode.split("\\s", 3)[1]);

        int end = (int) System.currentTimeMillis();
        int time_response = end - start;
        java.util.Date javaDate = new java.util.Date();
        long javaTime = javaDate.getTime();
        java.sql.Date sqlDate = new java.sql.Date(javaTime);

        Database r = new Database();
        r.updateDatabases(url.toString(), sqlDate, time_response, intCode);


        //return intCode;
    }

}
