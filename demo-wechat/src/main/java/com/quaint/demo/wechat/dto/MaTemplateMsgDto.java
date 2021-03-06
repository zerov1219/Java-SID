package com.quaint.demo.wechat.dto;

import cn.binarywang.wx.miniapp.bean.WxMaTemplateData;
import com.quaint.demo.wechat.helper.WxServiceContainer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信官方 于 2020-01-10 下架该接口, 使用消息订阅
 * @see MaSubscribeMsgDto
 * @author quaint
 * @date 2020-01-07 12:53
 */
@Data
@Deprecated
public class MaTemplateMsgDto {

    /**
     * @see WxServiceContainer
     */
    @ApiModelProperty("渠道 Demo 请忽略")
    @Deprecated
    private Integer channel;

    @ApiModelProperty(value = "模板Id",required = true)
    @NotNull(message = "模板不能为空")
    private String templateId;

    @ApiModelProperty(value = "表单Id",required = true)
    @NotNull(message = "表单Id不能为空")
    private String formId;

    @ApiModelProperty(value = "用户openId",required = true)
    @NotNull(message = "用户openId不能为空")
    private String openId;

    @ApiModelProperty(value = "小程序page",required = true)
    private String page;

    @ApiModelProperty(value = "模板数据",required = true)
    private List<WxMaTemplateData> data;

    public void appendData(String name,String value){
        if(this.data == null){
            this.data = new ArrayList<>();
        }
        this.data.add(new WxMaTemplateData(name,value));
    }

}
