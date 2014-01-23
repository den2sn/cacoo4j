#Cacoo4J

Cacoo4J is a Cacoo API binding library for the Java language licensed under Apache License 2.0.

##Install

###Maven

    <dependency>
        <groupId>org.cacoo4j</groupId>
        <artifactId>cacoo4j</artifactId>
        <version>1.0.1</version>
    </dependency>

###Download

https://github.com/den2sn/cacoo4j/releases/download/1.0.1/cacoo4j.zip

##Usage

###Authentication

####API Key

    Cacoo c = new Cacoo("[API Key]");

You can make an API key here.
https://cacoo.com/profile/api

####OAuth

    Cacoo c = new Cacoo("[consumerKey]", "[consumerSecret]");
    AccessToken accessToken = new AccessToken("[token]", "[tokenSecret]");
    c.setAccessToken(accessToken);

You can register applications here.
https://cacoo.com/profile/apps

You can get your Access Token from the following code.(exec console)

    AccessToken accessToken = c.retrieve();

###Get Diagrams

  Returns a list of your diagrams

    Diagrams diagrams = c.getDiagrams();
    for (Diagram diagram : diagrams.getResult()) {
        //...
    }

###Get Diagram

    Diagram diagram = c.getDiagram("[diagramId]");

###Output Image

    Diagram diagram = c.getDiagram("[diagramId]");
    FileOutputStream out = new FileOutputStream("/Users/username/Desktop/diagram.png");
    c.outputImage(diagram, out);
    out.close();

###Get Chat Messages

    Messages messages = c.getChatMessages("[diagramId]");

###Get Folders

    Folders folders = c.getFolders();
    for (Folder folder : folders.getResult()) {
        //...
    }

###Get User

    CacooUser user = c.getUser("[username]");

###Get Account

    CacooUser user = c.getAccount();

###Get License

    License license = c.getLicense();

###Create Diagram

    Diagram diagram = c.createDiagram(new CreateDiagramRequest("[titlename]"));

###Copy Diagram

    Diagram diagram = c.copyDiagram("[diagramId]");

###Delete Diagram

    c.deleteDiagram("[diagramId]");

###Post Comment

    c.postComment("[diagramId]", "[comment]");

###Get Diagram Contents

    String contents = c.getDiagramContents("[diagramId]");
