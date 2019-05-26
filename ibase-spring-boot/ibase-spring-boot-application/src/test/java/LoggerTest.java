import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerTest {

    public void test2(){
        log.debug("debug");//默认日志级别为info
        log.info("info");
        log.error("error");
        log.warn("warn");
    }
}
