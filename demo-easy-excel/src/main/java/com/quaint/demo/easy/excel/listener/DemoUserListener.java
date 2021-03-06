package com.quaint.demo.easy.excel.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.fastjson.JSONObject;
import com.quaint.demo.easy.excel.ann.ExcelPropertyNotNull;
import com.quaint.demo.easy.excel.dto.DemoUserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * 官方提示:有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
 *
 * 如果想被spring 管理的话, 改为原型模式, Controller 以 getBean 形式获取
 * @author quaint
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@Scope(SCOPE_PROTOTYPE)
@Component
public class DemoUserListener extends AnalysisEventListener<DemoUserDto> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;

    private List<DemoUserDto> list = new ArrayList<>();

    /**
     * 方式一
     *  可以换成 @Autowired 注入 service 或者mapper
     *  不被spring管理的话  使用构造函数 接收外面被spring管理的mapper -->constructor
     * @Autowired
     * DemoUserMapper demoUserMapper;
     */
    private List<DemoUserDto> virtualDataBase = new ArrayList<>();

    /**
     * 方式二
     * 假设 virtualDataBase 是 mapper, 这里就在外面new该类的时候传进来  调用方注入过得mapper
     * @param virtualDataBase
     */
//    public DemoUserListener(List<DemoUserDto> virtualDataBase) {
//        this.virtualDataBase = virtualDataBase;
//    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(DemoUserDto data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSONObject.toJSONString(data));

        // 校验非空
        Field[] fields = DemoUserDto.class.getDeclaredFields();
        for (Field f: fields) {
            f.setAccessible(true);
            ExcelPropertyNotNull ann = f.getAnnotation(ExcelPropertyNotNull.class);
            if (null != ann && ann.open()){
                try {
                    if(null == f.get(data)){
                        log.info("有一条数据未通过校验,message[{}]",ann.message());
                        log.info("列号：[{}]",ann.col());
                        return;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
     * @param exception exception
     * @param context context
     * @throws Exception e
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
            log.error("第{}行，第{}列解析异常", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex());
        }
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        virtualDataBase.addAll(list);
        log.info("存储数据库成功！");
    }
}