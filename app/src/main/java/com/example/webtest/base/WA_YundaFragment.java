package com.example.webtest.base;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.webtest.Utils.ConstantUtils;
import com.example.webtest.io.WA_Parameters;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * @author z.h
 * @desc 存放基本业务逻辑&Js调用本地方法的接口函数
 */
public class WA_YundaFragment extends WA_BaseFragment {

    protected WebView listWeb;
    protected WebView detailWeb;
    private String logicStr;
    private String myAmount = "";
    private int[] fiboArr;


    protected enum SearchType {
        All, Shop, Mall
    }

    private Handler handler = new Handler();

    /**
     * Function：选择商品所在的商铺类型(天猫或淘宝)
     */
    protected String selectSearchType(boolean isTMall) {
        String str = "var sortType= doGetTextByCN(\"s-input-tab-txt\");" + "if(!" + isTMall + "){" + "if(sortType!=\"天猫\"){" + "doClickByCN(\"s-input-tab-txt\",1);" + "doClickByCN(\"all\",2);" + "doClickByCN(\"s-input-tab-txt\",2);" + "}}else{" + "if(sortType!=\"宝贝\"){" + "doClickByCN(\"s-input-tab-txt\",1);" + "doClickByCN(\"mall\",2);" + "doClickByCN(\"s-input-tab-txt\",2);" + "}}";
        return str;
    }

