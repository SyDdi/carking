/**
 * cookie保存2天
 * @param name
 * @param value
 */
function setCookie(name, value) {
	$.cookie(name,value, { expires: 2, path: '/' });
}
function getCookie(name) {
	return $.cookie(name);
}
