import Vue from 'vue';
import Router from 'vue-router';
import UserLoginRegister from '@/components/UserLoginRegister.vue';
import MainMenu from '@/components/MainMenu.vue';
// 读者
import BookSearch from '@/components/BookSearch.vue';
import BookBorrow from '@/components/BookBorrow.vue';
import BookReturn from '@/components/BookReturn.vue';
import PersonInfo from '@/components/PersonInfo.vue';
// 管理员
import AdminBookManage from '@/components/AdminBookManage.vue';
import AdminReaderManage from '@/components/AdminReaderManage.vue';
import AdminBorrowManage from '@/components/AdminBorrowManage.vue';


Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/loginRegister',
            name: 'UserLoginRegister',
            component: UserLoginRegister
        },
        {
            path: '/mainMenu/:account/:password/:card_num',
            name: 'MainMenu',
            component: MainMenu,
            children: [
                {
                    path: 'bookSearch',
                    name: 'BookSearch',
                    component: BookSearch
                },
                {
                    path: 'bookBorrow',
                    name: 'BookBorrow',
                    component: BookBorrow
                },
                {
                    path: 'bookReturn',
                    name: 'BookReturn',
                    component: BookReturn
                },
                {
                    path: 'personInfo',
                    name: 'PersonInfo',
                    component: PersonInfo
                },
                {
                    path: 'adminBookManage',
                    name: 'AdminBookManage',
                    component: AdminBookManage
                },
                {
                    path: 'adminReaderManage',
                    name: 'AdminReaderManage',
                    component: AdminReaderManage
                },
                {
                    path: 'adminBorrowManage',
                    name: 'AdminBorrowManage',
                    component: AdminBorrowManage
                }
            ]
        },
        {
            path: '/',
            redirect: '/loginRegister'
        }
    ],
    mode: 'history'
});