    /**
     * Function：点击进入搜索(BelongTo Step1)
     *
     * @param buyPositionList
     * @param buyNumList
     * @param buyDifList
     * @param typeBlank
     */
    protected void doEnterSearchPage(final ArrayList<String> buyPositionList, final ArrayList<String> buyNumList, final ArrayList<String> buyDifList, final String amount, final boolean IS_Auto, final int typeBlank) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                enterSearchPage(listWeb, buyPositionList, buyNumList, buyDifList, amount, IS_Auto, typeBlank);
            }
        });
    }

    protected void goSc() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                goSc(listWeb);
            }
        });
    }

    protected void goFt() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                goFt(listWeb);
            }
        });
    }

    /**
     * Function：选择商铺类型(BelongTo Step2)
     */
    protected void doSelectStoreType(final WA_Parameters parameter) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                selectStoreType(listWeb, parameter.getIsTMall());
            }
        });
    }

    /**
     * Function：进行商品搜索(BelongTo Step2)
     */
    protected void doSearch(final WA_Parameters parameter) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                searchFor(listWeb, parameter.getKeywordStr());
            }
        });
    }

    /**
     * Function：首次进行商品浏览(BelongTo Step3)
     */
    protected void doScan(final WA_Parameters parameter) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scanGoods(listWeb, parameter.getTitleStr());
            }
        }, 4000);

    }

    /**
     * Function：根据销量排序(BelongTo Step4)
     */
    protected void doOrderBySellAmount() {

        handler.post(new Runnable() {
            @Override
            public void run() {
                orderBySellAmount(listWeb);
            }
        });
    }

    /**
     * Function：若当前页中不存在该商铺则翻页，同时另一个页面进行随机商品浏览，浏览时长随机(BelongTo Step5)
     */
    protected void doFlipAndScan(final WA_Parameters parameter, final int randomTime) {
        // 跳转到下一页
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getNextPage(listWeb);
            }
        }, 2000);

        // 在当前页查找，若没查到则翻到下一页递归查找
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scanGoods(listWeb, parameter.getTitleStr());
            }
        }, 5000 + randomTime * 1000);
    }

    /**
     * Function：不翻页，在当前页进行随机商品浏览，浏览时长随机(BelongTo Step5)
     */
    protected void doScanForLongTime(final WA_Parameters parameter, final int randomTime) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scanGoods(listWeb, parameter.getTitleStr());
            }
        }, 5000 + randomTime * 1000);

    }

    /**
     * Function：关闭提示框(BelongTo Step5)
     */
    protected void doAlertHide() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                alertHide(detailWeb);
            }
        }, 2000);
    }

    /**
     * Function：根据给定的URL进入执行商铺(BelongTo Step5)
     */
    protected void doEnterShop(final String url) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                enterShop(detailWeb, url);
            }
        });

    }

    /**
     * Function：选择商品SKU
     */
    protected void doSelectSku() {

    }

    //TODO

    /**
     * 点击进入搜索页面(主页面)
     */
    private void enterSearchPage(WebView webView, ArrayList<String> buyPositionList, ArrayList<String> buyNumList, ArrayList<String> buyDifList, String amount, boolean IS_Auto, int typeBlank) {
        logicStr = "";
//        if (!Utils.isCurrentInTimeScope(13, 20, 4, 0)) {
//            return;
//        }

        ArrayList<String> buylist = new ArrayList<String>();
        ArrayList<Integer> smallerList = new ArrayList<Integer>();

        for (int i = 0; i < buyPositionList.size(); i++) {
            buylist.add(buyPositionList.get(i));
            if (i % 2 != 0) {
                String[] split = buyPositionList.get(i).split("数字：");
                int smarllInt = Integer.parseInt(split[2]);
                smallerList.add(smarllInt);
            }


        }
        for (int i = 0; i < buyNumList.size(); i++) {
            buylist.add(buyNumList.get(i));
            if (i % 2 != 0) {
                String[] split = buyNumList.get(i).split("数字：");
                int smarllInt = Integer.parseInt(split[2]);
                smallerList.add(smarllInt);
            }
        }

        int[] smarllerInts = sortIntList(smallerList);

        if (buyPositionList.size() > 0) {
            if (ConstantUtils.isCUSTOM()) {
                getMethod(buyPositionList, amount, "0", IS_Auto, typeBlank, smarllerInts[smarllerInts.length - 2]);
            } else {
                getMethod(buyPositionList, amount, "0", IS_Auto, typeBlank, 0);
            }
            logicStr = logicStr + "commitData(1000);";
        }
        if (buyNumList.size() > 0) {
            if (ConstantUtils.isCUSTOM()) {
                getMethod(buyNumList, amount, "3500", IS_Auto, typeBlank, smarllerInts[smarllerInts.length - 2]);
            } else {
                getMethod(buyNumList, amount, "3500", IS_Auto, typeBlank, 0);
            }
            logicStr = logicStr + "commitData(4500);";
        }
        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    private void getMethod(ArrayList<String> buyPositionList, String amount, String time, boolean IS_AUTO, int typeBlank, int smarllerInts) {
        for (int i = 0; i < buyPositionList.size(); i++) {
            String[] split = buyPositionList.get(i).split("数字：");
            int num = (Integer.parseInt(split[0]) - 1) * 10 + Integer.parseInt(split[1]);
            if (IS_AUTO) {

                if (!ConstantUtils.isCUSTOM()) {

                    if (i % 2 == 0) {
                        String[] splitAmount = buyPositionList.get(i).split("数字：");
                        String[] splitAmount2 = buyPositionList.get(i + 1).split("数字：");
                        int sameInt = ConstantUtils.autoSame10;
                        switch (typeBlank) {
                            case ConstantValue.TYPE_BLANK_20:
                                //TODO
                                sameInt = ConstantUtils.autoSame20;
                                break;
                            case ConstantValue.TYPE_BLANK_15:
                                sameInt = ConstantUtils.autoSame15;
                                break;
                            case ConstantValue.TYPE_BLANK_10:
                                sameInt = ConstantUtils.autoSame10;
                                break;
                        }
                        myAmount = (Integer.parseInt(splitAmount2[2]) - Integer.parseInt(splitAmount[2])) > 0 ? getCustomAmount(Integer.parseInt(splitAmount[2]), sameInt) : getCustomAmount(Integer.parseInt(splitAmount2[2]), sameInt);
                    }
                } else {

                    if (i % 2 == 0) {
                        String[] splitAmount = buyPositionList.get(i).split("数字：");
                        String[] splitAmount2 = buyPositionList.get(i + 1).split("数字：");
                        if ((Integer.parseInt(splitAmount2[2]) >= smarllerInts) && (Integer.parseInt(splitAmount[2]) >= smarllerInts)) {
                            myAmount = getCustomAmount(smarllerInts, typeBlank);
                        } else {
                            myAmount = getCustomAmount(Integer.parseInt(splitAmount2[2]), typeBlank);
                        }

                    }
                }
//                getCustomAmount(ints[0],);
//                if (!Utils.isCurrentInTimeScope(13, 20, 3, 0) && Integer.parseInt(myAmount) < ConstantUtils.originAmount * 3) {
//                    return;
//                }
                logicStr = logicStr + "selectNumRange(" + num + "," + myAmount + "," + time + ");";
            } else {
                logicStr = logicStr + "selectNumRange(" + num + "," + amount + "," + time + ");";
            }
        }
    }

//    private void getCustomAmount(ArrayList<String> buyPositionList, int typeBlank) {
//        int[] ints = new int[buyPositionList.size()];
//        for (int i = 0; i < buyPositionList.size(); i++) {
//            String[] split = buyPositionList.get(i).split("数字：");
//            ints[i] = Integer.parseInt(split[2]);
//        }
//        sortInt(ints);
//        myAmount = getCustomAmount(ints[0], typeBlank);
//    }

    private String getCustomAmount(int anInt, int typeBlank) {
        int sameInt = ConstantUtils.autoSame10;
        String mAmount = "";
        switch (typeBlank) {

            case ConstantValue.TYPE_BLANK_20:
                //TODO
                sameInt = ConstantUtils.autoSame20;
                break;
            case ConstantValue.TYPE_BLANK_15:
                sameInt = ConstantUtils.autoSame15;
                break;
            case ConstantValue.TYPE_BLANK_10:
                sameInt = ConstantUtils.autoSame10;
                break;
        }
//        if ((anInt - sameInt) / 2 <= 1) {
//            mAmount = 1 + "";
//        } else {
//            double floor = Math.floor((anInt - sameInt) / 2);
//            mAmount = (floor + "").replace(".0", "");
//        }

//        mAmount = (anInt - sameInt)＋"";


        if (fiboArr == null) {
            fiboArr = new int[14];
            fiboArr[0] = fiboArr[1] = 1;
            for (int i = 2; i < 7; i++) {
                fiboArr[i] = fiboArr[i - 1] + fiboArr[i - 2];
            }

            fiboArr[7] = fiboArr[8] = 3;
            for (int i = 9; i < fiboArr.length; i++) {
                fiboArr[i] = fiboArr[i - 1] + fiboArr[i - 2];
            }
        }
        try {
            mAmount = fiboArr[anInt - sameInt] * ConstantUtils.getOriginAmount() + "";
        } catch (Exception e) {

            Log.e(TAG, "exception!!!: anInt" + anInt);
            Log.e(TAG, "exception!!!: typeBlank" + typeBlank);
//             Log.e(TAG, "exception!!!: anInt" + anInt);
//            Log.e(TAG, "exception!!!: typeBlank" + typeBlank);
            mAmount = "";
        }
        return mAmount;

    }

    private String getLotteryAmount(int str, final int sameInt) {
        int amount = ConstantUtils.originAmount;
        String StrAmount = amount + "";

        if (str >= sameInt && str <= (sameInt + 2)) {
            StrAmount = amount + "";
        } else if (str == (sameInt + 3)) {
            StrAmount = amount * 2 + "";
        } else if (str == (sameInt + 4)) {
            StrAmount = amount * 3 + "";
        } else if (str == (sameInt + 5)) {
            StrAmount = amount * 5 + "";
        } else if (str >= (sameInt + 6) && str <= (sameInt + 7)) {
            StrAmount = amount * 8 + "";
        } else if (str >= (sameInt + 8) && str <= (sameInt + 13)) {
            StrAmount = amount * 16 + "";
        } else if (str >= (sameInt + 14) && str <= (sameInt + 16)) {
            StrAmount = amount * 10 + "";
        } else if (str >= (sameInt + 17) && str <= (sameInt + 20)) {
            StrAmount = amount * 20 + "";
        } else {
            StrAmount = "";
        }

//        if (str >= sameInt && str <= (sameInt + 4)) {
//            StrAmount = amount + "";
//        } else if (str >= (sameInt + 5) && str <= (sameInt + 6)) {
//            StrAmount = amount * 2 + "";
//        } else if (str >= (sameInt + 7) && str <= (sameInt + 8)) {
//            StrAmount = amount * 3 + "";
//        } else if (str >= (sameInt + 9) && str <= (sameInt + 10)) {
//            StrAmount = amount * 5 + "";
//        } else if (str >= (sameInt + 11) && str <= (sameInt + 12)) {
//            StrAmount = amount * 8 + "";
//        } else if (str >= (sameInt + 13) && str <= (sameInt + 15)) {
//            StrAmount = amount * 13 + "";
//        } else if (str >= (sameInt + 16) && str <= (sameInt + 17)) {
//            StrAmount = amount * 21 + "";
//        } else {
//            StrAmount = "";
//        }
        return StrAmount;
    }

    private void goSc(WebView webView) {
        String logicStr = "goSc();";
        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    private void goFt(WebView webView) {
        String logicStr = "goFt();";
        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }


    /**
     * 选择店铺类型
     */
    private void selectStoreType(WebView webView, boolean isTMall) {
        // 拼接业务逻辑
//		String logicStr = selectSearchType(isTMall);
        String logicStr = "doComfir();";
        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 输入搜索内容，然后查找
     */
    private void searchFor(WebView webView, String keywordStr) {
        // 拼接业务逻辑
        String logicStr = "doInputByCN(\"J_autocomplete\",\"" + keywordStr + "\",2);" + "doClickByCN(\"icons-search\",4);";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 点击筛选按钮
     */
    private void filterGoods(WebView webView) {
        // 拼接业务逻辑
        String logicStr = "doTapByRI(\"J_Sift\");";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 确定筛选条件
     */
    private void confirmFilter(WebView webView) {
        // 拼接业务逻辑
        String logicStr = "doClickByRI(\"J_SiftCommit\",2);";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 按销量优先排序
     */
    private void orderBySellAmount(WebView webView) {
        // 拼接业务逻辑
        String logicStr = "doTapByParentCN(\"sort-tab\",\"sort\");";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 浏览商铺
     */
    private void scanGoods(WebView webView, String titleStr) {
        // 拼接业务逻辑
        String logicStr = "var currentPage=doGetTextByCNByInner(\"currentPage\");" + "var totalSize=getSize(\"list-item\");"
                // +"localMethod.JI_showToast(totalSize);"
                // + "localMethod.JI_showToast(currentPage);"
                + "doTapForScanGoodsByTitle(\"list-item\",\"d-title\",\"" + titleStr + "\",currentPage,totalSize);";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 关闭提示框
     */
    private void alertHide(WebView webView) {
        // 拼接业务逻辑
        String logicStr = "doClickByCN(\"btn-hide\",2);";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 进入目标商铺
     */
    private void enterShop(WebView webView, String url) {
        webView.loadUrl("https:" + url);
    }

    private void skuSelect(WebView webView) {
        // 拼接业务逻辑
        String logicStr = "doTapByCN02(); ";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 翻页
     */
    private void getNextPage(WebView mWebView) {
        String logicStr = "doTapByCN(\"J_PageNext\"); ";

        String completeJs = doAutoTest(logicStr);
        loadUrl(mWebView, completeJs);
    }

    /* 暴露给JavaScript脚本调用的方法* */
    public class LocalMethod {
        Context mContext;
        private WA_Parameters parameter;

        public LocalMethod(Context c, WA_Parameters parameter) {
            this.mContext = c;
            this.parameter = parameter;
        }

        public WA_Parameters getParameter() {
            return parameter;
        }

        public void setParameter(WA_Parameters parameter) {
            this.parameter = parameter;
        }

        @JavascriptInterface
        public void JI_showToast(String content) {
            Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void JI_LOG(String content) {
            Log.e(TAG, "JI_LOG: " + content);
//			Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void JI_scrollView() {
            listWeb.scrollBy(0, 1800);
        }

        @JavascriptInterface
        public void JI_doGetNextPage(int randomTime) {
            doFlipAndScan(parameter, randomTime);
        }

        @JavascriptInterface
        public void JI_doScanCurrentPage(int randomTime) {

            doScanForLongTime(parameter, randomTime);
        }

        @JavascriptInterface
        public void JI_doCloseAlert() {
            doAlertHide();
        }

        @JavascriptInterface
        public void JI_doEnterShop(String url) {
            doEnterShop(url);
        }

        @JavascriptInterface
        public void JI_createLog(String infoStr) throws IOException {
            createLog(infoStr);
        }
    }


    private int[] sortInt(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    private int[] sortIntList(ArrayList<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}
