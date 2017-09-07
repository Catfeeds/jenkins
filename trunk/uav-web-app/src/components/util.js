/**
 * Created by Administrator on 2017/5/11/0011.
 */

function padLeftZero(str) {
  return ('00' + str).substr(str.length);
}

/**
 * 格式化日期
 * @param date
 *     日期
 * @param fmt
 *     格式
 * @returns {*}
 */
function formatDate (date, fmt) {
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
  }
  let o = {
    'M+': date.getMonth() + 1,
    'd+': date.getDate(),
    'h+': date.getHours(),
    'm+': date.getMinutes(),
    's+': date.getSeconds()
  };
  for (let k in o) {
    if (new RegExp(`(${k})`).test(fmt)) {
      let str = o[k] + '';
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : padLeftZero(str));
    }
  }
  return fmt;
}

/**
 * 计算地图上两点间的距离
 * @param lon1
 * @param lat1
 * @param lon2
 * @param lat2
 */
function distanceOnMap(lon1,lat1,lon2,lat2){
  var a, b, R;
  R = 6371; // 地球半径
  lat1 = lat1 * Math.PI / 180.0;
  lat2 = lat2 * Math.PI / 180.0;
  a = lat1 - lat2;
  b = (lon1 - lon2) * Math.PI / 180.0;
  var d;
  var sa2, sb2;
  sa2 = Math.sin(a / 2.0);
  sb2 = Math.sin(b / 2.0);
  d = 2 * R * Math.asin(
      Math.sqrt(sa2 * sa2 + Math.cos(lat1)
        * Math.cos(lat2) * sb2 * sb2));
  return d;
}

export default {
  formatDate: formatDate,
  distance:distanceOnMap
};
