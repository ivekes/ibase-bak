package com.wzk.v1;

import java.util.concurrent.LinkedBlockingQueue;

public class ProcessorPre extends Thread implements IRequestProcessor {

    LinkedBlockingQueue<RequestData> requestAction = new LinkedBlockingQueue();
    private volatile boolean isFinish = false;
    private IRequestProcessor nextProcessor;

    public ProcessorPre(IRequestProcessor requestProcessor){
        this.nextProcessor = requestProcessor;
    }

    public void shutdown(){
        this.isFinish = true;
    }

    @Override
    public void run() {
        while (!isFinish){
            try {
                System.out.println("3");
                RequestData requestData = requestAction.take();//从阻塞队列获取数据
                System.out.println("processorPre run");//处理业务逻辑
                nextProcessor.process(requestData);//交给下个责任链
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void process(RequestData requestData) {
        requestAction.add(requestData);
    }
}
