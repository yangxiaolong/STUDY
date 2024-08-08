package com.atguigu;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @auther yangxiaolong
 * @create 2024/6/30
 */
public class FlinkCDC_DataStream {

    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> {
//            logger.setLevel(Level.INFO);
            logger.setLevel(Level.ERROR);
        });
    }

    public static void main(String[] args) throws Exception {
        //1.获取Flink执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        //2.开启CheckPoint
        //3.使用FlinkCDC构建MySQLSource
        MySqlSource<String> mySqlSource = MySqlSource.<String>builder()
                .hostname("localhost")
                .port(3306)
                .username("root")
                .password("123456")
                .databaseList("test")
                .tableList("test.t1")//在写表时，需要带上库名, 因为可以监控多个数据库
                .startupOptions(StartupOptions.initial())//从数据库的历史数据
//                .startupOptions(StartupOptions.latest())//从数据库的最新数据

                .deserializer(new JsonDebeziumDeserializationSchema())//将source中的数据转换为json格式
                .build();

        //4.读取数据
        DataStreamSource<String> mysqlDS = env.fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "mysql-source");
        //5.打印
        mysqlDS.print();
        //6.启动
        env.execute();
    }

}
