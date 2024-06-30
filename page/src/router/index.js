// src/router/index.js
import Vue from 'vue';
import Router from 'vue-router';
import UserLogin from '@/components/UserLogin';
import BookManagement from '@/components/BookManagement';

Vue.use(Router);

export default new Router({
    mode: 'history', // 可选项：hash 或 history 模式
    routes: [
        {
            path: '/',
            name: 'UserLogin',
            component: UserLogin
        },
        {
            path: '/book-management',
            name: 'BookManagement',
            component: BookManagement
        }
    ]
});