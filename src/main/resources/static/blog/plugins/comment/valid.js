/**
 * 判空
 *
 * @param obj
 * @returns {boolean}
 */
function isNull(obj) {
    return obj == null || obj.trim() === "";

}

/**
 * 参数长度验证
 *
 * @param obj
 * @param length
 * @returns {boolean}
 */
function validLength(obj, length) {
    return obj.trim().length < length;

}

/**
 * 正则匹配2-100位的中英文字符串
 *
 * @param str
 * @returns {boolean}
 */
function validCN_ENString2_100(str) {
    var pattern = /^[a-zA-Z0-9-\u4E00-\u9FA5_,， ]{2,100}$/;
    return pattern.test(str.trim());
}

/**
 * 邮箱验证
 * @param str
 * @returns {boolean}
 */
function isEmail(str) {
    var pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return pattern.test(str.trim());
}

/**
 * 网址验证(url,支持端口和"?+参数"和"#+参数)
 * @param str
 * @returns {boolean}
 */
function isUrl(str) {
    var pattern = /^(((ht|f)tps?):\/\/)?[\w-]+(\.[\w-]+)+([\w.,@?^=%&:/~+#-]*[\w@?^=%&/~+#-])?$/;
    return pattern.test(str.trim());
}
