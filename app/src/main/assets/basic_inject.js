/** ע����JS�ļ����ڴ�ų��ú�����������صĺ�������Java�ļ���ע��*/


/** No.1 ģ�����¼�############################################################################################*/
//ģ�����¼�
function doClickByRI(resId,time) {
 var btn = document.getElementById(resId);
 if(null!=btn){
    setTimeout(function(){
        btn.click();
    },time*1000);
    }
}

function doClickByTag(){
  var itemli = document.getElementsByTagName("li");
  localMethod.JI_showToast("length��"+itemli.length);

}

function goSc(){
  var scBtn = document.getElementById('homeMenus').getElementsByTagName("a")[0];
  scBtn.click();
  var money = document.getElementById('userinfo_money');
  localMethod.JI_LOG(money.innerHTML);
  setTimeout(function(){
      var oneTenBtn = document.getElementById('cate_menus_50').getElementsByTagName("a")[1];
      oneTenBtn.click();

      setTimeout(function(){
          var itema = document.getElementById('framePage').contentWindow.document.getElementsByTagName('input');
          localMethod.JI_showToast("itema:"+itema.length);
          if(itema.length<100){
            goSc();
          }
      },1500);
  },1000);

}

function goFt(){
  var scBtn = document.getElementById('homeMenus').getElementsByTagName("a")[1];
  scBtn.click();
  var money = document.getElementById('userinfo_money');
  localMethod.JI_LOG(money.innerHTML);
  setTimeout(function(){
      var oneTenBtn = document.getElementById('cate_menus_55').getElementsByTagName("a")[1];
      oneTenBtn.click();

      setTimeout(function(){
          var itema = document.getElementById('framePage').contentWindow.document.getElementsByTagName('input');
          localMethod.JI_showToast("itema:"+itema.length);
          if(itema.length<100){
            goFt();
          }
      },1500);
  },500);
}


function doComfir(){

    setTimeout(function(){

  var btn = document.getElementsByClassName("layui-layer-btn0");
    localMethod.JI_showToast("btn:"+btn.length);
    btn[0].click();
        },3000);
}

function selectNumRange(position,amount,time){

  setTimeout(function(){
  var itema = document.getElementById('framePage').contentWindow.document.getElementsByTagName('input');
  localMethod.JI_showToast("itema:"+itema.length);
    itema[position].click();
    itema[position].value = amount;
  },time);

}

function commitData(time){
  var commitBtn = document.getElementById('framePage').contentWindow.document.getElementById('openBetWinBtn2');
    setTimeout(function(){
        commitBtn.click()
    },500+time);

    setTimeout(function(){

        var btn = document.getElementsByClassName("layui-layer-btn0");
        localMethod.JI_showToast("btn:"+btn.length);
        btn[0].click();
     },1000+time);
}


function doClickByCN(className,time) {
  var itemli = document.getElementsByTagName("li");
  localMethod.JI_showToast("length��"+itemli.length);

  var btn = document.getElementsByClassName(className)[0];
  if(null!=btn){
    setTimeout(function(){
        btn.click();
    },time*1000);
    }
}

//ģ�ⴥ���¼�
function doTapByRI(resId,index) {
   if(null==index){index=0;}
   $("#"+resId).eq(index).trigger("tap");
}

function doTapByCN(className,index) {
  if(null==index){index=0;}
  $("."+className).eq(index).trigger("tap")
}

//���ݸ��ؼ������ӿؼ��ٴ���
function doTapByParentCN(parentCN,className,index) {
  if(null==index){index=0;}
  $("."+parentCN).children("."+className) .eq(index).trigger("tap");
}

function doTapForScanGoods(parentCN,index) {
   if(null==index){index=0;}
   $("."+parentCN).eq(index).children(".p").children("a").trigger("tap");
}


/** No.2 �����ı���Ϣ���������############################################################################################*/
function doInputByRI(resId,context,time) {
   var text = document.getElementById(resId);
    setTimeout(function(){
        text.value = context;
    },time*1000);
}

function doInputByCN(className,context,time) {
    var text = document.getElementsByClassName(className)[0];
    setTimeout(function(){
        text.value = context;
    },time*1000);
}


/** No.3 ��ȡ�ؼ����ı���Ϣ###########################################################################################*/
function doGetTextByRI(resId) {
    var text = document.getElementById(resId);
    return text.value;
}

function doGetTextByCN(className) {
    var text = document.getElementsByClassName(className)[0];
    return text.value;
}

function doGetTextByCNByInner(className) {
    var text = document.getElementsByClassName(className)[0];
    return text.innerHTML;
}



