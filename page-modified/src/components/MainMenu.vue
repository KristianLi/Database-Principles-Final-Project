<template>
  <el-container>
    <el-header class="maintitle">图书管理系统</el-header>
    <el-container>
      <!--侧边菜单栏-->
      <el-aside style="width: 250px;height: 900px;">
        <el-menu :router='true' active-text-color='#ff2e63'>
          <el-menu-item :index="`/mainMenu/${account}/${password}/${card_num}/adminBookManage`">
            <template slot='title'>
              <span class="parent-menu">图书管理</span>
            </template>
          </el-menu-item>

          <el-menu-item :index="`/mainMenu/${account}/${password}/${card_num}/adminReaderManage`">
            <template slot='title'>
              <span class="parent-menu">读者管理</span>
            </template>
          </el-menu-item>

          <el-menu-item :index="`/mainMenu/${account}/${password}/${card_num}/adminBorrowManage`">
            <template slot='title'>
              <span class="parent-menu">借阅信息管理</span>
            </template>
          </el-menu-item>

          <el-submenu :index="`/mainMenu/${account}/${password}/${card_num}/`">
            <template slot='title'>
              <span class="parent-menu">读者借阅</span>
            </template>
            <el-menu-item :index="`/mainMenu/${account}/${password}/${card_num}/bookSearch`">书籍查询</el-menu-item>
            <el-menu-item :index="`/mainMenu/${account}/${password}/${card_num}/bookBorrow`">书籍借阅</el-menu-item>
            <el-menu-item :index="`/mainMenu/${account}/${password}/${card_num}/bookReturn`">书籍归还</el-menu-item>
          </el-submenu>
          <el-menu-item :index="`/mainMenu/${account}/${password}/${card_num}/personInfo`">
            <template slot='title'>
              <span class="parent-menu">个人信息</span>
            </template>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <div>
        </div>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: 'MainMenu',
  data() {
    return {
      account: '',
      password: '',
      card_num:''
    };
  },
  created() {
    this.account = this.$route.params.account;
    this.password = this.$route.params.password;
    this.card_num = this.$route.params.card_num;
    console.log(`Account: ${this.account}`);
    console.log(`Password: ${this.password}`);
    console.log(`Card_num: ${this.card_num}`);
  }
}
</script>

<style>
.el-header {
  background-color: #e3fdfd;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  color: #333;
  line-height: 20px;
}

body>.el-container {
  margin-bottom: 0px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

body {
  margin: 0px;
}

.maintitle {
  font-size: 30px;
  font-weight: bold;
  text-align: center;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 260px;
  height: 100%;
}

.parent-menu {
  font-size: 15px;
  font-weight: bold;
  color: #333;
  line-height: 60px;
  text-align: center;
}
</style>