import Vue from 'vue'
import App from '@/App.vue'
// element ui 完全引入
import ElementUI from 'element-ui'
import '@/assets/css/element-variables.scss'
import '@/assets/css/style.scss'
// 加载路由
// import router from '@/router/router-static.js';
import router from '@/router/router-static.js';
// 面包屑导航，注册为全局组件
import BreadCrumbs from '@/components/common/BreadCrumbs'
// 引入echart
import * as echarts from 'echarts'
import 'echarts-wordcloud'
// 引入echart主题
// import  '@/assets/js/echarts-theme-macarons.js'
import 'echarts/theme/macarons.js'
// ajax
import http from '@/utils/http.js'
// 基础配置
import base from '@/utils/base'
// 工具类
import { isAuth, getCurDate, getCurDateTime } from '@/utils/utils'
// storage 封装
import storage from "@/utils/storage";
// 上传组件
import FileUpload from "@/components/common/FileUpload";
import ExcelFileUpload from "@/components/common/ExcelFileUpload";
// 富文本编辑组件
import Editor from "@/components/common/Editor";
// api 接口
import api from '@/utils/api'
// 数据校验工具类
import * as validate from '@/utils/validate.js'
// 后台地图
import VueAMap from 'vue-amap'
import '@/icons'
//excel导出
import JsonExcel from 'vue-json-excel'
//打印
import printJS from 'print-js'
//MD5
import md5 from 'js-md5';

// 后台 localStorage 命名空间隔离，避免与前台互相覆盖登录态
(function () {
  const NS = 'admin__';
  const rawGetItem = window.localStorage.getItem.bind(window.localStorage);
  const rawSetItem = window.localStorage.setItem.bind(window.localStorage);
  const rawRemoveItem = window.localStorage.removeItem.bind(window.localStorage);
  const rawKey = window.localStorage.key.bind(window.localStorage);

  const mapKey = (k) => `${NS}${k}`;

  window.localStorage.getItem = function (key) {
    let v = rawGetItem(mapKey(key));
    if (v === null) {
      v = rawGetItem(key);
      if (v !== null) {
        rawSetItem(mapKey(key), v);
      }
    }
    return v;
  };

  window.localStorage.setItem = function (key, value) {
    return rawSetItem(mapKey(key), value);
  };

  window.localStorage.removeItem = function (key) {
    rawRemoveItem(mapKey(key));
    rawRemoveItem(key);
  };

  window.localStorage.clear = function () {
    const keys = [];
    for (let i = 0; i < window.localStorage.length; i++) {
      const k = rawKey(i);
      if (k && k.startsWith(NS)) keys.push(k);
    }
    keys.forEach(k => rawRemoveItem(k));
  };
})();

// 后台地图
Vue.use(VueAMap)
VueAMap.initAMapApiLoader({
  //key: 'ca04cee7ac952691aa67a131e6f0cee0',
  key: '001d42eaa139dc53fd655e7c23c0187e',
  plugin: ['AMap.Autocomplete', 'AMap.PlaceSearch', 'AMap.Scale', 'AMap.OverView', 'AMap.ToolBar', 'AMap.MapType', 'AMap.PolyEditor', 'AMap.CircleEditor', 'AMap.Geocoder','AMap.CitySearch'],
  // 默认高德 sdk 版本为 1.4.4
  v: '1.4.4'
})
Vue.prototype.$validate = validate
Vue.prototype.$http = http // ajax请求方法
Vue.prototype.$echarts = echarts
Vue.prototype.$base = base.get()
Vue.prototype.$project = base.getProjectName()
Vue.prototype.$storage = storage
Vue.prototype.$api = api
// 判断权限方法
Vue.prototype.isAuth = isAuth
Vue.prototype.getCurDateTime = getCurDateTime
Vue.prototype.getCurDate = getCurDate
// Vue.prototype.$base = base
Vue.use(ElementUI, { size: 'medium', zIndex: 3000 });
Vue.config.productionTip = false
// 组件全局组件
Vue.component('bread-crumbs', BreadCrumbs)
Vue.component('file-upload', FileUpload)
Vue.component('excel-file-upload', ExcelFileUpload)
Vue.component('editor', Editor)
//excel导出
Vue.component('downloadExcel', JsonExcel)
//MD5
Vue.prototype.$md5 = md5;
new Vue({
  render: h => h(App),
  router
}).$mount('#app')
