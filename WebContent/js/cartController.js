/*
 * @Description  : 
 * @Author       : Maverick
 * @Date         : 2020-09-18 14:01:08
 * @LastEditors  : Maverick
 * @LastEditTime : 2020-11-27 17:08:57
 */
var product = document.getElementsByClassName("product");//商品类别
var box = document.getElementsByName("item");//选择按钮
var all = document.getElementsByName("selectAll");//全选按钮
var total = document.getElementsByClassName("total");//总价
var minus = document.getElementsByClassName("minus");//控制商品数量减少
var add = document.getElementsByClassName("add");//控制商品数量增加
var number = document.getElementsByClassName("number");//商品数量选择器
var summary = document.getElementsByClassName("summary");//单件商品总价选择器
var del = document.getElementsByClassName("delete");
var delAll = document.getElementsByClassName("delAll");//删除选中商品按钮
var pattern = /^(?!0)\d+$/;
var patternP = /\d+.\d+/;
//当前选中的商品类别个数
var boxCount = 0;
//通过input获取商品数量
var oldNum = 0;
var newNum = 0;
//单个商品总计元素
var inP;//div
//商品的价格和数量
var price;//text
var proCount;//number

var xmlHttp;
function createXMLHttpRequest() {
    if (window.ActiveXObject) {//如果是IE
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } else if (window.XMLHttpRequest) {//非IE
        xmlHttp = new XMLHttpRequest();
    }
}
function sendCountChange(id, count) {
    createXMLHttpRequest();
    var url = "countChangeServlet?current=" + (new Date().getTime()) + "&id=" + id + "&count=" + count;
    xmlHttp.open("GET", url, true);
    xmlHttp.send();
}
function sendCountChangeRequest(obj, count) {
    var id = obj.parentNode.parentNode.getElementsByClassName("hiddenID")[0].innerText;
    //console.log(id + " " + count);
    sendCountChange(id, count);
}
// window.alert(price);
// window.alert(patternP.exec(price));//3.9

//按钮全选
all[0].onclick = function () {
    if (all[0].checked) {//选中全选按钮
        var sum = 0.0;
        for (let index = 0; index < box.length; index++) {
            box[index].checked = true;
            //合计计价
            sum = sum + parseFloat(patternP.exec(summary[index].innerText));//计算总价 浮点数存在精度问题
        }
        boxCount = box.length;

        total[0].innerHTML = "合计：&nbsp;<span>￥" + sum.toFixed(1) + "</span>&nbsp;";
    }
    else {
        for (let index = 0; index < box.length; index++) {
            box[index].checked = false;
        }
        boxCount = 0;

        total[0].innerHTML = "合计：&nbsp;<span>￥0.0</span>&nbsp;";
    }
}
delAll[0].onclick = function () {
    if (boxCount != 0) {
        if (window.confirm("确定要删除选中商品吗?")) {
            var length = product.length;
            var now = 0;
            var id = new Array();
            var url = "removeProductServlet?";
            for (let index = 0; index < length; index++) {
                if (product[now].getElementsByClassName("cb")[0].checked) {
                    id[index] = product[now].getElementsByClassName("hiddenID")[0].innerText;
                    //alert(id[index]);
                    url += "id" + "=" + id[index] + "&";
                    product[now].remove();
                }
                else {
                    now++;
                }
            }
            //全选按钮改变
            if (all[0].checked) {
                url = "removeProductServlet?id=all&";//test
                all[0].checked = false;
            }
            //选中商品数量改变
            boxCount = 0;
            //合计计价
            total[0].innerHTML = "合计：&nbsp;<span>￥0.0</span>&nbsp;";
            //传递更改到后台
            createXMLHttpRequest();
            url = url.substring(0, url.length - 1);
            //console.log(url);
            xmlHttp.open("GET", url, true);
            xmlHttp.send();
        }
    }
    else {
        window.alert("尚未选中商品!");
    }
}

function totalPrice(changePrice, type) {//合计全部商品价格
    var newTotal = 0;
    if (type == 1) {//1为减少
        newTotal = parseFloat(patternP.exec(total[0].innerText)) - changePrice;
    } else if (type == 2) {//2为增加
        newTotal = parseFloat(patternP.exec(total[0].innerText)) + changePrice;
    }
    total[0].innerHTML = "合计：&nbsp;<span>￥" + newTotal.toFixed(1) + "</span>&nbsp;";
}

