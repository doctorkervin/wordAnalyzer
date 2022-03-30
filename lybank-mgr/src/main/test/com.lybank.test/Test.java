package com.lybank.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author ABu
 * @ClassName com.lybank.test
 * @Description
 * @Date 2019/7/17 10:15
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
//        String str = "{\"code\":\"success\",\"msg\":\"操作成功\",\"data\":{\"transOutAmount\":\"0.50000000000000000000\",\"list\":[]}}";
        String str = "{\"code\":\"success\",\"msg\":\"操作成功\",\"data\":{\"transOutAmount\":\"0.50000000000000000000\",\"list\":[{\"recordId\":\"18120318462120190704999999143999999143\",\"accountType\":\"CTIS\",\"accountNum\":\"6013******8022\",\"date\":\"20190704\",\"time\":\"135517\",\"classify\":\"转账给他人\",\"classifyId\":\"227\",\"transCat\":\"2\",\"transMsg\":\"跨行转账\",\"debitCreditFlag\":\"D\",\"amount\":\"0.50000000000000000000\",\"otherActName\":\"刘肖举\",\"otherCardNum\":\"6226******3495\",\"otherCardType\":\"0\",\"sumFlag\":\"1\",\"transType\":\"\",\"merName\":\"\"},{\"recordId\":\"18120318462120190702999999146999999146\",\"accountType\":\"CTIS\",\"accountNum\":\"6013******8022\",\"date\":\"20190702\",\"time\":\"122442\",\"classify\":\"其他收入\",\"classifyId\":\"113\",\"transCat\":\"1\",\"transMsg\":\"跨行转账\",\"debitCreditFlag\":\"C\",\"amount\":\"1.00000000000000000000\",\"otherActName\":\"戚里志\",\"otherCardNum\":\"6214******8803\",\"otherCardType\":\"0\",\"sumFlag\":\"1\",\"transType\":\"\",\"merName\":\"\"}],\"totalItems\":\"2\",\"retItems\":\"2\",\"transInAmount\":\"1.00000000000000000000\",\"monthSumList\":[{\"month\":\"201907\",\"direction\":\"2\",\"monthSum\":0.5},{\"month\":\"201907\",\"direction\":\"1\",\"monthSum\":1.0}]}}";
//        JSONObject jsonObject = JSONObject.parseObject(str);
//        JSONObject data = jsonObject.getJSONObject("data");
//        JSONArray list = data.getJSONArray("list");
//
//        //json字符串转list集合实体类
//        for (Object o : list) {
//            JSONObject s = JSONObject.parseObject(o.toString());
//            System.out.println(s.get("recordId"));
//        }
//        System.out.println(list.size());
    }
}
