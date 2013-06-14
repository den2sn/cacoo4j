/*
 * Copyright 2013 den2sn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cacoo4j;

import cacoo4j.http.HttpUtil;
import cacoo4j.http.ResponseException;
import cacoo4j.oauth.AccessToken;
import cacoo4j.oauth.OAuthUtil;
import cacoo4j.xml.handler.ChatMessagesHandler;
import cacoo4j.xml.handler.CommentHandler;
import cacoo4j.xml.handler.DiagramHandler;
import cacoo4j.xml.handler.DiagramsHandler;
import cacoo4j.xml.handler.FoldersHandler;
import cacoo4j.xml.handler.LicenseHandler;
import cacoo4j.xml.handler.UserHandler;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author den2sn
 */
public class Cacoo {
    private static final String API_URL = "https://cacoo.com/api/v1/";
    private static final String DIAGRAMS_URL = API_URL + "diagrams.xml";
    private static final String DIAGRAM_URL = API_URL + "diagrams/%s.xml";
    private static final String CHAT_MESSAGES_URL = API_URL + "diagrams/%s/chat/messages.xml";
    private static final String FOLDERS_URL = API_URL + "folders.xml";
    private static final String USER_URL = API_URL + "users/%s.xml";
    private static final String ACCOUNT_URL = API_URL + "account.xml";
    private static final String LICENSE_URL = API_URL + "account/license.xml";
    private static final String CREATE_DIAGRAM_URL = API_URL + "diagrams/create.xml";
    private static final String COPY_DIAGRAM_URL = API_URL + "diagrams/%s/copy.xml";
    private static final String DELETE_DIAGRAM_URL = API_URL + "diagrams/%s/delete.xml";
    private static final String POST_COMMENT_URL = API_URL + "diagrams/%s/comments/post.xml";
    private static final String DIAGRAM_CONTENTS_URL = API_URL + "diagrams/%s/contents.xml";
    
    private String apiKey;
    private String consumerKey;
    private String consumerSecret;
    private AccessToken accessToken;
    private OAuthUtil oAuthUtil;

    public Cacoo() {
    }
    public Cacoo(String apiKey) {
        this.apiKey = apiKey;
    }
    public Cacoo(String consumerKey, String consumerSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
    }
    public AccessToken retrieve() {
        try {
            String url = retrieveRequestToken();

            System.out.println("Open the following URL and grant access to your account:");
            System.out.println(url);
            System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String pin = br.readLine();

            retrieveAccessToken(pin);

            System.out.println("Token : " + accessToken.getToken());
            System.out.println("TokenSecret : " + accessToken.getTokenSecret());

            return accessToken;
        } catch (Exception e) {
            throw new CacooException("API access error.", e);
        }
    }

