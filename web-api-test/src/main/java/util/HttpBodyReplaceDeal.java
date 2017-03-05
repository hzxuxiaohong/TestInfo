package util;

/**
 * Created by hzyangbaosong on 2016-05-25.
 */
public class HttpBodyReplaceDeal {
    public  String  replaceBodyContent(String body,String bodyReplaceKey,String realReplaceWord){
        String bodyAfterDeal=body;
        if(realReplaceWord!=null||realReplaceWord.equals("")) {
            String needReplaceBodyKeyWord = "#" + bodyReplaceKey;
            if (body.contains(needReplaceBodyKeyWord)) {
                bodyAfterDeal=body.replace(needReplaceBodyKeyWord, realReplaceWord);

            }

        }
        return bodyAfterDeal;
    }

}