//每类商品
for (let index = 0; index < product.length; index++) {
    //商品选择 √
    box[index].onclick = function () {
        inP = this.parentNode.parentNode.getElementsByClassName("summary");//单件商品总计计价
        if (!this.checked) {
            all[0].checked = false;
            //减少商品 减价
            totalPrice(parseFloat(patternP.exec(inP[0].innerText)), 1);
            boxCount--;
        }
        else {
            // window.alert(total[0].innerHTML);
            //增加商品 加价
            totalPrice(parseFloat(patternP.exec(inP[0].innerText)), 2);
            boxCount++;
        }
        if (boxCount == box.length) {
            all[0].checked = true;
        }
    }

    //商品数量通过加减号增减 √
    minus[index].addEventListener("click", function () {
        price = this.parentNode.parentNode.getElementsByClassName("price")[0].innerText;
        proCount = this.nextElementSibling;
        if (proCount.value > 1) {
            proCount.value--;
            //总计计价
            inP = this.parentNode.parentNode.getElementsByClassName("summary");
            inP[0].innerHTML = "￥" + (parseFloat(patternP.exec(price)) * proCount.value).toFixed(1);
            //合计计价
            if (this.parentNode.parentNode.getElementsByClassName("cb")[0].checked) {
                totalPrice(parseFloat(patternP.exec(price)), 1);//每次只改变一份商品的价格
            }
            //向后台传递商品数量
            sendCountChangeRequest(this, proCount.value);
        }
        else if (proCount.value == 1) {
            window.alert("商品数量最小为1");
        }
    });

    add[index].addEventListener("click", function () {
        price = this.parentNode.parentNode.getElementsByClassName("price")[0].innerText;
        proCount = this.previousElementSibling;
        if (proCount.value < 999) {
            proCount.value++;
            //总计计价
            inP = this.parentNode.parentNode.getElementsByClassName("summary");
            inP[0].innerHTML = "￥" + (parseFloat(patternP.exec(price)) * proCount.value).toFixed(1);
            //合计计价
            if (this.parentNode.parentNode.getElementsByClassName("cb")[0].checked) {
                totalPrice(parseFloat(patternP.exec(price)), 2);
            }
            //向后台传递商品数量
            sendCountChangeRequest(this, proCount.value);
        }
        else if (proCount.value == 999) {
            window.alert("库存不足");
        }
    });

    //商品数量文本框只能输入非0开头数字 √
    number[index].onfocus = function () {
        oldNum = this.value;
        // console.log(oldNum);
    }//.onchange = 
    number[index].addEventListener("change", function () {
        if (!pattern.test(this.value)) {
            this.value = oldNum;
        }
        else {//输入正常 按数量计价
            newNum = this.value;
            price = this.parentNode.parentNode.getElementsByClassName("price")[0].innerText;
            //总计计价
            inP = this.parentNode.parentNode.getElementsByClassName("summary");
            inP[0].innerHTML = "￥" + (parseFloat(patternP.exec(price)) * newNum).toFixed(1);
            //合计计价
            if (this.parentNode.parentNode.getElementsByClassName("cb")[0].checked) {
                totalPrice(parseFloat(patternP.exec(price)) * (newNum - oldNum), 2);//减去变化的商品数量对应的价格
            }
            //向后台传递商品数量
            sendCountChangeRequest(this, newNum);
        }
    }, false);

    //确认删除就index发生改变 √
    del[index].addEventListener("click", function () {
        if (window.confirm("确认删除该商品?")) {
            //合计计价改变
            if (this.parentNode.getElementsByClassName("cb")[0].checked) {
                inP = this.parentNode.getElementsByClassName("summary");
                totalPrice(parseFloat(patternP.exec(inP[0].innerText)), 1);//减去删除商品对应的总计价格
                boxCount--;
            }
            //向servlet传递信息 ajax局部刷新
            var id = this.parentNode.getElementsByClassName("hiddenID")[0].innerText;
            //删除product结点
            var pro = this.parentNode;
            pro.remove();
            if (boxCount == box.length && boxCount != 0) {
                all[0].checked = true;
            }
            else {
                all[0].checked = false;
            }
            createXMLHttpRequest();
            var url = "removeProductServlet?id=" + id;
            xmlHttp.open("GET", url, true);
            xmlHttp.send();
        }
    });
}
