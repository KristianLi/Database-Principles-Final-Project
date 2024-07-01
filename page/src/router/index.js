import Vue from 'vue';
import Router from 'vue-router';
import UserLogin from '@/components/UserLogin.vue';
import BookManagement from '@/components/BookManagement.vue';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'UserLogin',
            component: UserLogin
        },
        {
            path: '/book-management/:account/:password/:card_num',
            name: 'BookManagement',
            component: BookManagement
        }
    ]
});