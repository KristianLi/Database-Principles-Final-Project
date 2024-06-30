// src/main.js
import Vue from 'vue';
import App from './App.vue';
import router from './router'; // 引入路由配置

Vue.config.productionTip = false;

new Vue({
  router, // 将路由注入到 Vue 实例中
  render: h => h(App)
}).$mount('#app');