package http_database;

import java.io.IOException;

import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

public class ResponseHttp {
    private Response response = null;
    private HttpResponse httpResponse = null;

    private final Log log = LogFactory.getLog(ResponseHttp.class);

    public void getResp (String url) throws Exception {

        int start = (int) System.currentTimeMillis();
        response = Request.Get(url).execute();
        httpResponse = response.returnResponse();
        //log.info("Get Http response");
        StatusLine code =  httpResponse.getStatusLine();
        String strcode = code.toString();
        int intCode = Integer.parseInt(strcode.split("\\s", 3)[1]);

        //thoi gian phan hoi
        int end = (int) System.currentTimeMillis();
        int time_response = end - start;

        //thoi gian luc truy van
        java.util.Date javaDate = new java.util.Date();
        long javaTime = javaDate.getTime();
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(javaTime);

        DatabaseConnecting r = new DatabaseConnecting("com.mysql.jdbc.Driver");
        log.debug("push to database");
        r.updateDatabases(url, sqlTimestamp, time_response, intCode);

        //return intCode;
    }

}