    public String retrieveRequestToken() {
        createOAuthUtil();
        return oAuthUtil.retrieveRequestToken();
    }
    public AccessToken retrieveAccessToken(String pin) {
        if (oAuthUtil != null) {
            accessToken = oAuthUtil.getToken(pin);
        }
        return accessToken;
    }
    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }
    public AccessToken getAccessToken() {
        return accessToken;
    }
    public Diagrams getDiagrams() {
        return getDiagrams(0, 0);
    }
    public Diagrams getDiagrams(int limit, int offset) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            if (apiKey != null) params.put("apiKey", apiKey);
            if (limit != 0) params.put("limit", String.valueOf(limit));
            if (offset != 0) params.put("offset", String.valueOf(offset));
            String query = HttpUtil.createQueryString(params);

            DiagramsHandler handler = new DiagramsHandler();
            parseResponse(DIAGRAMS_URL + query, handler);

            return handler.getDiagrams();
        } catch(Exception e) {
            throw new CacooException("API access error.", e);
        }
    }

    public Diagram getDiagram(String diagramId) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            if (apiKey != null) params.put("apiKey", apiKey);
            String query = HttpUtil.createQueryString(params);

            DiagramHandler handler = new DiagramHandler();
            parseResponse(String.format(DIAGRAM_URL, diagramId) + query, handler);

            return handler.getDiagram();
        } catch(ResponseException e) {
            if (e.getResponseCode() == 404) {
                throw new DiagramNotFoundException(e);
            } else {
                throw new CacooException("API access error.", e);
            }
        } catch(Exception e) {
            throw new CacooException("API access error.", e);
        }
    }

    public Messages getChatMessages(String diagramId) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            if (apiKey != null) params.put("apiKey", apiKey);
            String query = HttpUtil.createQueryString(params);

            ChatMessagesHandler handler = new ChatMessagesHandler();
            parseResponse(String.format(CHAT_MESSAGES_URL, diagramId) + query, handler);

            return handler.getMessages();
        } catch(ResponseException e) {
            if (e.getResponseCode() == 404) {
                throw new DiagramNotFoundException(e);
            } else {
                throw new CacooException("API access error.", e);
            }
        } catch(Exception e) {
            throw new CacooException("API access error.", e);
        }
    }

    public Folders getFolders() {
        try {
            Map<String, String> params = new HashMap<String, String>();
            if (apiKey != null) params.put("apiKey", apiKey);
            String query = HttpUtil.createQueryString(params);

            FoldersHandler handler = new FoldersHandler();
            parseResponse(FOLDERS_URL + query, handler);

            return handler.getFolders();
        } catch(Exception e) {
            throw new CacooException("API access error.", e);
        }
    }

    public CacooUser getUser(String name) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            if (apiKey != null) params.put("apiKey", apiKey);
            String query = HttpUtil.createQueryString(params);

            UserHandler handler = new UserHandler();
            parseResponse(String.format(USER_URL, name) + query, handler);

            return handler.getUser();
        } catch(ResponseException e) {
            if (e.getResponseCode() == 404) {
                throw new UserNotFoundException(e);
            } else {
                throw new CacooException("API access error.", e);
            }
        } catch(Exception e) {
            throw new CacooException("API access error.", e);
        }
    }

    public CacooUser getAccount() {
        try {
            Map<String, String> params = new HashMap<String, String>();
            if (apiKey != null) params.put("apiKey", apiKey);
            String query = HttpUtil.createQueryString(params);

            UserHandler handler = new UserHandler();
            parseResponse(ACCOUNT_URL + query, handler);

            return handler.getUser();
        } catch(Exception e) {
            throw new CacooException("API access error.", e);
        }
    }

    public License getLicense() {
        try {
            Map<String, String> params = new HashMap<String, String>();
            if (apiKey != null) params.put("apiKey", apiKey);
            String query = HttpUtil.createQueryString(params);

            LicenseHandler handler = new LicenseHandler();
            parseResponse(LICENSE_URL + query, handler);

            return handler.getLicense();
        } catch(Exception e) {
            throw new CacooException("API access error.", e);
        }
    }

    public Diagram createDiagram(CreateDiagramRequest request) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            if (apiKey != null) params.put("apiKey", apiKey);
            if (request != null) {
                if (request.getFolderId() != 0) params.put("folderId", String.valueOf(request.getFolderId()));
                if (request.getTitle() != null) params.put("title", request.getTitle());
                if (request.getDescription() != null) params.put("description", request.getDescription());
                if (request.getSecurity() != null) params.put("security", request.getSecurity());
            }
            // TODO POSTがうまく動作しないので暫定でGETを使用する
            String query = HttpUtil.createQueryString(params);

            DiagramHandler handler = new DiagramHandler();
            parseResponse(CREATE_DIAGRAM_URL + query, handler);

            return handler.getDiagram();
        } catch(ResponseException e) {
            if (e.getResponseCode() == 403) {
                throw new LimitException(e);
            } else if (e.getResponseCode() == 404) {
                throw new FolderNotFoundException(e);
            } else {
                throw new CacooException("API access error.", e);
            }
        } catch(Exception e) {
            throw new CacooException("API access error.", e);
        }
    }

    public Diagram copyDiagram(String diagramId) {
        return copyDiagram(diagramId, null);
    }

    public Diagram copyDiagram(String diagramId, CopyDiagramRequest request) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            if (apiKey != null) params.put("apiKey", apiKey);
            if (request != null) {
                if (request.getTitle() != null) params.put("title", request.getTitle());
                if (request.getDescription() != null) params.put("description", request.getDescription());
                if (request.getSecurity() != null) params.put("security", request.getSecurity());
            }
            // TODO POSTがうまく動作しないので暫定でGETを使用する
            String query = HttpUtil.createQueryString(params);

            DiagramHandler handler = new DiagramHandler();
            parseResponse(String.format(COPY_DIAGRAM_URL, diagramId) + query, handler);

            return handler.getDiagram();
        } catch(ResponseException e) {
            if (e.getResponseCode() == 400) {
                throw new ParameterException();
            } else if (e.getResponseCode() == 403) {
                throw new LimitException(e);
            } else if (e.getResponseCode() == 404) {
                throw new DiagramNotFoundException(e);
            } else {
                throw new CacooException("API access error.", e);
            }
        } catch(Exception e) {
            throw new CacooException("API access error.", e);
        }
    }

    public void deleteDiagram(String diagramId) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            if (apiKey != null) params.put("apiKey", apiKey);
            String query = HttpUtil.createQueryString(params);

            execRequest(String.format(DELETE_DIAGRAM_URL, diagramId) + query);
        } catch(ResponseException e) {
            if (e.getResponseCode() == 404) {
                throw new DiagramNotFoundException(e);
            } else {
                throw new CacooException("API access error.", e);
            }
        } catch(Exception e) {
            throw new CacooException("API access error.", e);
        }
    }

    public Comment postComment(String diagramId, String content) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            if (apiKey != null) params.put("apiKey", apiKey);
            if (content != null) params.put("content", content);
            // TODO POSTがうまく動作しないので暫定でGETを使用する
            String query = HttpUtil.createQueryString(params);

            CommentHandler handler = new CommentHandler();
            parseResponse(String.format(POST_COMMENT_URL, diagramId) + query, handler);

            return handler.getComment();
        } catch(ResponseException e) {
            if (e.getResponseCode() == 400) {
                throw new ParameterException();
            } else if (e.getResponseCode() == 404) {
                throw new DiagramNotFoundException(e);
            } else {
                throw new CacooException("API access error.", e);
            }
        } catch(Exception e) {
            throw new CacooException("API access error.", e);
        }
    }

    public String getDiagramContents(String diagramId) {
        return getDiagramContents(diagramId, null);
    }

    public String getDiagramContents(String diagramId, String returnValues) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            if (apiKey != null) params.put("apiKey", apiKey);
            if (returnValues != null) params.put("returnValues", returnValues);
            String query = HttpUtil.createQueryString(params);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            output(String.format(DIAGRAM_CONTENTS_URL, diagramId) + query, out);

            return out.toString("UTF-8");
        } catch(ResponseException e) {
            if (e.getResponseCode() == 400) {
                throw new ParameterException();
            } else if (e.getResponseCode() == 404) {
                throw new DiagramNotFoundException(e);
            } else {
                throw new CacooException("API access error.", e);
            }
        } catch(Exception e) {
            throw new CacooException("API access error.", e);
        }
    }

    public void outputImage(Diagram diagram, OutputStream out) {
        outputImage(diagram, out, 0, 0);
    }

    public void outputImage(Sheet sheet, OutputStream out) {
        outputImage(sheet, out, 0, 0);
    }

    public void outputImage(Diagram diagram, OutputStream out, int width, int height) {
        outputImage(diagram.getImageUrlForApi(), out, width, height);
    }

    public void outputImage(Sheet sheet, OutputStream out, int width, int height) {
        outputImage(sheet.getImageUrlForApi(), out, width, height);
    }

    private void outputImage(String urlStr, OutputStream out, int width, int height) {
        Map<String, String> params = new HashMap<String, String>();
        if (apiKey != null) params.put("apiKey", apiKey);
        if (width != 0) params.put("width", String.valueOf(width));
        if (height != 0) params.put("height", String.valueOf(height));
        String query = HttpUtil.createQueryString(params);

        output(urlStr + query, out);
    }
    
    private void output(String urlStr, OutputStream out) {
        try {

            URL url = new URL(urlStr);
            HttpURLConnection con = null;
            try {
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                if (accessToken != null) {
                    createOAuthUtil();
                    oAuthUtil.sign(accessToken, con);
                }

                con.connect();

                if (con.getResponseCode() != 200) {
                    throw new ResponseException(con.getResponseCode(), con.getResponseMessage());
                }

                InputStream in = con.getInputStream();
                byte[] b = new byte[2048];
                int len;
                while((len = in.read(b)) != -1) {
                    out.write(b, 0, len);
                }

                out.flush();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
            }

        } catch(Exception e) {
            throw new CacooException("API access error.", e);
        }
    }

    private void execRequest(String urlStr) throws Exception {
        parseResponse(urlStr, null, null);
    }

    private void parseResponse(String urlStr, DefaultHandler handler) throws Exception {
        parseResponse(urlStr, handler, null);
    }

    private void parseResponse(String urlStr, DefaultHandler handler, Map<String, String> postParam) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
            if (accessToken != null) {
                createOAuthUtil();
                oAuthUtil.sign(accessToken, con);
            }
            if (postParam == null) {
                con.setRequestMethod("GET");
            } else {
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                PrintWriter out = new PrintWriter(con.getOutputStream());
                out.print(HttpUtil.createPostParams(postParam));
                out.flush();
                out.close();
            }

            con.connect();

            if (con.getResponseCode() != 200) {
                throw new ResponseException(con.getResponseCode(), con.getResponseMessage());
            }

            if (handler != null) {
                SAXParserFactory parserFactory = SAXParserFactory.newInstance();
                SAXParser parser = parserFactory.newSAXParser();
                parser.parse(con.getInputStream(), handler);
            } else {
                // 読み捨て
                InputStream in = con.getInputStream();
                byte[] bytes = new byte[1024];
                while (in.read(bytes) != -1) {
                }
            }
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    private void createOAuthUtil() {
        if (oAuthUtil == null) oAuthUtil = new OAuthUtil(consumerKey, consumerSecret);
    }
}
