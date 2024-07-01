<template>
  <div class="login_face">
    <div class="welcome_container">
      <h1 class="welcome_title">欢迎使用图书管理系统</h1>
      <div class="login_container">

        <h2 class="login_title">{{ isLogin ? '用户登录' : '用户注册' }}</h2>
        <el-input placeholder="账号" v-model="account" clearable id="account" class="custom-input"></el-input>
        <el-input placeholder="密码" v-model="password" id="password" show-password class="custom-input"></el-input>
        <el-input v-if="!isLogin" placeholder="借书证号" v-model="card_num" id="card_num" class="custom-input"></el-input>

        <div class="button_container">
          <el-button v-if="isLogin" id="btn" type="primary" @click="login()">登录</el-button>
<!--          <el-button v-else id="register-btn" type="primary" @click="register()">注册</el-button>-->
        </div>

        <div class="switch_container">
<!--          <el-link @click="toggleForm">{{ isLogin ? '没有账号？注册' : '已有账号？登录' }}</el-link>-->
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import axios from "axios";

export default {
  data() {
    return {
      account: '',
      password: '',
      card_num: '',
      isLogin: true,
      isSuccess: true
    }
  },
  methods: {
    userinfo() {

    },
    async login() {
      // 登录功能
      // 检查账号和密码是否为空
      if (this.account.length === 0) {
        this.$message({
          message: '账号不能为空!',
          type: 'warning'
        });
        return;
      }
      if (this.password.length === 0) {
        this.$message({
          message: '密码不能为空!',
          type: 'warning'
        });
        return;
      }
      const formData = new FormData();
      formData.append('account', this.account);
      formData.append('password', this.password);
      // 后端交互 to do
      // isSuccess 测试用的，模拟后端返回成功
      try {
        const response = await axios.post('http://localhost:8080/user/login', formData, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        });
        if (response.data.code === 0) {
          this.message = response.data.data; // 显示"登陆成功"
          const account = this.account;
          const password = this.password;
          const newfd = new FormData();
          newfd.append('account', this.account);
          const res = await axios.post(`http://localhost:8080/user/userinfo`,newfd, {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            }
          });
          if(res.data.code === 0){
            this.card_num = res.data.data.card_num;
          }
          const card_num = this.card_num;
          this.$router.push(`/mainMenu/${account}/${password}/${card_num}/`)
        } else {
          this.message = `Login failed: ${response.data.message}`;
        }
      } catch (error) {
        console.error('There was an error!', error);
        this.message = 'An error occurred during login.';
      }
    },
    register() {
      if (this.account.length === 0) {
        this.$message({
          message: '账号不能为空!',
          type: 'warning'
        });
        return;
      }
      if (this.password.length === 0) {
        this.$message({
          message: '密码不能为空!',
          type: 'warning'
        });
        return;
      }
      if (this.card_num.length === 0) {
        this.$message({
          message: '借书证号不能为空!',
          type: 'warning'
        });
        return;
      }
      // 后端交互 to do
      if (this.isSuccess) {
        this.$message({
          message: '注册成功!',
          type: 'success'
        });
        this.toggleForm();
      } else {
        this.$message({
          message: '注册失败!',
          type: 'error'
        });
      }
    },
    toggleForm() {
      this.isLogin = !this.isLogin;
      this.account = '';
      this.password = '';
      this.confirmPassword = '';
    }
  }
}
</script>

<style>
.login_face {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background: linear-gradient(to right, #d0e7ff, #a2cffe);
  background-size: cover;
}

.welcome_container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.welcome_title {
  font-size: 36px;
  color: white;
  margin-bottom: 40px;
  text-align: center;
}

.login_container {
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background: white;
  width: 15%;
}

.login_title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 10px;
  text-align: center;
  color: #333;
}

.custom-input .el-input__inner {
  border-radius: 8px;
  border: 1px solid #dcdfe6;
  padding: 10px 15px;
  transition: border-color 0.3s, box-shadow 0.3s;
}

.custom-input .el-input__inner:focus {
  border-color: #409eff;
  box-shadow: 0 0 5px rgba(64, 158, 255, 0.2);
}

.button_container {
  display: flex;
  gap: 10px;
}

#btn,
#register-btn {
  width: 100%;
  padding: 10px 0;
  border-radius: 8px;
}

.switch_container {
  text-align: center;
  margin-top: 10px;
}
</style>
