package com.webank.oracle.test.util;

import static com.webank.oracle.base.utils.JsonUtils.stringToJsonNode;
import static com.webank.oracle.base.utils.JsonUtils.toList;
import static com.webank.oracle.test.oracle.VRF.VRFTest.bytesToHex;

import java.math.BigInteger;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.webank.oracle.base.utils.CryptoUtil;
import com.webank.oracle.base.utils.HttpUtil;

public class UtilTest {


    @Test
    public void numberTest() {
        BigInteger x = new BigInteger("c6047f9441ed7d6d3045406e95c07cd85c778e4b8cef3ca7abac09b95c709ee5", 16);
        BigInteger y = new BigInteger("1ae168fea63dc339a3c58419466ceaeef7f632653266d0e1236431a950cfe52a", 16);
        System.out.println(x);
        System.out.println(y);
        BigInteger z = new BigInteger("22108724849966695483138513023527230203911536283199945193882728796528949515038");
        System.out.println(z.toString(16));

        Credentials user = Credentials.create("b83261efa42895c38c6c2364ca878f43e77f3cddbc922bf57d0d48070f79feb6");
        // gm address  0x1f609497612656e806512fb90972d720e2e508b5
        //   address   0xc950b511a1a6a1241fc53d5692fdcbed4f766c65
        System.out.println(user.getAddress());
        System.out.println(user.getEcKeyPair().getPublicKey());
        String pk = user.getEcKeyPair().getPublicKey().toString(16);
        System.out.println(pk);
        System.out.println(pk.length());
        int len = pk.length();
        String pkx = pk.substring(0, len / 2);
        String pky = pk.substring(len / 2);
        BigInteger Bx = new BigInteger(pkx, 16);
        BigInteger By = new BigInteger(pky, 16);
        System.out.println(new BigInteger(pkx, 16));
        System.out.println(new BigInteger(pky, 16));
        System.out.println(bytesToHex(CryptoUtil.soliditySha3(Bx, By)));

    }


    @Test
    public void stringTest() {

        String argValue = "plain(https://www.random.org/integers/?num=100&min=1&max=100&col=1&base=10&format=plain&rnd=new)";
        int left = argValue.indexOf("(");
        int right = argValue.indexOf(")");
        String header = argValue.substring(0, left);
        String url = argValue.substring(left + 1, right);
        System.out.println(header);
        System.out.println(url);

        String argValue1 = "plain(https://www.random.org/integers/?num=100&min=1&max=100&col=1&base=10&format=plain&rnd=new)";
        if (StringUtils.isBlank(argValue1) || argValue1.endsWith(")")) {
            System.out.println("*******");

        }
        String resultIndex = argValue.substring(argValue.indexOf(").") + 2);

        String[] resultIndexArr = resultIndex.split("\\.");
        List resultList = new ArrayList<>(resultIndexArr.length);
        Collections.addAll(resultList, resultIndexArr);
        System.out.println(resultList);
        // List<Object> httpResultIndexList = subFiledValueForHttpResultIndex(argValue);

    }

    @Test
    public void httpsTest() throws Exception, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        // String argValue = "plain(https://www.random.org/integers/?num=100&min=1&max=100&col=1&base=10&format=plain&rnd=new)";

        String argValue = "json(https://api.kraken.com/0/public/Ticker?pair=ETHXBT).result.XETHXXBT.c.0";
        //      String argValue = "json(https://api.apiopen.top/EmailSearch?number=1012002).result";

        //   String argValue = "plain(https://www.random.org/integers/?num=100&min=1&max=100&col=1&base=10&format=plain&rnd=new)";

        int left = argValue.indexOf("(");
        int right = argValue.indexOf(")");
        String header = argValue.substring(0, left);
        String url = argValue.substring(left + 1, right);
        List<String> httpResultIndexList = subFiledValueForHttpResultIndex(argValue);
        System.out.println("********* begin");
//            String result = HttpsUtil.get(url);
//            System.out.println("*********" + result);
//            Object o = getValueByKeys(result, httpResultIndexList);
//            System.out.println(o);
        // Object o =  HttpService.getObjectByUrlAndKeys(url,header,httpResultIndexList);
        String result = HttpUtil.get(url);
        System.out.println(result);
    }


    private List<String> subFiledValueForHttpResultIndex(String argValue) {
        if (StringUtils.isBlank(argValue) || argValue.endsWith(")")) {
            return Collections.EMPTY_LIST;
        }

        String resultIndex = argValue.substring(argValue.indexOf(").") + 2);

        String[] resultIndexArr = resultIndex.split("\\.");
        List resultList = new ArrayList<>(resultIndexArr.length);
        Collections.addAll(resultList, resultIndexArr);
        return resultList;
    }

    private Object getValueByKeys(String resultString, List<String> keyList) {
        if (resultString == null || keyList == null || keyList.size() == 0) return resultString;

        Object finalResult = stringToJsonNode(resultString);
        for (String key : keyList) {
            finalResult = getValueByKey(finalResult, key);
        }
        return finalResult;
    }

    private Object getValueByKey(Object jsonNode, String key) {
        if (jsonNode instanceof ArrayNode) {
            List<Object> jsonArray = toList(jsonNode);
            return jsonArray.get(Integer.valueOf(String.valueOf(key)));
        }
        try {
            JsonNode jsonNode1 = (JsonNode) jsonNode;
            return jsonNode1.get(key);
        } catch (Exception ex) {
            return jsonNode;
        }
    }

}