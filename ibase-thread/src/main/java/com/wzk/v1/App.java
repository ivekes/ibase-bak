package com.wzk.v1;

public class App {
    static IRequestProcessor requestProcessor;

    public void setUp(){//初始
        ProcessorPrint processorPrint = new ProcessorPrint();
        processorPrint.start();

        ProcessorSave processorSave = new ProcessorSave(processorPrint);
        processorSave.start();

        requestProcessor = new ProcessorPre(processorSave);
        ((ProcessorPre) requestProcessor).start();
    }

    public static void main(String[] args){
        App app = new App();
        app.setUp();
        RequestData requestData = new RequestData();
        requestData.setName("test");
        requestProcessor.process(requestData);
        requestData.setName("test2");
        requestProcessor.process(requestData);
    }
}
